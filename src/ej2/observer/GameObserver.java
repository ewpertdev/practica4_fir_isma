package ej2.observer;

import ej2.events.GameEvent;

/**
 * Interfaz para observadores del juego
 */
public interface GameObserver {
    void onGameEvent(GameEvent event);
} 