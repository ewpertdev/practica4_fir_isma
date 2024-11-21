package ej2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Representa un jugador en el juego de búsqueda del tesoro.
 * Cada jugador se ejecuta en su propio hilo y se sincroniza con otros jugadores
 * mediante una barrera cíclica para mantener turnos ordenados.
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

    /**
     * Genera una posición inicial aleatoria válida para el jugador.
     * 
     * @return Posición inicial generada
     */
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

    /**
     * Método principal que ejecuta la lógica del jugador.
     * Se ejecuta mientras el jugador esté vivo y haya oro o jugadores vivos.
     */
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

    /**
     * Realiza un movimiento en el terreno.
     */
    private void makeMove() {
        Position newPos = calculateNextPosition();
        if (terrain.isValidPosition(newPos) && terrain.movePlayer(position, newPos, this)) {
            position = newPos;
        }
    }

    /**
     * Calcula la siguiente posición del jugador de manera aleatoria.
     * El jugador puede moverse una casilla en cualquier dirección.
     * 
     * @return Nueva posición calculada
     */
    private Position calculateNextPosition() {
        int dx = random.nextInt(3) - 1; // -1, 0, or 1
        int dy = random.nextInt(3) - 1; // -1, 0, or 1
        return new Position(position.getX() + dx, position.getY() + dy);
    }

    // Getters
    /**
     * Obtiene el identificador único del jugador.
     * @return Identificador del jugador
     */
    public int getId() { return id; }

    /**
     * Obtiene el nombre del jugador.
     * @return Nombre del jugador
     */
    public String getName() { return name; }

    /**
     * Obtiene la posición actual del jugador en el terreno.
     * @return Posición actual del jugador
     */
    public Position getPosition() { return position; }

    /**
     * Obtiene la cantidad de oro recolectado por el jugador.
     * @return Cantidad de oro recolectado
     */
    public int getGoldCount() { return goldCount; }

    /**
     * Verifica si el jugador sigue vivo en el juego.
     * @return true si el jugador está vivo
     */
    public boolean isAlive() { return isAlive; }

    /**
     * Incrementa el contador de oro del jugador
     */
    public void incrementGoldCount() { goldCount++; }

    /**
     * Marca al jugador como eliminado cuando encuentra una mina
     */
    public void die() { 
        isAlive = false;
        System.out.println(name + " ha encontrado una mina y ha sido eliminado!");
    }
} 