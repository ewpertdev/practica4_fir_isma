package ej2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que inicia el juego de búsqueda del tesoro.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Main {
    /**
     * Constructor por defecto.
     */
    public Main() {
        // Constructor vacío
    }

    private static final int TERRAIN_SIZE = 15;
    private static final int NUM_PLAYERS = 4;
    private static Terrain terrain;
    private static List<Player> players;
    private static ExecutorService executor;

    /**
     * Método principal que inicia y ejecuta el juego.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== Juego de Búsqueda del Tesoro ===");
        initializeGame();
        runGame();
        determineWinner();
    }

    /**
     * Inicializa el juego creando el terreno, los jugadores y la barrera de sincronización.
     */
    private static void initializeGame() {
        terrain = new Terrain(TERRAIN_SIZE, NUM_PLAYERS);
        CyclicBarrier barrier = new CyclicBarrier(NUM_PLAYERS, () -> {
            System.out.println("\n=== Estado del terreno ===");
            terrain.printTerrain();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        players = new ArrayList<>();
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            players.add(new Player(i, terrain, barrier));
        }

        executor = Executors.newFixedThreadPool(NUM_PLAYERS);
    }

    /**
     * Ejecuta el juego iniciando los hilos de los jugadores y esperando su finalización.
     */
    private static void runGame() {
        System.out.println("\nEstado inicial del terreno:");
        terrain.printTerrain();

        for (Player player : players) {
            executor.execute(player);
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.MINUTES)) {
                System.out.println("El juego ha excedido el tiempo límite!");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Determina y anuncia el ganador del juego basado en la cantidad de oro recolectado.
     */
    private static void determineWinner() {
        System.out.println("\n=== Fin del juego ===");

        if (!terrain.hasAlivePlayers()) {
            System.out.println("¡Todos los jugadores han encontrado minas! No hay ganador.");
            return;
        }

        Player winner = null;
        int maxGold = -1;

        for (Player player : players) {
            if (player.isAlive() && player.getGoldCount() > maxGold) {
                maxGold = player.getGoldCount();
                winner = player;
            }
            System.out.printf("%s: %d pepitas%s%n", 
                player.getName(), 
                player.getGoldCount(),
                player.isAlive() ? "" : " (eliminado)");
        }

        if (winner != null) {
            System.out.println("\n¡" + winner.getName() + " ha ganado con " + 
                             maxGold + " pepitas de oro!");
        } else {
            System.out.println("\nNo hay ganador.");
        }
    }
}
