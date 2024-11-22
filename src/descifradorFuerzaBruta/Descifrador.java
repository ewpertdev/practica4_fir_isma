package descifradorFuerzaBruta;

/**
 * Clase principal que ejecuta las pruebas de los diferentes descifradores.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Descifrador {
    /** Hash objetivo a descifrar */
    private static final String HASH_OBJETIVO = 
        "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79";
    
    /** Longitud de la contraseña a buscar */
    private static final int LONGITUD_PASSWORD = 4;

    /**
     * Constructor por defecto.
     */
    public Descifrador() {
        // Constructor vacío
    }

    /**
     * Punto de entrada principal del programa.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== Descifrador de Contraseñas ===");
        System.out.println("Hash objetivo: " + HASH_OBJETIVO);
        System.out.println("Longitud: " + LONGITUD_PASSWORD);
        
        probarImplementaciones();
    }
    
    /**
     * Prueba todas las implementaciones de descifradores disponibles.
     */
    private static void probarImplementaciones() {
        System.out.println("\n=== Prueba Secuencial ===");
        probarDescifrador(new DescifradorSecuencial());
        
        System.out.println("\n=== Prueba Paralela ===");
        int numHilos = Runtime.getRuntime().availableProcessors();
        probarDescifrador(new DescifradorParalelo(numHilos));
        
        System.out.println("\n=== Prueba ForkJoin ===");
        probarDescifrador(new DescifradorForkJoin());
    }
    
    /**
     * Ejecuta una implementación específica del descifrador.
     * 
     * @param descifrador Implementación del descifrador a probar
     */
    private static void probarDescifrador(IDescifrador descifrador) {
        System.out.println("Usando: " + descifrador.getClass().getSimpleName());
        System.out.println("------------------------");
        descifrador.encontrarPassword(HASH_OBJETIVO, LONGITUD_PASSWORD);
    }
} 