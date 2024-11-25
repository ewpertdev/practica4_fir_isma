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

    // Getters y setters
} 