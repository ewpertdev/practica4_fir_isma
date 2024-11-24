package SimuladorMinasYOro_Ismael_Fir;

import java.util.Random;

public class Jugador implements Runnable {
    private final int id;           // Identificador único del jugador
    private final Terreno terreno; // Referencia al terreno compartido
    private final Random aleatorio; // Generador de movimientos aleatorios

    public Jugador(int id, Terreno terreno) {
        this.id = id;
        this.terreno = terreno;
        this.aleatorio = new Random();
        
    }
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
