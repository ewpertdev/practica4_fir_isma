package SistemaBancario_Fir_Ismael.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa una transferencia bancaria entre dos clientes.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 * @version 1.0
 */
public class Transferencia {
    /**
     * ID del cliente que envía el dinero
     */
    private String origen;
    
    /**
     * ID del cliente que recibe el dinero
     */
    private String destino;
    
    /**
     * Cantidad de dinero a transferir
     */
    private double cantidad;

    // Constructor por defecto para Jackson
    public Transferencia() {}

    // Constructor con parámetros
    @JsonCreator
    public Transferencia(
        @JsonProperty("origen") String origen,
        @JsonProperty("destino") String destino,
        @JsonProperty("cantidad") double cantidad) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    // Getters
    @JsonProperty("origen")
    public String getOrigen() { return origen; }

    @JsonProperty("destino")
    public String getDestino() { return destino; }

    @JsonProperty("cantidad")
    public double getCantidad() { return cantidad; }

    // Setters para el constructor por defecto
    public void setOrigen(String origen) { this.origen = origen; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return String.format("Transferencia{%s -> %s: %.2f}", 
                           origen, destino, cantidad);
    }
} 