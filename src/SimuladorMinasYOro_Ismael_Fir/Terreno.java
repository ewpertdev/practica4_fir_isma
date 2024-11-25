package SimuladorMinasYOro_Ismael_Fir;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Clase que representa el terreno de juego donde se desarrolla la partida.
 * Contiene la lógica para gestionar el estado del juego, el movimiento de los jugadores,
 * y la distribución de oro y minas.
 * 
 * @author Ismael Lozano
 * @author Mohd Firdaus Bin Abdullah
 */
public class Terreno {
    private final char[][] cuadrado;          // Representación del terreno
    private final int tamanio;               // Tamaño del terreno
    private final Random aleatorio;          // Generador de posiciones aleatorias
    private final AtomicBoolean juegoTerminado; // Bandera para indicar si el juego terminó

    /**
     * Constructor que crea un nuevo terreno de juego.
     * 
     * @param tamanio Dimensión del terreno (terreno cuadrado de tamanio x tamanio)
     */
    public Terreno(int tamanio) {
        this.tamanio = tamanio;
        this.cuadrado = new char[tamanio][tamanio];
        this.aleatorio = new Random();
        this.juegoTerminado = new AtomicBoolean(false); // Inicializamos el juego como no terminado
    }
        /**
     * Inicializa el terreno distribuyendo oro (O), minas (M) y celdas vacías (.).
     */
    private void inicializarTerreno() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                int rand = aleatorio.nextInt(5); // Probabilidad de contenido
                if (rand == 0) {
                    cuadrado[i][j] = 'O'; // Oro
                } else if (rand == 1) {
                    cuadrado[i][j] = 'M'; // Mina
                } else {
                    cuadrado[i][j] = '.'; // Celda vacía
                }
            }
        }
    }
    /**
     * Mueve a un jugador a una posición del terreno.
     * Si encuentra oro o pisa una mina, termina el juego.
     *
     * @param x        Coordenada X del movimiento
     * @param y        Coordenada Y del movimiento
     * @param idJugador ID del jugador que se mueve
     * @return true si el movimiento termina el juego, false de lo contrario
     */
    public synchronized boolean moverJugador(int x, int y, int idJugador) {
        // Si el juego ya terminó, no permitimos más movimientos
        if (juegoTerminado.get()) {
            return false;
        }

        char contenido = cuadrado[x][y]; // Obtenemos el contenido de la celda
        if (contenido == 'O') {
            System.out.println("Jugador " + idJugador + " encontró ORO en (" + x + ", " + y + ") y ganó el juego!");
            cuadrado[x][y] = 'P'; // Marcamos la posición con el jugador
            juegoTerminado.set(true); // Marcamos el juego como terminado
            return true;
        } else if (contenido == 'M') {
            System.out.println("Jugador " + idJugador + " encontró una MINA en (" + x + ", " + y + ") y perdió el juego!");
            cuadrado[x][y] = 'P'; // Marcamos la posición con el jugador
            juegoTerminado.set(true); // Marcamos el juego como terminado
            return true;
        } else {
            System.out.println("Jugador " + idJugador + " no encontró nada en (" + x + ", " + y + ")");
            cuadrado[x][y] = 'P'; // Marcamos la posición del jugador
            return false;
        }
    }
    /**
     * Verifica si el juego ha terminado.
     *
     * @return true si el juego ha terminado, false de lo contrario
     */
    public boolean juegoTerminado() {
        return juegoTerminado.get();
    }
    /**
     * Imprime el estado actual del terreno.
     */
    public void imprimirTerreno() {
        System.out.println("\nEstado actual del terreno:");
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                System.out.print(cuadrado[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Verifica si una posición está dentro de los límites del terreno.
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return true si está dentro de los límites, false de lo contrario
     */
    public boolean posicionValida(int x, int y) {
        return x >= 0 && x < tamanio && y >= 0 && y < tamanio;
    }
    /**
     * Obtiene el tamaño del terreno.
     * 
     * @return El tamaño del terreno (número de casillas por lado)
     */
    public int getTamanio() {
        return tamanio;
    }

}
