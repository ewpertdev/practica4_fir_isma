package ej2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa el terreno del juego donde se desarrolla la búsqueda del tesoro.
 * Utiliza un ConcurrentHashMap para manejar accesos concurrentes de manera segura.
 * El terreno contiene pepitas de oro, minas y jugadores distribuidos aleatoriamente.
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
        this.goldCount = new AtomicInteger(numPlayers * 3);
        this.mineCount = new AtomicInteger(numPlayers / 2);
        this.alivePlayers = new AtomicInteger(numPlayers);
        this.random = new Random();
        initializeTerrain();
        distributeItems();
    }

    /**
     * Inicializa el terreno con celdas vacías.
     */
    private void initializeTerrain() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.put(new Position(i, j), new Cell());
            }
        }
    }

    /**
     * Distribuye pepitas de oro y minas aleatoriamente en el terreno.
     */
    private void distributeItems() {
        for (int i = 0; i < goldCount.get(); i++) {
            placeRandomItem(CellContent.ORE);
        }
        
        for (int i = 0; i < mineCount.get(); i++) {
            placeRandomItem(CellContent.MINE);
        }
    }

    /**
     * Coloca un item (oro o mina) en una posición aleatoria vacía.
     * 
     * @param content Tipo de contenido a colocar
     */
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

    /**
     * Actualiza la posición del jugador en el terreno.
     */
    private void updatePlayerPosition(Position oldPos, Position newPos) {
        getCell(oldPos).setContent(CellContent.EMPTY);
        getCell(newPos).setContent(CellContent.PLAYER);
    }

    /**
     * Procesa la recolección de oro por parte de un jugador.
     */
    private void collectGold(Player player, Position pos) {
        player.incrementGoldCount();
        goldCount.decrementAndGet();
        System.out.println(player.getName() + " encontró una pepita de oro!");
    }

    /**
     * Procesa la eliminación de un jugador por una mina.
     */
    private void handleMine(Player player, Position pos) {
        player.die();
        mineCount.decrementAndGet();
        alivePlayers.decrementAndGet();
        getCell(pos).setContent(CellContent.EMPTY);
    }

    // Métodos de acceso y utilidad
    /**
     * Obtiene la celda en la posición especificada.
     * 
     * @param position La posición de la celda a obtener
     * @return La celda en la posición especificada
     */
    public Cell getCell(Position position) {
        return grid.get(position);
    }

    /**
     * Obtiene el tamaño del terreno.
     * 
     * @return El tamaño del terreno (número de celdas por lado)
     */
    public int getSize() {
        return size;
    }

    /**
     * Verifica si una posición está dentro de los límites del terreno.
     * 
     * @param position La posición a verificar
     * @return true si la posición es válida dentro del terreno
     */
    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < size &&
               position.getY() >= 0 && position.getY() < size;
    }

    /**
     * Verifica si una celda está vacía.
     * 
     * @param pos La posición de la celda a verificar
     * @return true si la celda está vacía
     */
    public boolean isCellEmpty(Position pos) {
        return getCell(pos).getContent() == CellContent.EMPTY;
    }

    /**
     * Establece el contenido de una celda.
     * 
     * @param pos La posición de la celda
     * @param content El nuevo contenido a establecer
     */
    public void setContent(Position pos, CellContent content) {
        getCell(pos).setContent(content);
    }

    /**
     * Verifica si quedan pepitas de oro en el terreno.
     * 
     * @return true si hay pepitas de oro disponibles
     */
    public boolean hasGoldNuggets() {
        return goldCount.get() > 0;
    }

    /**
     * Verifica si quedan jugadores vivos en el juego.
     * 
     * @return true si hay al menos un jugador vivo
     */
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