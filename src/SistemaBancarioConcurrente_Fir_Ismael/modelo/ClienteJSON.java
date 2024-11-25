package SistemaBancarioConcurrente_Fir_Ismael.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteJSON {
    @JsonProperty("id")
    private String id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("saldo")
    private double saldo;
    @JsonProperty("numeroCuenta")
    private String numeroCuenta;
    @JsonProperty("direccion")
    private String direccion;

    // Constructor por defecto necesario para Jackson
    public ClienteJSON() {}

    // Constructor completo
    public ClienteJSON(String id, String nombre, double saldo, String numeroCuenta, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.direccion = direccion;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}