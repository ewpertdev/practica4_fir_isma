package ej2;

/*La clase Cell representa una celda en el terreno, es decir, un espacio en el tablero.
 * Para crear la clase Cell, primero se crea un constructor que inicializa el contenido de la celda en EMPTY.
 * Luego, se crea un método setContent que permite cambiar el contenido de la celda.
 * Por último, se crea un método getContent que permite obtener el contenido de la celda.
*/

public class Cell {
    // Primero, creamos un atributo privado que es el contenido de la celda. 
    // Un atributo privado es aquel que solo se puede acceder desde la clase, en este caso, desde la clase Cell.
    private CellContent content;
// Creamos un constructor que inicializa el contenido de la celda en EMPTY, para que al crear una celda, esta no tenga contenido.
// Le ponemos .EMPTY para que sepa que es un espacio vacío.
    public Cell() {
        this.content = CellContent.EMPTY;
    }
// Creamos un método setContent que permite cambiar el contenido de la celda.
// Recordar que los setters son los que modifican los atributos de la clase.
    public void setContent(CellContent content) {
        this.content = content;
    }
// Creamos un método getContent que permite obtener el contenido de la celda.
// Este método no recibe ningún parámetro y devuelve el contenido de la celda.
    public CellContent getContent() {
        return content;
    }
} 