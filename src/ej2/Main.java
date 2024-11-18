package ej2;

/*Requerimientos del programa:

1. Implementa la cuadrícula utilizando una estructura de datos de tipo HashMap (donde
la clave es la posición (x, y) y el valor es la entidad (vacío, pepita, mina, Jugador).
Puedes utilizar la que consideres mejor. Deberás justificar tu respuesta. Tal vez
necesites crear una clase Posicion para utilizarla de clave del HashMap, así como
comparar posiciones.

2. Implementa la clase Jugador. Puedes utilizar un enum para Pepita, y Mina.

3. Cada instancia de Jugador debe ejecutarse en su propio hilo que deberán
sincronizarse para ir haciendo los movimientos por turnos. No es posible que un
jugador haya hecho 3 movimientos mientras otro solo ha hecho 1 (Consulta
CyclingBarrier).

4. La simulación termina cuando se han acabado las pepitas (dando un mensaje de
quién ha sido el ganador) o cuando todos los jugadores han encontrado una mina
(dando un mensaje de que nadie ha ganado).

5. Extra: Valora la posibilidad de utilizar un pool de hilos para los jugadores. (Es más
difícil al principio pero mucho más cómodo a la larga)
 */
public class Main {
    // Creamos un atributo privado estático final que representa el tamaño del terreno.
    // es final porque no cambia.
    // es static porque es un atributo de la clase y no de los objetos.
    private static final int TERRAIN_SIZE = 15;
    // Creamos un atributo privado estático que representa el terreno.
    // es private porque solo se puede acceder a él desde la clase.
    // es static porque es un atributo de la clase y no de los objetos.
    private static Terrain terrain;

    public static void main(String[] args) {
        // Se crea el terreno.
        terrain = new Terrain(TERRAIN_SIZE);
        // Probamos la posición (0,0).
        Position testPos = new Position(0, 0);
        // Se obtiene la celda de la posición (0,0).
        Cell cell = terrain.getCell(testPos);
        // Se imprime el contenido de la celda de la posición (0,0).
        System.out.println("Cell at (0,0) is: " + cell.getContent());
    }
}
