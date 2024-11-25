package SistemaBancario_Fir_Ismael.servicios;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

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
        System.out.println("Iniciando procesamiento concurrente con " + NUM_HILOS + " hilos");
        
        // Enviar cada archivo como una tarea al pool de hilos
        for (String archivo : archivos) {
            executorService.submit(() -> {
                try {
                    System.out.println("Hilo " + Thread.currentThread().getName() + 
                                     " procesando archivo: " + archivo);
                    servicioTransferencias.procesarArchivoTransferencias(archivo);
                } catch (IOException e) {
                    System.err.println("Error en hilo " + Thread.currentThread().getName() + 
                                     " al procesar archivo " + archivo + ": " + e.getMessage());
                }
            });
        }

        // Esperar a que terminen todas las tareas
        cerrarYEsperar();
    }

    /**
     * Cierra el pool de hilos y espera a que terminen todas las tareas.
     */
    private void cerrarYEsperar() {
        executorService.shutdown();
        try {
            // Esperar hasta 1 minuto para que terminen todas las tareas
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.err.println("Tiempo de espera agotado. Forzando cierre...");
                executorService.shutdownNow();
            }
            System.out.println("Procesamiento concurrente completado");
        } catch (InterruptedException e) {
            System.err.println("Procesamiento interrumpido");
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
} 