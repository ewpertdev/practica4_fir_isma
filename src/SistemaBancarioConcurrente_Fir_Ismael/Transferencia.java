package SistemaBancarioConcurrente_Fir_Ismael;
public class Transferencia {
    private String origen;
    private String destino;
    private double monto;

    public Transferencia(String origen, String destino, double monto) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    // Getters
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public double getMonto() { return monto; }
}
