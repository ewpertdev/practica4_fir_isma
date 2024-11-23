package ej2.display;

import ej2.Terrain;
import ej2.observer.GameObserver;
import ej2.events.GameEvent;

/**
 * Clase responsable de la visualización del juego
 */
public class GameDisplay implements GameObserver {
    private final Terrain terrain;

    public GameDisplay(Terrain terrain) {
        this.terrain = terrain;
    }

    public void displayGameState() {
        System.out.println("\n=== Estado del terreno ===");
        terrain.printTerrain();
    }

    public void displayGameStart() {
        System.out.println("=== Juego de Búsqueda del Tesoro ===");
        System.out.println("\nEstado inicial del terreno:");
        terrain.printTerrain();
    }

    public void displayGameEnd(String winnerMessage) {
        System.out.println("\n=== Fin del juego ===");
        System.out.println(winnerMessage);
    }

    @Override
    public void onGameEvent(GameEvent event) {
        // Manejar diferentes tipos de eventos y mostrar mensajes apropiados
        if (event instanceof GoldCollectedEvent) {
            // Mostrar mensaje de oro recolectado
        } else if (event instanceof PlayerDiedEvent) {
            // Mostrar mensaje de jugador eliminado
        }
    }
} 