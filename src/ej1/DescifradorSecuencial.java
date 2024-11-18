package ej1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DescifradorSecuencial {
    private String passwordEncontrada = null;
    
    public void encontrarPassword(String hashObjetivo, int longitud) {
        byte[] hashObjetivoBytes = hexStringToByteArray(hashObjetivo);
        StringBuilder intentoActual = new StringBuilder();
        probarCombinaciones(intentoActual, longitud, hashObjetivoBytes);
        
        if (passwordEncontrada != null) {
            System.out.println("Contraseña encontrada: " + passwordEncontrada);
        } else {
            System.out.println("No se encontró la contraseña");
        }
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
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(texto.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
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