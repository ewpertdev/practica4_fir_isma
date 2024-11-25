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

    /**
     * Constructor por defecto necesario para Jackson.
     */
    public Cliente() {}

    /**
     * Obtiene el ID del cliente.
     * @return ID único del cliente
     */
    @JsonProperty("id")
    public String getId() { return id; }

    /**
     * Establece el ID del cliente.
     * @param id ID único del cliente
     */
    public void setId(String id) { this.id = id; }
    
    /**
     * Obtiene el nombre del cliente.
     * @return Nombre del cliente
     */
    @JsonProperty("nombre")
    @JsonAlias({"Nombre"})
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del cliente.
     * @param nombre Nombre completo del cliente
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    /**
     * Obtiene el saldo actual de la cuenta.
     * @return Saldo actual
     */
    @JsonProperty("saldo")
    public synchronized double getSaldo() { return saldo; }

    /**
     * Establece el saldo de la cuenta del cliente.
     * @param saldo Nuevo saldo de la cuenta
     */
    public synchronized void setSaldo(double saldo) { this.saldo = saldo; }
    
    /**
     * Obtiene el número de cuenta del cliente.
     * @return Número de cuenta bancaria
     */
    @JsonProperty("numeroCuenta")
    public String getNumeroCuenta() { return numeroCuenta; }

    /**
     * Establece el número de cuenta del cliente.
     * @param numeroCuenta Nuevo número de cuenta bancaria
     */
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    
    /**
     * Obtiene la dirección del cliente.
     * @return Dirección postal del cliente
     */
    @JsonProperty("direccion")
    public String getDireccion() { return direccion; }

    /**
     * Establece la dirección postal del cliente.
     * @param direccion Nueva dirección postal del cliente
     */
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