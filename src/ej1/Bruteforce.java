package ej1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Bruteforce {
    private final static String palabras = "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79";
    private final static int longitudPalabra = 4;
    private static final int numHilos = Runtime.getRuntime().availableProcessors();
    private static final AtomicBoolean found = new AtomicBoolean(false);
    private static String passwordEncontrada = null;

    private static byte[] obtenerHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(text.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void buscarRangoPassword(int startChar, int endChar, byte[] targetHash){
        StringBuilder password = new StringBuilder();
        for (int i = startChar; i < endChar && !found.get(); i++) {
            password.setLength(0);
            password.append((char)('a' + i));
            probarTodasCombinaciones(password, 1, targetHash);

        }
    }

    private static void probarTodasCombinaciones(StringBuilder actual, int posicion, byte[] targetHash) {
        if (found.get()) return;

        if (posicion == longitudPalabra) {
            byte[] currentHash = obtenerHash(actual.toString());
            if (Arrays.equals(currentHash, targetHash)) {
                passwordEncontrada = actual.toString();
                found.set(true);
            }
            return;
        }

        for (char c = 'a'; c <= 'z' && !found.get(); c++) {
            actual.append(c);
            probarTodasCombinaciones(actual, posicion + 1, targetHash);
            actual.setLength(actual.length() - 1);
        }
    }
    private static byte[] hexStringToArrayDeBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }



    public static void main (String[] args) {
        long startTime = System.currentTimeMillis();
        
        byte[] destinoHashBytes = hexStringToArrayDeBytes(palabras);
        
        int longitudPartes = 26/numHilos+1;


    }


}

