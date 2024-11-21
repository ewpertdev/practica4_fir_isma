package ej3;

import ej3.model.Client;
import ej3.model.Transfer;
import ej3.util.JsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * Clase principal que ejecuta la simulación del sistema bancario.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Main {
    /** Ruta base para los archivos de datos */
    private static final String DATA_PATH = "src/ej3/data/";

    /**
     * Constructor por defecto.
     */
    public Main() {
        // Constructor vacío
    }

    /**
     * Punto de entrada principal del programa.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== Sistema Bancario ===");
        
        try {
            // Prueba lectura de un cliente
            Client cliente1 = JsonUtils.leerCliente(DATA_PATH + "Cliente1.json");
            System.out.println("\nCliente leído:");
            System.out.println(cliente1);

            // Prueba lectura de transferencias
            List<Transfer> transferencias = JsonUtils.leerTransferencias(DATA_PATH + "Transferencias1.json");
            System.out.println("\nTransferencias leídas:");
            transferencias.forEach(System.out::println);

            // Prueba escritura de cliente (en un archivo temporal)
            String tempFile = DATA_PATH + "temp_cliente.json";
            JsonUtils.guardarCliente(cliente1, tempFile);
            System.out.println("\nCliente guardado en: " + tempFile);

        } catch (IOException e) {
            System.err.println("Error al procesar archivos JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 