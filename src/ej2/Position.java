package ej2;

// Creamos una clase que representa una posición en el terreno.
public class Position {
    // Creamos un atributo privado final que representa la coordenada x.
    private final int x;
    // Creamos un atributo privado final que representa la coordenada y.
    private final int y;

    // Creamos un constructor que inicializa la posición.
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Creamos un método público que permite obtener la coordenada x.
    public int getX() {
        return x;
    }

    // Creamos un método público que permite obtener la coordenada y.
    public int getY() {
        return y;
    }

    // Creamos un método que permite comparar dos posiciones.
    @Override
    // El método equals lleva el parametro Object obj porque se quiere comparar con cualquier objeto.
    // Es un método booleano, es decir, devuelve true o false para indicar si las posiciones son iguales o no, 
    // porque se quiere saber si dos posiciones son iguales.
    public boolean equals(Object obj) {
        // Si las posiciones son iguales, se devuelve true.
        // this es la posición actual y obj es la posición que se quiere comparar.
        if (this == obj) return true;
        // Si el objeto es nulo o no es una instancia de la clase Position, se devuelve false.
        if (obj == null || getClass() != obj.getClass()) return false;
        // Se crea una posición que es igual al objeto.
        Position position = (Position) obj;
        // Se devuelve true si las posiciones son iguales.
        return x == position.x && y == position.y;
    }

    // Creamos un método que permite obtener el hashcode de una posición.
    @Override
    public int hashCode() {
        // Se devuelve el hashcode de la posición.
        // Es return 31 * x + y porque se multiplica por 31 para que el hashcode sea más único. 
        // Hemos cogido 31 porque es un número primo, podria ser cualquier número. 
        // Si no fuera primo, dos posiciones distintas podrían tener el mismo hashcode.
        return 31 * x + y;
    }
} 