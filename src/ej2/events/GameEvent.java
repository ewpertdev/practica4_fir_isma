package ej2.events;

/**
 * Clase base para todos los eventos del juego
 */
public abstract class GameEvent {
    private final long timestamp;

    protected GameEvent() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}

/**
 * Eventos específicos del juego
 */
class GoldCollectedEvent extends GameEvent {
    private final String playerName;
    private final int goldAmount;

    public GoldCollectedEvent(String playerName, int goldAmount) {
        this.playerName = playerName;
        this.goldAmount = goldAmount;
    }
}

class PlayerDiedEvent extends GameEvent {
    private final String playerName;

    public PlayerDiedEvent(String playerName) {
        this.playerName = playerName;
    }
} 