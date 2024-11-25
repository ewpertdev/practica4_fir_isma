package SistemaBancario_Fir_Ismael;

import SistemaBancario_Fir_Ismael.servicios.ServicioTransferencias;
import SistemaBancario_Fir_Ismael.servicios.ProcesadorConcurrente;
import java.io.IOException;

/**
 * Clase principal que ejecuta el sistema bancario.
 * Gestiona la carga de clientes y el procesamiento de transferencias.
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class Main {
    /**
     * Constructor privado para evitar instanciación.
     * Esta clase solo contiene métodos estáticos.
     */
    private Main() {}

    /**
     * Método principal que inicia la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        try {
            // Inicializar servicios
            ServicioTransferencias servicioTransferencias = new ServicioTransferencias();
            ProcesadorConcurrente procesadorConcurrente = 
                new ProcesadorConcurrente(servicioTransferencias);

            // Cargar clientes
            servicioTransferencias.cargarClientes();
            
            // Definir archivos a procesar
            String[] archivosTransferencias = {
                "data/Transferencias1.json",
                "data/transferencias10.json",
                "data/transferencias5.json",
                "data/transferencias6.json",
                "data/transferencias7.json"
            };
            
            // Procesar archivos concurrentemente
            procesadorConcurrente.procesarArchivosConcurrentemente(archivosTransferencias);
            
            // Mostrar estadísticas
            System.out.println("\nEstadísticas:");
            System.out.println("Total de transferencias procesadas: " + 
                servicioTransferencias.getTransferenciasProcesadas());
            
            // Mostrar estado final
            System.out.println("\nEstado final de los clientes:");
            servicioTransferencias.getClientes().values().forEach(System.out::println);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
