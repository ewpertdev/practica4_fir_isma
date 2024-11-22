package descifradorFuerzaBruta.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase de utilidad para operaciones relacionadas con hashes.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class HashUtils {
    /**
     * Constructor por defecto privado para evitar instanciación.
     */
    private HashUtils() {
        // Constructor privado para evitar instanciación
    }
    
    /**
     * Genera el hash SHA-256 de un texto dado.
     * 
     * @param digest Instancia de MessageDigest a utilizar
     * @param texto Texto a hashear
     * @return Array de bytes con el hash generado
     */
    public static byte[] obtenerHash(MessageDigest digest, String texto) {
        return digest.digest(texto.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Convierte una cadena hexadecimal en un array de bytes.
     * 
     * @param s String hexadecimal a convertir
     * @return Array de bytes correspondiente
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
    /**
     * Crea una nueva instancia de MessageDigest para SHA-256.
     * 
     * @return Nueva instancia de MessageDigest
     * @throws RuntimeException si el algoritmo SHA-256 no está disponible
     */
    public static MessageDigest crearMessageDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No se pudo inicializar SHA-256", e);
        }
    }
} 