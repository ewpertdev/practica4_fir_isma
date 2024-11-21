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

    // Getters y setters básicos - los completaremos en el siguiente commit
} 