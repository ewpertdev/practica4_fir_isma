package ej2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que ejecuta la simulación del juego de búsqueda del tesoro.
 */
public class Main {
    private static final int TERRAIN_SIZE = 15;
    private static final int NUM_PLAYERS = 4;
    private static Terrain terrain;
    private static List<Player> players;
    private static ExecutorService executor;

    public static void main(String[] args) {
        System.out.println("=== Juego de Búsqueda del Tesoro ===");
        initializeGame();
        runGame();
        determineWinner();
    }

    private static void initializeGame() {
        // Inicializar el terreno y la barrera cíclica
        terrain = new Terrain(TERRAIN_SIZE, NUM_PLAYERS);
        CyclicBarrier barrier = new CyclicBarrier(NUM_PLAYERS, () -> {
            // Esta acción se ejecuta cada vez que todos los jugadores completan un turno
            System.out.println("\n=== Estado del terreno ===");
            terrain.printTerrain();
            try {
                Thread.sleep(1000); // Pausa para mejor visualización
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Crear jugadores
        players = new ArrayList<>();
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            players.add(new Player(i, terrain, barrier));
        }

        // Inicializar el pool de hilos
        executor = Executors.newFixedThreadPool(NUM_PLAYERS);
    }

    private static void runGame() {
        System.out.println("\nEstado inicial del terreno:");
        terrain.printTerrain();

        // Iniciar los hilos de los jugadores
        for (Player player : players) {
            executor.execute(player);
        }

        // Esperar a que termine el juego
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

    private static void determineWinner() {
        System.out.println("\n=== Fin del juego ===");

        // Verificar si alguien ganó
        if (!terrain.hasAlivePlayers()) {
            System.out.println("¡Todos los jugadores han encontrado minas! No hay ganador.");
            return;
        }

        // Encontrar al jugador con más oro
        Player winner = null;
        int maxGold = -1;

        for (Player player : players) {
            if (player.isAlive() && player.getGoldCount() > maxGold) {
                maxGold = player.getGoldCount();
                winner = player;
            }
            // Mostrar resultados de cada jugador
            System.out.printf("%s: %d pepitas%s%n", 
                player.getName(), 
                player.getGoldCount(),
                player.isAlive() ? "" : " (eliminado)");
        }

        // Anunciar ganador
        if (winner != null) {
            System.out.println("\n¡" + winner.getName() + " ha ganado con " + 
                             maxGold + " pepitas de oro!");
        } else {
            System.out.println("\nNo hay ganador.");
        }
    }
}
