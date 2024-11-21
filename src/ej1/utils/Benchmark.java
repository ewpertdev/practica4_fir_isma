package ej1.utils;

import ej1.*;

/**
 * Clase utilitaria para realizar mediciones de rendimiento.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Benchmark {
    /**
     * Constructor por defecto.
     */
    public Benchmark() {
        // Constructor vacío
    }
    
    /**
     * Ejecuta una serie de pruebas de rendimiento con diferentes contraseñas.
     */
    public static void ejecutarBenchmarks() {
        String[] passwords = {"aaaa", "mmmm", "zzzz", "zzzzz"};
        
        for (String password : passwords) {
            System.out.println("\n=== Benchmark para password: " + password + " ===");
            String hash = bytesToHex(HashUtils.obtenerHash(HashUtils.crearMessageDigest(), password));
            int longitud = password.length();
            
            probarTodos(hash, longitud);
        }
    }
    
    /**
     * Prueba todas las implementaciones de descifradores con una contraseña específica.
     * 
     * @param hash Hash de la contraseña a probar
     * @param longitud Longitud de la contraseña
     */
    private static void probarTodos(String hash, int longitud) {
        // Secuencial
        medirTiempo("Secuencial", new DescifradorSecuencial(), hash, longitud);
        
        // Paralelo con diferentes números de hilos
        int[] numHilos = {2, 4, 8, Runtime.getRuntime().availableProcessors()};
        for (int n : numHilos) {
            medirTiempo("Paralelo-" + n + "hilos", new DescifradorParalelo(n), hash, longitud);
        }
        
        // ForkJoin
        medirTiempo("ForkJoin", new DescifradorForkJoin(), hash, longitud);
    }
    
    /**
     * Mide el tiempo de ejecución de un descifrador específico.
     * 
     * @param nombre Nombre identificativo de la prueba
     * @param descifrador Implementación del descifrador a probar
     * @param hash Hash de la contraseña a encontrar
     * @param longitud Longitud de la contraseña
     */
    private static void medirTiempo(String nombre, IDescifrador descifrador, String hash, int longitud) {
        System.gc(); // Intentar limpiar memoria antes de cada prueba
        
        long inicio = System.currentTimeMillis();
        descifrador.encontrarPassword(hash, longitud);
        long tiempo = System.currentTimeMillis() - inicio;
        
        System.out.printf("%s: %dms%n", nombre, tiempo);
    }
    
    /**
     * Convierte un array de bytes en su representación hexadecimal.
     * 
     * @param hash Array de bytes a convertir
     * @return String con la representación hexadecimal
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
} 