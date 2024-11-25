package SistemaBancario_Fir_Ismael.servicios;

import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.err.println("Error: Cliente no encontrado en transferencia " + transferencia);
            return;
        }
        
        double monto = transferencia.getMonto();
        synchronized (origen) {
            synchronized (destino) {
                origen.setSaldo(origen.getSaldo() - monto);
                destino.setSaldo(destino.getSaldo() + monto);
                System.out.printf("Transferencia realizada: %s -> %s: %.2f€%n", 
                    origen.getId(), destino.getId(), monto);
            }
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
} 