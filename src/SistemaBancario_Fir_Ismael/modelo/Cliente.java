package SistemaBancario_Fir_Ismael.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

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
} 