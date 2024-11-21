package ej1;

import ej1.utils.HashUtils;
import java.security.MessageDigest;
import java.util.Arrays;

public class DescifradorSecuencial implements IDescifrador {
    private final MessageDigest digest;
    private String passwordEncontrada;

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

    private void comprobarPassword(String password, byte[] hashObjetivo) {
        byte[] hashActual = HashUtils.obtenerHash(digest, password);
        if (Arrays.equals(hashActual, hashObjetivo)) {
            passwordEncontrada = password;
        }
    }

    private void mostrarResultados(long tiempoTotal) {
        if (passwordEncontrada != null) {
            System.out.println("Contraseña encontrada: " + passwordEncontrada);
        } else {
            System.out.println("No se encontró la contraseña");
        }
        System.out.println("Tiempo de ejecución: " + tiempoTotal + "ms");
    }
} 