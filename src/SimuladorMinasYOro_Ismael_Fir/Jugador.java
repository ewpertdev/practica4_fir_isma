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
