package SistemaBancarioConcurrente_Fir_Ismael.modelo;

public class Transferencia {
    private String origen;
    private String destino;
    private double monto;

    // Constructor vacío para Jackson
    public Transferencia() {}

    public Transferencia(String origen, String destino, double monto) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    // Getters y setters
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
}
