package ej3.model;

/**
 * Representa una transferencia bancaria entre dos clientes.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Transfer {
    private String origen;
    private String destino;
    private double monto;

    /**
     * Constructor por defecto necesario para Jackson.
     */
    public Transfer() {
        // Constructor vacío para Jackson
    }
} 