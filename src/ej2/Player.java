package ej2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Representa un jugador en el juego de búsqueda del tesoro.
 * Cada jugador se ejecuta en su propio hilo y se sincroniza con otros jugadores.
 */
public class Player implements Runnable {
    private final int id;
    private final String name;
    private Position position;
    private final Terrain terrain;
    private final CyclicBarrier barrier;
    private int goldCount;
    private boolean isAlive;
    private final Random random;

    /**
     * Constructor del jugador.
     * 
     * @param id Identificador numérico del jugador
     * @param terrain Terreno de juego
     * @param barrier Barrera para sincronización entre jugadores
     */
    public Player(int id, Terrain terrain, CyclicBarrier barrier) {
        this.id = id;
        this.name = "Jugador-" + id;
        this.terrain = terrain;
        this.barrier = barrier;
        this.goldCount = 0;
        this.isAlive = true;
        this.random = new Random();
        this.position = generateInitialPosition();
    }

    private Position generateInitialPosition() {
        Position pos;
        do {
            int x = random.nextInt(terrain.getSize());
            int y = random.nextInt(terrain.getSize());
            pos = new Position(x, y);
        } while (!terrain.isCellEmpty(pos));
        
        terrain.setContent(pos, CellContent.PLAYER);
        return pos;
    }

    @Override
    public void run() {
        try {
            while (isAlive && terrain.hasGoldNuggets() && terrain.hasAlivePlayers()) {
                makeMove();
                barrier.await(); // Esperar a que todos los jugadores hagan su movimiento
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void makeMove() {
        Position newPos = calculateNextPosition();
        if (terrain.isValidPosition(newPos) && terrain.movePlayer(position, newPos, this)) {
            position = newPos;
        }
    }

    private Position calculateNextPosition() {
        int dx = random.nextInt(3) - 1; // -1, 0, or 1
        int dy = random.nextInt(3) - 1; // -1, 0, or 1
        return new Position(position.getX() + dx, position.getY() + dy);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Position getPosition() { return position; }
    public int getGoldCount() { return goldCount; }
    public boolean isAlive() { return isAlive; }

    // Métodos para actualizar el estado del jugador
    public void incrementGoldCount() { goldCount++; }
    public void die() { 
        isAlive = false;
        System.out.println(name + " ha encontrado una mina y ha sido eliminado!");
    }
} 