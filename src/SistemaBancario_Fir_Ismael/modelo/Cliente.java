package SistemaBancario_Fir_Ismael.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    @JsonProperty("saldo")
    public synchronized double getSaldo() { return saldo; }
    public synchronized void setSaldo(double saldo) { this.saldo = saldo; }
    
    @Override
    public String toString() {
        return String.format("Cliente{id='%s', nombre='%s', saldo=%.2f}", 
                           id, nombre, saldo);
    }
} 