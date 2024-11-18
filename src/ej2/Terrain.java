package ej2;

import java.util.HashMap;
import java.util.Map;

// TODO: faltaria añadir algunos métodos de utilidad al Terrain

// La clase Terrain representa el terreno del juego, es decir, el tablero.
public class Terrain {
    // Primero, creamos un atributo privado que es el tamaño del terreno.
    private final int size;
    // Luego, creamos un atributo privado que es la cuadrícula del terreno.
    private final Map<Position, Cell> grid;

    // Creamos un constructor que inicializa el terreno.
    public Terrain(int size) {
        this.size = size;
        this.grid = new HashMap<>();
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
        for (int i = 0; i < size; i++) {
            // Se crea un bucle for que recorre todas las columnas de la cuadrícula.
            // j representa las columnas.
            // Si j es menor que size, siendo size el tamaño del terreno, se crea una celda en la posición i,j.
            for (int j = 0; j < size; j++) {
                // Se crea una celda en la posición i,j despues de recorrer todas las columnas y filas.
                grid.put(new Position(i, j), new Cell());
            }
        }
    }

    // Creamos un método público que permite obtener una celda de la cuadrícula.
    // No es public void ya que no modifica ningún atributo de la clase.
    public Cell getCell(Position position) {
        return grid.get(position);
    }

    // Creamos un método público que permite obtener el tamaño del terreno.
    public int getSize() {
        return size;
    }

    // Creamos un método público que permite saber si una posición es válida.
    // Es un método booleano, es decir, devuelve true o false para indicar si la posición es válida o no, 
    // porque se quiere saber si una posición está dentro del terreno o no.
    public boolean isValidPosition(Position position) {
        // Se devuelve true si la posición es válida, es decir, si la posición está dentro del terreno.
        // La coordenada x de la posición tiene que ser mayor o igual que 0 y menor que el tamaño del terreno.
        return position.getX() >= 0 && position.getX() < size &&
                // La coordenada y de la posición tiene que ser mayor o igual que 0 y menor que el tamaño del terreno.
               position.getY() >= 0 && position.getY() < size;
               
               // Si cumple las dos condiciones, se devuelve true y por tanto la posición es válida.
               // Si no cumple las dos condiciones, se devuelve false y por tanto la posición no es válida.
    }
} 