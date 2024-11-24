public class Cliente {
    private String id;
    private String nombre;
    private double saldo;

    public Cliente(String id, String nombre, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    // Getters y setters
    public String getId() { return id; }
    public double getSaldo() { return saldo; }
    
    public synchronized void actualizarSaldo(double cantidad) {
        this.saldo += cantidad;
    }
} 