package ej3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa un cliente del sistema bancario.
 * Contiene la información básica del cliente y su saldo.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Client {
    private String id;
    private String nombre;
    private double saldo;
    private String numeroCuenta;
    private String direccion;

    /**
     * Constructor por defecto necesario para Jackson.
     */
    public Client() {
        // Constructor vacío para Jackson
    }

    /**
     * Constructor con todos los campos.
     *
     * @param id Identificador del cliente
     * @param nombre Nombre del cliente
     * @param saldo Saldo actual del cliente
     * @param numeroCuenta Número de cuenta bancaria
     * @param direccion Dirección del cliente
     */
    public Client(String id, String nombre, double saldo, String numeroCuenta, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.direccion = direccion;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getDireccion() { return direccion; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    /**
     * Actualiza el saldo del cliente de forma sincronizada.
     * 
     * @param cantidad Cantidad a añadir (positiva) o restar (negativa)
     */
    public synchronized void actualizarSaldo(double cantidad) {
        this.saldo += cantidad;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
} 