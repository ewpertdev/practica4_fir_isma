package ej2;

/**
 * Representa una posición en el terreno mediante coordenadas (x,y).
 * Implementa equals() y hashCode() para poder ser usada como clave en un Map.
 */
public class Position {
    private final int x;
    private final int y;

    /**
     * Constructor que inicializa una posición con coordenadas específicas.
     * 
     * @param x Coordenada x
     * @param y Coordenada y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene la coordenada x.
     * 
     * @return Coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada y.
     * 
     * @return Coordenada y
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
} 