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
        // Carga los 6 clientes
        for (int i = 1; i <= 6; i++) {
            Cliente cliente = GestorJSON.leerCliente("data/Cliente" + i + ".json");
            clientes.put(cliente.getId(), cliente);
        }
    }
    
    public void procesarTransferencia(Transferencia transferencia) {
        Cliente origen = clientes.get(transferencia.getOrigen());
        Cliente destino = clientes.get(transferencia.getDestino());
        
        if (origen != null && destino != null) {
            double monto = transferencia.getMonto();
            origen.setSaldo(origen.getSaldo() - monto);
            destino.setSaldo(destino.getSaldo() + monto);
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