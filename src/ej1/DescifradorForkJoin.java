package ej1;

import ej1.utils.HashUtils;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementación del descifrador usando ForkJoin Framework.
 * Utiliza división recursiva del trabajo para paralelizar la búsqueda.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class DescifradorForkJoin implements IDescifrador {
    private final AtomicReference<String> passwordEncontrada;
    private final MessageDigest digest;
    private static final int UMBRAL = 2;

    /**
     * Constructor que inicializa el descifrador ForkJoin.
     */
    public DescifradorForkJoin() {
        this.passwordEncontrada = new AtomicReference<>(null);
        this.digest = HashUtils.crearMessageDigest();
    }

    @Override
    public void encontrarPassword(String hashObjetivo, int longitud) {
        long tiempoInicio = System.currentTimeMillis();
        byte[] hashObjetivoBytes = HashUtils.hexStringToByteArray(hashObjetivo);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new TareaBusqueda("", 0, longitud, hashObjetivoBytes));
        pool.shutdown();

        mostrarResultados(System.currentTimeMillis() - tiempoInicio);
    }

    /**
     * Tarea recursiva que implementa la estrategia divide y vencerás.
     */
    private class TareaBusqueda extends RecursiveAction {
        private final String prefijo;
        private final int nivel;
        private final int longitudTotal;
        private final byte[] hashObjetivo;

        /**
         * Constructor de la tarea de búsqueda.
         * 
         * @param prefijo Prefijo actual de la contraseña
         * @param nivel Nivel actual de recursión
         * @param longitudTotal Longitud total de la contraseña
         * @param hashObjetivo Hash objetivo en bytes
         */
        public TareaBusqueda(String prefijo, int nivel, int longitudTotal, byte[] hashObjetivo) {
            this.prefijo = prefijo;
            this.nivel = nivel;
            this.longitudTotal = longitudTotal;
            this.hashObjetivo = hashObjetivo;
        }

        @Override
        protected void compute() {
            if (passwordEncontrada.get() != null) return;

            if (nivel >= longitudTotal) {
                comprobarPassword(prefijo, hashObjetivo);
                return;
            }

            if (nivel < UMBRAL) {
                dividirTrabajo();
            } else {
                procesarSecuencialmente();
            }
        }

        /**
         * Divide el trabajo en subtareas para procesamiento paralelo.
         */
        private void dividirTrabajo() {
            TareaBusqueda[] tareas = new TareaBusqueda[26];
            int i = 0;
            for (char c = 'a'; c <= 'z' && passwordEncontrada.get() == null; c++) {
                tareas[i++] = new TareaBusqueda(
                    prefijo + c,
                    nivel + 1,
                    longitudTotal,
                    hashObjetivo
                );
            }
            invokeAll(tareas);
        }

        /**
         * Procesa secuencialmente cuando el tamaño del problema es pequeño.
         */
        private void procesarSecuencialmente() {
            StringBuilder sb = new StringBuilder(prefijo);
            completarSecuencialmente(sb, nivel, longitudTotal, hashObjetivo);
        }
    }

    /**
     * Completa la búsqueda de manera secuencial.
     * 
     * @param actual StringBuilder con la combinación actual
     * @param posicion Posición actual en la contraseña
     * @param longitud Longitud total de la contraseña
     * @param hashObjetivo Hash objetivo en bytes
     */
    private void completarSecuencialmente(StringBuilder actual, int posicion, int longitud, byte[] hashObjetivo) {
        if (passwordEncontrada.get() != null) return;

        if (posicion == longitud) {
            comprobarPassword(actual.toString(), hashObjetivo);
            return;
        }

        for (char c = 'a'; c <= 'z' && passwordEncontrada.get() == null; c++) {
            actual.append(c);
            completarSecuencialmente(actual, posicion + 1, longitud, hashObjetivo);
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