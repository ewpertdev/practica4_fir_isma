package ej2;

// La clase Terrain representa el terreno del juego, es decir, el tablero.
public class Terrain {
    private final int size;
    private Cell[][] grid;

    public Terrain(int size) {
        this.size = size;
        initializeTerrain();
    }

    private void initializeTerrain() {
        grid = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }
} 