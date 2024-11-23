package juego;

public class Principal {
     // Tamaño del terreno y número de jugadores configurables
    private static final int TAMANIO_TERRENO = 8; // Cambiar el tamaño del terreno aquí
    private static final int NUMERO_JUGADORES = 4; // Cambiar el número de jugadores aquí
    private static Terreno terreno;
    private static ExecutorService ejecutor;
    
    public static void main(String[] args) {
          System.out.println("=== Juego de Minas y Oro ===");

        // Inicializamos el terreno
        terreno = new Terreno(TAMANIO_TERRENO);
        terreno.imprimirTerreno();
    }
}
