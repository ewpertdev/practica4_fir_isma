package SistemaBancario_Fir_Ismael.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa una transferencia entre dos clientes.
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Transferencia {
    private String origen;
    private String destino;
    private double monto;

    // Constructor por defecto para Jackson
    public Transferencia() {}

    // Constructor con parámetros
    @JsonCreator
    public Transferencia(
        @JsonProperty("origen") String origen,
        @JsonProperty("destino") String destino,
        @JsonProperty("monto") double monto) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    // Getters
    @JsonProperty("origen")
    public String getOrigen() { return origen; }

    @JsonProperty("destino")
    public String getDestino() { return destino; }

    @JsonProperty("monto")
    public double getMonto() { return monto; }

    // Setters para el constructor por defecto
    public void setOrigen(String origen) { this.origen = origen; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setMonto(double monto) { this.monto = monto; }

    @Override
    public String toString() {
        return String.format("Transferencia{%s -> %s: %.2f}", 
                           origen, destino, monto);
    }
} 