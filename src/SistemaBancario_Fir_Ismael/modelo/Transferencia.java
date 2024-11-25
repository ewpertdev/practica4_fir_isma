package SistemaBancario_Fir_Ismael.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transferencia {
    private final String origen;
    private final String destino;
    private final double monto;

    @JsonCreator
    public Transferencia(
        @JsonProperty("origen") String origen,
        @JsonProperty("destino") String destino,
        @JsonProperty("monto") double monto) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public double getMonto() { return monto; }

    @Override
    public String toString() {
        return String.format("Transferencia{%s -> %s: %.2f}", 
                           origen, destino, monto);
    }
} 