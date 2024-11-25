package SistemaBancario_Fir_Ismael.servicios;

import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio que gestiona las operaciones de transferencias entre clientes.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 * @version 1.0
 */
public class ServicioTransferencias {
    private Map<String, Cliente> clientes;
    
    public ServicioTransferencias() {
        this.clientes = new HashMap<>();
    }
    
    public void cargarClientes() throws IOException {
        for (int i = 1; i <= 6; i++) {
            String rutaArchivo = "data/Cliente" + i + ".json";
            try {
                Cliente cliente = GestorJSON.leerCliente(rutaArchivo);
                clientes.put(cliente.getId(), cliente);
                System.out.println("Cliente cargado: " + cliente);
            } catch (IOException e) {
                System.err.println("Error al cargar cliente desde " + rutaArchivo + ": " + e.getMessage());
                throw e;
            }
        }
    }
    
    public void procesarTransferencia(Transferencia transferencia) {
        Cliente origen = clientes.get(transferencia.getOrigen());
        Cliente destino = clientes.get(transferencia.getDestino());
        
        if (origen == null || destino == null) {
            System.out.println("Error: Cliente no encontrado");
            return;
        }

        if (!origen.realizarTransferencia(destino, transferencia.getMonto())) {
            System.out.println("La transferencia no pudo ser procesada");
        }
    }
    
    public void procesarArchivoTransferencias(String rutaArchivo) throws IOException {
        List<Transferencia> transferencias = GestorJSON.leerTransferencias(rutaArchivo);
        for (Transferencia transferencia : transferencias) {
            procesarTransferencia(transferencia);
        }
    }
    
    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    /**
     * Realiza una transferencia entre dos clientes.
     * 
     * @param origen Cliente que envía el dinero
     * @param destino Cliente que recibe el dinero
     * @param cantidad Monto a transferir
     * @return true si la transferencia se realizó con éxito, false en caso contrario
     */
    public boolean realizarTransferencia(Cliente origen, Cliente destino, double cantidad) {
        // ... method implementation ...
    }
} 