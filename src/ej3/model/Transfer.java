package ej3.model;

/**
 * Representa una transferencia bancaria entre dos clientes.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Transfer {
    private String origen;
    private String destino;
    private double monto;

    /**
     * Constructor por defecto necesario para Jackson.
     */
    public Transfer() {
        // Constructor vacío para Jackson
    }

    /**
     * Constructor con todos los campos.
     *
     * @param origen ID del cliente que envía el dinero
     * @param destino ID del cliente que recibe el dinero
     * @param monto Cantidad de dinero a transferir
     */
    public Transfer(String origen, String destino, double monto) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    // Getters
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public double getMonto() { return monto; }

    // Setters
    public void setOrigen(String origen) { this.origen = origen; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setMonto(double monto) { this.monto = monto; }

    /**
     * Valida si la transferencia es válida.
     * Una transferencia es válida si el monto es positivo y los IDs no son nulos.
     *
     * @return true si la transferencia es válida
     */
    public boolean esValida() {
        return monto > 0 && origen != null && destino != null;
    }

    @Override
    public String toString() {
        return "Transferencia{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", monto=" + monto +
                '}';
    }
} 