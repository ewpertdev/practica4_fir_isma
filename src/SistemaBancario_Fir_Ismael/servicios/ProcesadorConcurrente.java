package SistemaBancario_Fir_Ismael.servicios;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que maneja el procesamiento concurrente de transferencias bancarias.
 * Utiliza un pool de hilos para procesar múltiples archivos de transferencias simultáneamente.
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class ProcesadorConcurrente {
    private final ExecutorService executorService;
    private final ServicioTransferencias servicioTransferencias;
    private static final int NUM_HILOS = 3; // Número de hilos en el pool
    private final AtomicInteger transferenciasExitosas = new AtomicInteger(0);
    private final AtomicInteger transferenciasFallidas = new AtomicInteger(0);

    /**
     * Constructor que inicializa el pool de hilos y el servicio de transferencias.
     * @param servicioTransferencias Servicio que procesa las transferencias
     */
    public ProcesadorConcurrente(ServicioTransferencias servicioTransferencias) {
        this.servicioTransferencias = servicioTransferencias;
        this.executorService = Executors.newFixedThreadPool(NUM_HILOS);
    }

    /**
     * Procesa múltiples archivos de transferencias de forma concurrente.
     * @param archivos Array con las rutas de los archivos a procesar
     */
    public void procesarArchivosConcurrentemente(String[] archivos) {
        System.out.println("\n=== Inicio de Procesamiento Concurrente ===");
        System.out.println("Hilos activos: " + NUM_HILOS);
        System.out.println("Archivos a procesar: " + archivos.length);
        System.out.println("=====================================\n");
        
        // Crear una lista para almacenar las tareas futuras
        List<Future<?>> tareas = new ArrayList<>();
        
        // Enviar cada archivo como una tarea al pool de hilos
        for (String archivo : archivos) {
            Future<?> tarea = executorService.submit(() -> {
                try {
                    System.out.println("Hilo " + Thread.currentThread().getName() + 
                                     " procesando archivo: " + archivo);
                    servicioTransferencias.procesarArchivoTransferencias(archivo);
                } catch (IOException e) {
                    System.err.println("Error en hilo " + Thread.currentThread().getName() + 
                                     " al procesar archivo " + archivo + ": " + e.getMessage());
                }
            });
            tareas.add(tarea);
        }

        // Esperar a que terminen todas las tareas
        cerrarYEsperar(tareas);
        
        mostrarResumen();
    }

    /**
     * Cierra el pool de hilos y espera a que terminen todas las tareas.
     * @param tareas Lista de tareas futuras a esperar
     */
    private void cerrarYEsperar(List<Future<?>> tareas) {
        try {
            // Esperar a que cada tarea termine
            for (Future<?> tarea : tareas) {
                try {
                    tarea.get(30, TimeUnit.SECONDS); // Timeout por tarea
                } catch (TimeoutException e) {
                    System.err.println("Tarea excedió el tiempo límite");
                    tarea.cancel(true);
                }
            }
            
            // Cerrar el pool de hilos
            executorService.shutdown();
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("Algunas tareas no terminaron. Forzando cierre...");
                executorService.shutdownNow();
            } else {
                System.out.println("Procesamiento concurrente completado exitosamente");
            }
        } catch (InterruptedException e) {
            System.err.println("Procesamiento interrumpido");
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.err.println("Error durante la ejecución: " + e.getCause().getMessage());
        }
    }

    private void mostrarResumen() {
        System.out.println("\n=== Resumen de Operaciones ===");
        System.out.println("Transferencias exitosas: " + transferenciasExitosas.get());
        System.out.println("Transferencias fallidas: " + transferenciasFallidas.get());
        System.out.println("Total procesadas: " + 
            (transferenciasExitosas.get() + transferenciasFallidas.get()));
        System.out.println("============================\n");
    }
} 