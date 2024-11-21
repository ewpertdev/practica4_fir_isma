package ej1.utils;

import ej1.*;

public class Benchmark {
    public static void ejecutarBenchmarks() {
        String[] passwords = {"aaaa", "mmmm", "zzzz", "zzzzz"};
        
        for (String password : passwords) {
            System.out.println("\n=== Benchmark para password: " + password + " ===");
            String hash = bytesToHex(HashUtils.obtenerHash(HashUtils.crearMessageDigest(), password));
            int longitud = password.length();
            
            probarTodos(hash, longitud);
        }
    }
    
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
    
    private static void medirTiempo(String nombre, IDescifrador descifrador, String hash, int longitud) {
        System.gc(); // Intentar limpiar memoria antes de cada prueba
        
        long inicio = System.currentTimeMillis();
        descifrador.encontrarPassword(hash, longitud);
        long tiempo = System.currentTimeMillis() - inicio;
        
        System.out.printf("%s: %dms%n", nombre, tiempo);
    }
    
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