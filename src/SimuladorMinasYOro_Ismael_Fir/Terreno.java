package juego;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Terreno {
    private final char[][] cuadrado;    // Matriz que representa el terreno
    private final int tamanio;         // Tamaño del terreno (siempre cuadrado)
    private final AtomicBoolean juegoTerminado; // Indica si el juego ha terminado

    public Terreno(int tamanio) {
        this.tamanio = tamanio;
        this.cuadrado = new char[tamanio][tamanio];
        this.juegoTerminado = new AtomicBoolean(false); // Inicialmente no terminado
    }
}
