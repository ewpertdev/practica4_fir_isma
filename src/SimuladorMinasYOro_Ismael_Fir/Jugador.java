package SimuladorMinasYOro_Ismael_Fir;

import java.util.Random;

/**
 * Clase que representa un jugador en el juego de Minas y Oro.
 * Cada jugador se ejecuta en su propio hilo y busca oro en el terreno.
 * 
 * @author Ismael Lozano
 * @author Mohd Firdaus Bin Abdullah
 */
public class Jugador implements Runnable {
    private final int id;           // Identificador único del jugador
    private final Terreno terreno; // Referencia al terreno compartido
    private final Random aleatorio; // Generador de movimientos aleatorios

    /**
     * Constructor para crear un nuevo jugador.
     * 
     * @param id Identificador único del jugador
     * @param terreno Referencia al terreno compartido donde se desarrolla el juego
     */
    public Jugador(int id, Terreno terreno) {
        this.id = id;
        this.terreno = terreno;
        this.aleatorio = new Random();
        
    }

    /**
     * Implementación del método run() de la interfaz Runnable.
     * El jugador se mueve aleatoriamente por el terreno buscando oro y evitando minas.
     */
    @Override
    public void run() {
        // Mientras el juego no haya terminado, el jugador intenta moverse
        while (!terreno.juegoTerminado()) {
            int x = aleatorio.nextInt(terreno.getTamanio());
            int y = aleatorio.nextInt(terreno.getTamanio());

            // Solo intentamos movernos si la posición es válida
            if (terreno.posicionValida(x, y)) {
                boolean terminado = terreno.moverJugador(x, y, id); // Movimiento del jugador
                terreno.imprimirTerreno(); // Imprimimos el estado del terreno después del movimiento
                if (terminado) {
                    break; // Salimos si el juego ha terminado
                }
            }
            try {
                Thread.sleep(500); // Pausa para observar el juego
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            }
        }
    }
}
