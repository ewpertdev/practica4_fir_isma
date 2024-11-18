package ej1;

public class Descifrador {
    public static void main(String[] args) {
        String hash = "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79";
        int longitud = 4;
        
        System.out.println("=== Iniciando búsqueda ===");
        System.out.println("Hash objetivo: " + hash);
        System.out.println("Longitud: " + longitud);
        System.out.println("------------------------");
        
        DescifradorSecuencial descifrador = new DescifradorSecuencial();
        descifrador.encontrarPassword(hash, longitud);
    }
} 