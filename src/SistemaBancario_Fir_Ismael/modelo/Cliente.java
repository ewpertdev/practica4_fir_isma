package SistemaBancario_Fir_Ismael.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * Clase que representa un cliente del sistema bancario.
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {
    private String id;
    private String nombre;
    private double saldo;
    private String numeroCuenta;
    private String direccion;

    // Constructor por defecto para Jackson
    public Cliente() {}

    @JsonProperty("id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    @JsonProperty("nombre")
    @JsonAlias({"Nombre"})
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    @JsonProperty("saldo")
    public synchronized double getSaldo() { return saldo; }
    public synchronized void setSaldo(double saldo) { this.saldo = saldo; }
    
    @JsonProperty("numeroCuenta")
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    
    @JsonProperty("direccion")
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    @Override
    public String toString() {
        return String.format("Cliente{id='%s', nombre='%s', saldo=%.2f, numeroCuenta='%s', direccion='%s'}", 
                           id, nombre, saldo, numeroCuenta, direccion);
    }

    /**
     * Realiza una transferencia a otro cliente.
     * @param destino Cliente que recibirá la transferencia
     * @param monto Cantidad a transferir
     * @return true si la transferencia se realizó con éxito, false en caso contrario
     */
    public synchronized boolean realizarTransferencia(Cliente destino, double monto) {
        // Validar que el monto sea positivo
        if (monto <= 0) {
            System.out.println("Error: El monto debe ser positivo");
            return false;
        }

        // Validar que haya saldo suficiente
        if (this.saldo < monto) {
            System.out.println("Error: Saldo insuficiente para la transferencia de " + 
                             this.id + " a " + destino.getId() + 
                             " por " + String.format("%.2f€", monto));
            return false;
        }

        // Realizar la transferencia
        this.saldo -= monto;
        destino.recibirTransferencia(monto);
        
        System.out.println("Transferencia realizada: " + this.id + " -> " + 
                          destino.getId() + ": " + String.format("%.2f€", monto));
        return true;
    }

    /**
     * Método privado para recibir una transferencia.
     * @param monto Cantidad recibida
     */
    private synchronized void recibirTransferencia(double monto) {
        this.saldo += monto;
    }
} 