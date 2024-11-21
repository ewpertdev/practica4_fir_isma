package ej1;

public class Descifrador {
    private static final String HASH_OBJETIVO = 
        "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79";
    private static final int LONGITUD_PASSWORD = 4;

    public static void main(String[] args) {
        System.out.println("=== Descifrador de Contraseñas ===");
        System.out.println("Hash objetivo: " + HASH_OBJETIVO);
        System.out.println("Longitud: " + LONGITUD_PASSWORD);
        
        probarImplementaciones();
    }
    
    private static void probarImplementaciones() {
        System.out.println("\n=== Prueba Secuencial ===");
        probarDescifrador(new DescifradorSecuencial());
        
        System.out.println("\n=== Prueba Paralela ===");
        int numHilos = Runtime.getRuntime().availableProcessors();
        probarDescifrador(new DescifradorParalelo(numHilos));
    }
    
    private static void probarDescifrador(IDescifrador descifrador) {
        System.out.println("Usando: " + descifrador.getClass().getSimpleName());
        System.out.println("------------------------");
        descifrador.encontrarPassword(HASH_OBJETIVO, LONGITUD_PASSWORD);
    }
} 