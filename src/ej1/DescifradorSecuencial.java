package ej1;

import ej1.utils.HashUtils;
import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicReference;

public class DescifradorParalelo implements IDescifrador {
    private final AtomicReference<String> passwordEncontrada;
    private final MessageDigest digest;

    public DescifradorParalelo() {
        this.passwordEncontrada = new AtomicReference<>(null);
        this.digest = HashUtils.crearMessageDigest();
    }

    @Override
    public void encontrarPassword(String hashObjetivo, int longitud) {
        // Implementación pendiente
        throw new UnsupportedOperationException("Versión paralela en desarrollo");
    }

    protected void comprobarPassword(String password, byte[] hashObjetivo) {
        byte[] hashActual;
        synchronized(digest) {
            hashActual = HashUtils.obtenerHash(digest, password);
        }
        // Implementación pendiente
    }
} 