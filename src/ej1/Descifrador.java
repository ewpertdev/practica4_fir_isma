package ej1;

public class Descifrador {
    public static void main(String[] args) {
        // Se define el hash objetivo y la longitud de la contraseña.
        String hash = "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79";
        // Se define la longitud de la contraseña.
        int longitud = 4;
        
        // Se muestra el inicio de la búsqueda.
        System.out.println("=== Iniciando búsqueda ===");
        System.out.println("Hash objetivo: " + hash);
        System.out.println("Longitud: " + longitud);
        System.out.println("------------------------");
        
        // Se crea una instancia de DescifradorSecuencial.
        DescifradorSecuencial descifrador = new DescifradorSecuencial();
        // Se llama al método que permite encontrar la contraseña.
        descifrador.encontrarPassword(hash, longitud);
    }
} 