package SimuladorMinasYOro_Ismael_Fir;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
