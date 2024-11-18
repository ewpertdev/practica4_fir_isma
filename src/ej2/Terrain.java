package ej2;

// La clase Terrain representa el terreno del juego, es decir, el tablero.
public class Terrain {
    // Primero, creamos un atributo privado que es el tamaño del terreno.
    private final int size;
    // Luego, creamos un atributo privado que es la cuadrícula del terreno.
    private Cell[][] grid;

    // Creamos un constructor que inicializa el terreno.
    public Terrain(int size) {
        this.size = size;
        initializeTerrain();
    }

    // Creamos un método privado que inicializa el terreno.
    private void initializeTerrain() {
        // grid significa cuadrícula.
        // Creamos la cuadrícula del terreno, es decir, un array bidimensional de celdas.
        // es grid = new Cell[size][size]; porque el terreno es cuadrado, es decir, tiene el mismo número de filas y columnas.
        // Por qué se pone [size][size] y no [size][size] + 1?
        // Porque el terreno es cuadrado, es decir, tiene el mismo número de filas y columnas, y la cuadrícula comienza desde 0.
        // Se pasa size en el parentesis porque size es el tamaño del terreno, es decir, el número de filas y columnas que tiene el terreno.
        grid = new Cell[size][size];
        // Se crea un bucle for que recorre todas las filas y columnas de la cuadrícula.
        // i representa las filas y j representa las columnas.
        // Si i es menor que size, siendo size el tamaño del terreno, se crea una celda en la posición i,j.
        for (int i = 0; i < size; i++) {
            // Se crea un bucle for que recorre todas las columnas de la cuadrícula.
            // j representa las columnas.
            // Si j es menor que size, siendo size el tamaño del terreno, se crea una celda en la posición i,j.
            for (int j = 0; j < size; j++) {
                // Se crea una celda en la posición i,j despues de recorrer todas las columnas y filas.
                grid[i][j] = new Cell();
            }
        }
    }

    // Creamos un método público que permite obtener una celda de la cuadrícula.
    // No es public void ya que no modifica ningún atributo de la clase.
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    // Creamos un método público que permite obtener el tamaño del terreno.
    public int getSize() {
        return size;
    }
} 