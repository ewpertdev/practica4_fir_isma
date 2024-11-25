package SistemaBancario_Fir_Ismael;

import SistemaBancario_Fir_Ismael.servicios.GestorClientes;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GestorClientes gestor = new GestorClientes();
            
            // Cargar clientes
            gestor.cargarClientes();
            
            // TODO: Procesar transferencias de forma concurrente
            // Por ahora, solo mostramos los clientes cargados
            for (int i = 1; i <= 6; i++) {
                System.out.println(gestor.getCliente("Cliente" + i));
            }
            
        } catch (IOException e) {
            System.err.println("Error al procesar archivos: " + e.getMessage());
        }
    }
}
