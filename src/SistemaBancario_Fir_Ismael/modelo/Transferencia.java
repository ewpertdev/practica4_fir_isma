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

    /**
     * Constructor por defecto necesario para Jackson.
     */
    public Transferencia() {}

    /**
     * Constructor que crea una nueva transferencia.
     * @param origen ID del cliente origen
     * @param destino ID del cliente destino
     * @param monto Cantidad a transferir
     */
    @JsonCreator
    public Transferencia(
        @JsonProperty("origen") String origen,
        @JsonProperty("destino") String destino,
        @JsonProperty("monto") double monto) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    /**
     * Obtiene el ID del cliente origen.
     * @return ID del cliente que realiza la transferencia
     */
    @JsonProperty("origen")
    public String getOrigen() { return origen; }

    /**
     * Obtiene el ID del cliente destino.
     * @return ID del cliente que recibe la transferencia
     */
    @JsonProperty("destino")
    public String getDestino() { return destino; }

    /**
     * Obtiene el monto de la transferencia.
     * @return Cantidad a transferir
     */
    @JsonProperty("monto")
    public double getMonto() { return monto; }

    /**
     * Establece el ID del cliente origen.
     * @param origen ID del cliente que realiza la transferencia
     */
    public void setOrigen(String origen) { this.origen = origen; }

    /**
     * Establece el ID del cliente destino.
     * @param destino ID del cliente que recibe la transferencia
     */
    public void setDestino(String destino) { this.destino = destino; }

    /**
     * Establece el monto de la transferencia.
     * @param monto Cantidad a transferir
     */
    public void setMonto(double monto) { this.monto = monto; }

    @Override
    public String toString() {
        return String.format("Transferencia{%s -> %s: %.2f}", 
                           origen, destino, monto);
    }
} 