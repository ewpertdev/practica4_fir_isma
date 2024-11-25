package SistemaBancario_Fir_Ismael;

import SistemaBancario_Fir_Ismael.servicios.ServicioTransferencias;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ServicioTransferencias servicio = new ServicioTransferencias();
            servicio.cargarClientes();
            
            // Nombres conocidos de archivos de transferencias
            String[] archivosTransferencias = {
                "data/Transferencias1.json",
                "data/transferencias10.json",
                "data/transferencias5.json",
                "data/transferencias6.json",
                "data/transferencias7.json"
            };
            
            // Procesar cada archivo de transferencias
            for (String archivo : archivosTransferencias) {
                try {
                    System.out.println("\nProcesando archivo: " + archivo);
                    servicio.procesarArchivoTransferencias(archivo);
                } catch (IOException e) {
                    System.err.println("Error al procesar archivo " + archivo + ": " + e.getMessage());
                }
            }
            
            // Mostrar estado final
            System.out.println("\nEstado final de los clientes:");
            servicio.getClientes().values().forEach(System.out::println);
            
        } catch (IOException e) {
            System.err.println("Error al cargar los clientes: " + e.getMessage());
        }
    }
}
