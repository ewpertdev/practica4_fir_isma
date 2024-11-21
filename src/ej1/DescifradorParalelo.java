package ej1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

public class DescifradorParalelo implements IDescifrador {
    private final AtomicReference<String> passwordEncontrada;
    private final MessageDigest digest;

    public DescifradorParalelo() {
        this.passwordEncontrada = new AtomicReference<>(null);
        try {
            this.digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No se pudo inicializar SHA-256", e);
        }
    }

    @Override
    public void encontrarPassword(String hashObjetivo, int longitud) {
        // Implementación pendiente
        throw new UnsupportedOperationException("Versión paralela en desarrollo");
    }

    protected byte[] obtenerHash(String texto) {
        synchronized(digest) {
            return digest.digest(texto.getBytes(StandardCharsets.UTF_8));
        }
    }

    protected byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
} 