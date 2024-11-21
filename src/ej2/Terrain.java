package ej2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa el terreno del juego donde se desarrolla la búsqueda del tesoro.
 * Utiliza un ConcurrentHashMap para manejar accesos concurrentes de manera segura.
 */
public class Terrain {
    private final int size;
    private final Map<Position, Cell> grid;
    private final AtomicInteger goldCount;
    private final AtomicInteger mineCount;
    private final AtomicInteger alivePlayers;
    private final Random random;

    /**
     * Constructor que inicializa el terreno con un tamaño específico.
     *
     * @param size Tamaño del terreno (cuadrado size x size)
     * @param numPlayers Número de jugadores para calcular minas y pepitas
     */
    public Terrain(int size, int numPlayers) {
        this.size = size;
        this.grid = new ConcurrentHashMap<>();
        this.goldCount = new AtomicInteger(numPlayers * 3); // Triple de pepitas que jugadores
        this.mineCount = new AtomicInteger(numPlayers / 2); // Mitad de minas que jugadores
        this.alivePlayers = new AtomicInteger(numPlayers);
        this.random = new Random();
        initializeTerrain();
        distributeItems();
    }

    private void initializeTerrain() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.put(new Position(i, j), new Cell());
            }
        }
    }

    private void distributeItems() {
        // Distribuir pepitas
        for (int i = 0; i < goldCount.get(); i++) {
            placeRandomItem(CellContent.ORE);
        }
        
        // Distribuir minas
        for (int i = 0; i < mineCount.get(); i++) {
            placeRandomItem(CellContent.MINE);
        }
    }

    private void placeRandomItem(CellContent content) {
        Position pos;
        do {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            pos = new Position(x, y);
        } while (!isCellEmpty(pos));
        
        getCell(pos).setContent(content);
    }

    /**
     * Intenta mover un jugador a una nueva posición.
     *
     * @param oldPos Posición actual del jugador
     * @param newPos Nueva posición del jugador
     * @param player Jugador que se mueve
     * @return true si el movimiento fue exitoso
     */
    public synchronized boolean movePlayer(Position oldPos, Position newPos, Player player) {
        if (!isValidPosition(newPos)) return false;

        Cell newCell = getCell(newPos);
        CellContent content = newCell.getContent();

        // Procesar el contenido de la nueva celda
        switch (content) {
            case EMPTY:
                updatePlayerPosition(oldPos, newPos);
                return true;
            case ORE:
                collectGold(player, newPos);
                updatePlayerPosition(oldPos, newPos);
                return true;
            case MINE:
                handleMine(player, newPos);
                getCell(oldPos).setContent(CellContent.EMPTY);
                return true;
            default:
                return false;
        }
    }

    private void updatePlayerPosition(Position oldPos, Position newPos) {
        getCell(oldPos).setContent(CellContent.EMPTY);
        getCell(newPos).setContent(CellContent.PLAYER);
    }

    private void collectGold(Player player, Position pos) {
        player.incrementGoldCount();
        goldCount.decrementAndGet();
        System.out.println(player.getName() + " encontró una pepita de oro!");
    }

    private void handleMine(Player player, Position pos) {
        player.die();
        mineCount.decrementAndGet();
        alivePlayers.decrementAndGet();
        getCell(pos).setContent(CellContent.EMPTY);
    }

    // Métodos de acceso y utilidad
    public Cell getCell(Position position) {
        return grid.get(position);
    }

    public int getSize() {
        return size;
    }

    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < size &&
               position.getY() >= 0 && position.getY() < size;
    }

    public boolean isCellEmpty(Position pos) {
        return getCell(pos).getContent() == CellContent.EMPTY;
    }

    public void setContent(Position pos, CellContent content) {
        getCell(pos).setContent(content);
    }

    public boolean hasGoldNuggets() {
        return goldCount.get() > 0;
    }

    public boolean hasAlivePlayers() {
        return alivePlayers.get() > 0;
    }

    /**
     * Imprime el estado actual del terreno.
     */
    public void printTerrain() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                CellContent content = getCell(new Position(i, j)).getContent();
                char symbol = switch (content) {
                    case EMPTY -> '.';
                    case ORE -> 'O';
                    case MINE -> 'M';
                    case PLAYER -> 'P';
                };
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }
} 