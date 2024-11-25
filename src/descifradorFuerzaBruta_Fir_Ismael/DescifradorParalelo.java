package descifradorFuerzaBruta_Fir_Ismael;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import descifradorFuerzaBruta_Fir_Ismael.utils.HashUtils;

/**
 * Implementación paralela del descifrador de contraseñas usando ExecutorService.
 * Distribuye la carga de trabajo entre múltiples hilos para mejorar el rendimiento.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class DescifradorParalelo implements IDescifrador {
    private final AtomicReference<String> passwordEncontrada;
    private final MessageDigest digest;
    private final int numHilos;

    /**
     * Constructor que inicializa el descifrador paralelo.
     * 
     * @param numHilos Número de hilos a utilizar en el pool
     */
    public DescifradorParalelo(int numHilos) {
        this.passwordEncontrada = new AtomicReference<>(null);
        this.digest = HashUtils.crearMessageDigest();
        this.numHilos = numHilos;
    }

    @Override
    public void encontrarPassword(String hashObjetivo, int longitud) {
        long tiempoInicio = System.currentTimeMillis();
        byte[] hashObjetivoBytes = HashUtils.hexStringToByteArray(hashObjetivo);
        
        ExecutorService executor = Executors.newFixedThreadPool(numHilos);
        distribuirTrabajo(executor, hashObjetivoBytes, longitud);
        
        finalizarYMostrarResultados(executor, tiempoInicio);
    }

    /**
     * Distribuye el trabajo entre los hilos del pool.
     * Cada hilo procesa un subconjunto de las posibles primeras letras.
     * 
     * @param executor Servicio de ejecución para gestionar los hilos
     * @param hashObjetivo Hash objetivo en bytes
     * @param longitud Longitud de la contraseña a buscar
     */
    private void distribuirTrabajo(ExecutorService executor, byte[] hashObjetivo, int longitud) {
        // Distribuimos el trabajo por la primera letra
        for (char primeraLetra = 'a'; primeraLetra <= 'z'; primeraLetra++) {
            char letra = primeraLetra;
            executor.submit(() -> {
                StringBuilder base = new StringBuilder().append(letra);
                probarCombinaciones(base, 1, longitud, hashObjetivo);
            });
        }
    }

    /**
     * Prueba combinaciones de manera recursiva a partir de una base dada.
     * 
     * @param actual StringBuilder con la combinación actual
     * @param posicion Posición actual en la contraseña
     * @param longitud Longitud total de la contraseña
     * @param hashObjetivo Hash objetivo en bytes
     */
    private void probarCombinaciones(StringBuilder actual, int posicion, int longitud, byte[] hashObjetivo) {
        if (passwordEncontrada.get() != null) return;

        if (posicion == longitud) {
            comprobarPassword(actual.toString(), hashObjetivo);
            return;
        }

        for (char c = 'a'; c <= 'z' && passwordEncontrada.get() == null; c++) {
            actual.append(c);
            probarCombinaciones(actual, posicion + 1, longitud, hashObjetivo);
            actual.setLength(actual.length() - 1);
        }
    }

    /**
     * Comprueba si una contraseña candidata corresponde al hash objetivo.
     * 
     * @param password Contraseña a comprobar
     * @param hashObjetivo Hash objetivo en bytes
     */
    private void comprobarPassword(String password, byte[] hashObjetivo) {
        byte[] hashActual;
        synchronized(digest) {
            hashActual = HashUtils.obtenerHash(digest, password);
        }
        if (Arrays.equals(hashActual, hashObjetivo)) {
            passwordEncontrada.set(password);
        }
    }

    /**
     * Finaliza el pool de hilos y muestra los resultados.
     * 
     * @param executor Servicio de ejecución a finalizar
     * @param tiempoInicio Tiempo de inicio del proceso en millisegundos
     */
    private void finalizarYMostrarResultados(ExecutorService executor, long tiempoInicio) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long tiempoTotal = System.currentTimeMillis() - tiempoInicio;
        mostrarResultados(tiempoTotal);
    }

    /**
     * Muestra los resultados de la búsqueda y el tiempo empleado.
     * 
     * @param tiempoTotal Tiempo total empleado en millisegundos
     */
    private void mostrarResultados(long tiempoTotal) {
        String password = passwordEncontrada.get();
        if (password != null) {
            System.out.println("Contraseña encontrada: " + password);
        } else {
            System.out.println("No se encontró la contraseña");
        }
        System.out.println("Tiempo de ejecución: " + tiempoTotal + "ms");
    }
} 