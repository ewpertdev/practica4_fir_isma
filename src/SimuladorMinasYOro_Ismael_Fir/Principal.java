package SimuladorMinasYOro_Ismael_Fir;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase principal que inicia y controla el juego de Minas y Oro.
 * Gestiona la creación del terreno y los jugadores, y coordina la ejecución del juego.
 * 
 * @author Ismael Lozano
 * @author Mohd Firdaus Bin Abdullah
 */
public class Principal {
    /**
     * Tamaño del terreno de juego
     */
    private static final int TAMANIO_TERRENO = 8;

    /**
     * Número de jugadores participantes
     */
    private static final int NUMERO_JUGADORES = 4;

    private static Terreno terreno;
    private static ExecutorService ejecutor;
    
    /**
     * Constructor por defecto de la clase Principal.
     * Se mantiene privado ya que esta clase solo contiene métodos estáticos.
     */
    private Principal() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Método principal que inicia el juego.
     * Crea el terreno, inicializa los jugadores y gestiona la ejecución de los hilos.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
          System.out.println("=== Juego de Minas y Oro ===");

        // Inicializamos el terreno
        terreno = new Terreno(TAMANIO_TERRENO);
        terreno.imprimirTerreno();

         // Creamos un grupo de hilos (uno por jugador)
        ejecutor = Executors.newFixedThreadPool(NUMERO_JUGADORES);

        // Lanzamos los jugadores en hilos separados
        for (int i = 0; i < NUMERO_JUGADORES; i++) {
            ejecutor.execute(new Jugador(i + 1, terreno));
        }

        // Cerramos el ejecutor y esperamos a que los hilos terminen
        ejecutor.shutdown();
        while (!ejecutor.isTerminated()) {
            // Espera activa hasta que todos los hilos finalicen
        }

        System.out.println("\n=== Fin del Juego ===");
    }
}
