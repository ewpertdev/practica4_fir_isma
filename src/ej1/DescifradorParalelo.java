package ej1;

import ej1.utils.HashUtils;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DescifradorParalelo implements IDescifrador {
    private final AtomicReference<String> passwordEncontrada;
    private final MessageDigest digest;
    private final int numHilos;

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

    private void comprobarPassword(String password, byte[] hashObjetivo) {
        byte[] hashActual;
        synchronized(digest) {
            hashActual = HashUtils.obtenerHash(digest, password);
        }
        if (Arrays.equals(hashActual, hashObjetivo)) {
            passwordEncontrada.set(password);
        }
    }

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