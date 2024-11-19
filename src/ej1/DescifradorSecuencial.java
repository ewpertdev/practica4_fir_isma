package ej1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DescifradorSecuencial implements IDescifrador {
    private final MessageDigest digest;
    private String passwordEncontrada;

    public DescifradorSecuencial() {
        try {
            this.digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No se pudo inicializar SHA-256", e);
        }
    }

    @Override
    public void encontrarPassword(String hashObjetivo, int longitud) {
        long tiempoInicio = System.currentTimeMillis();
        byte[] hashObjetivoBytes = hexStringToByteArray(hashObjetivo);
        
        StringBuilder intentoActual = new StringBuilder(longitud);
        probarCombinaciones(intentoActual, longitud, hashObjetivoBytes);
        
        mostrarResultados(System.currentTimeMillis() - tiempoInicio);
    }

    private void mostrarResultados(long tiempoTotal) {
        if (passwordEncontrada != null) {
            System.out.println("Contraseña encontrada: " + passwordEncontrada);
        } else {
            System.out.println("No se encontró la contraseña");
        }
        System.out.println("Tiempo de ejecución: " + tiempoTotal + "ms");
    }

    private void probarCombinaciones(StringBuilder actual, int longitud, byte[] hashObjetivo) {
        if (passwordEncontrada != null) return;
        
        if (actual.length() == longitud) {
            byte[] hashActual = obtenerHash(actual.toString());
            if (Arrays.equals(hashActual, hashObjetivo)) {
                passwordEncontrada = actual.toString();
            }
            return;
        }
        
        for (char c = 'a'; c <= 'z' && passwordEncontrada == null; c++) {
            actual.append(c);
            probarCombinaciones(actual, longitud, hashObjetivo);
            actual.setLength(actual.length() - 1);
        }
    }

    private byte[] obtenerHash(String texto) {
        return digest.digest(texto.getBytes(StandardCharsets.UTF_8));
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
} 