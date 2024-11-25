package descifradorFuerzaBruta_Fir_Ismael;

import java.security.MessageDigest;
import java.util.Arrays;

import descifradorFuerzaBruta_Fir_Ismael.utils.HashUtils;

/**
 * Implementación secuencial del descifrador de contraseñas.
 * Realiza una búsqueda por fuerza bruta de manera secuencial.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class DescifradorSecuencial implements IDescifrador {
    private final MessageDigest digest;
    private String passwordEncontrada;

    /**
     * Constructor que inicializa el MessageDigest para SHA-256.
     */
    public DescifradorSecuencial() {
        this.digest = HashUtils.crearMessageDigest();
    }

    @Override
    public void encontrarPassword(String hashObjetivo, int longitud) {
        long tiempoInicio = System.currentTimeMillis();
        byte[] hashObjetivoBytes = HashUtils.hexStringToByteArray(hashObjetivo);
        
        StringBuilder intentoActual = new StringBuilder(longitud);
        probarCombinaciones(intentoActual, longitud, hashObjetivoBytes);
        
        mostrarResultados(System.currentTimeMillis() - tiempoInicio);
    }

    /**
     * Prueba todas las combinaciones posibles de manera recursiva.
     * 
     * @param actual StringBuilder actual para construir las combinaciones
     * @param longitud Longitud objetivo de la contraseña
     * @param hashObjetivo Hash objetivo en bytes
     */
    private void probarCombinaciones(StringBuilder actual, int longitud, byte[] hashObjetivo) {
        if (passwordEncontrada != null) return;
        
        if (actual.length() == longitud) {
            comprobarPassword(actual.toString(), hashObjetivo);
            return;
        }
        
        for (char c = 'a'; c <= 'z' && passwordEncontrada == null; c++) {
            actual.append(c);
            probarCombinaciones(actual, longitud, hashObjetivo);
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
        byte[] hashActual = HashUtils.obtenerHash(digest, password);
        if (Arrays.equals(hashActual, hashObjetivo)) {
            passwordEncontrada = password;
        }
    }

    /**
     * Muestra los resultados de la búsqueda y el tiempo empleado.
     * 
     * @param tiempoTotal Tiempo total empleado en millisegundos
     */
    private void mostrarResultados(long tiempoTotal) {
        if (passwordEncontrada != null) {
            System.out.println("Contraseña encontrada: " + passwordEncontrada);
        } else {
            System.out.println("No se encontró la contraseña");
        }
        System.out.println("Tiempo de ejecución: " + tiempoTotal + "ms");
    }
} 