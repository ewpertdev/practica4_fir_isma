package ej2;

/**
 * Representa una celda individual en el terreno del juego.
 * Cada celda puede contener un jugador, una pepita de oro, una mina o estar vacía.
 */
public class Cell {
    private CellContent content;

    /**
     * Constructor que inicializa una celda vacía.
     */
    public Cell() {
        this.content = CellContent.EMPTY;
    }

    /**
     * Establece el contenido de la celda.
     * 
     * @param content Nuevo contenido para la celda
     */
    public void setContent(CellContent content) {
        this.content = content;
    }

    /**
     * Obtiene el contenido actual de la celda.
     * 
     * @return Contenido actual de la celda
     */
    public CellContent getContent() {
        return content;
    }
} 