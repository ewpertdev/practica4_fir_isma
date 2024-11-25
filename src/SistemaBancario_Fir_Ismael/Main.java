package SistemaBancario_Fir_Ismael;

import SistemaBancario_Fir_Ismael.servicios.ServicioTransferencias;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ServicioTransferencias servicio = new ServicioTransferencias();
            servicio.cargarClientes();
            
            // Procesar cada archivo de transferencias
            for (int i = 1; i <= 10; i++) {
                String archivo = "data/transferencias" + i + ".json";
                try {
                    servicio.procesarArchivoTransferencias(archivo);
                } catch (IOException e) {
                    System.out.println("No se pudo procesar el archivo: " + archivo);
                }
            }
            
            // Imprimir resultados finales
            System.out.println("Estado final de los clientes:");
            servicio.getClientes().values().forEach(System.out::println);
            
        } catch (IOException e) {
            System.err.println("Error al cargar los clientes: " + e.getMessage());
        }
    }
}
