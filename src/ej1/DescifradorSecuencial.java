package ej1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* TODO: Faltaria optimizar esta implementacion y mejorar el manejo de recursos + errores */
public class DescifradorSecuencial {
    private String passwordEncontrada = null;
    
    // Método que permite encontrar la contraseña.
    public void encontrarPassword(String hashObjetivo, int longitud) {
        // Se obtiene el tiempo de inicio.
        long tiempoInicio = System.currentTimeMillis();
        
        // Se convierte el hash objetivo a un array de bytes.
        byte[] hashObjetivoBytes = hexStringToByteArray(hashObjetivo);
        // Se crea un StringBuilder para almacenar el intento actual.
        StringBuilder intentoActual = new StringBuilder();
        // Se llama al método que permite probar todas las combinaciones.
        probarCombinaciones(intentoActual, longitud, hashObjetivoBytes);
        
        // Se obtiene el tiempo de fin.
        long tiempoFin = System.currentTimeMillis();
        
        // Se muestra el resultado.
        mostrarResultados(tiempoFin - tiempoInicio);
    }
    
    private void mostrarResultados(long tiempoTotal) {
        // Si se ha encontrado la contraseña, se muestra.
        if (passwordEncontrada != null) {
            System.out.println("Contraseña encontrada: " + passwordEncontrada);
        } else {
            System.out.println("No se encontró la contraseña");
        }
        // Se muestra el tiempo de ejecución.
        System.out.println("Tiempo de ejecución: " + tiempoTotal + "ms");
    }
    
    // Método que permite probar todas las combinaciones.
    private void probarCombinaciones(StringBuilder actual, int longitud, byte[] hashObjetivo) {
        // Si se ha encontrado la contraseña, se devuelve.
        if (passwordEncontrada != null) return;
        
        // Si la longitud del intento actual es igual a la longitud objetivo, se compara el hash.
        if (actual.length() == longitud) {
            byte[] hashActual = obtenerHash(actual.toString());
            if (Arrays.equals(hashActual, hashObjetivo)) {
                passwordEncontrada = actual.toString();
            }
            return;
        }
        
        // Se recorre el alfabeto para probar todas las combinaciones.
        for (char c = 'a'; c <= 'z' && passwordEncontrada == null; c++) {
            actual.append(c);
            probarCombinaciones(actual, longitud, hashObjetivo);
            actual.setLength(actual.length() - 1);
        }
    }
    
    // Método que permite obtener el hash de un texto.
    private byte[] obtenerHash(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(texto.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Método que permite convertir un hash en un array de bytes.
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