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
}
