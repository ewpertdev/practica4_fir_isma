package ej2;

// La clase CellContent representa el contenido de una celda en el terreno, es decir, 
// el tipo de espacio que hay en un espacio del tablero.
// Para crear la clase CellContent, primero se crea un enum que representa el contenido de una celda.
// Este enum tiene cuatro valores: EMPTY, ORE, MINE y PLAYER. Un enum en JAVA es una lista de constantes.
public enum CellContent {
    EMPTY,
    ORE,
    MINE,
    PLAYER
} 