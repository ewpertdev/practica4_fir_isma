package SimuladorMinasYOro_Ismael_Fir;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Terreno {
    private final char[][] cuadrado;          // Representación del terreno
    private final int tamanio;               // Tamaño del terreno
    private final Random aleatorio;          // Generador de posiciones aleatorias
    private final AtomicBoolean juegoTerminado; // Bandera para indicar si el juego terminó

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

}
