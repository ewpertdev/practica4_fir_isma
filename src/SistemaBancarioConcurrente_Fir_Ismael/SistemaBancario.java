package SistemaBancarioConcurrente_Fir_Ismael;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SistemaBancario {
    private Map<String, Cliente> clientes = new HashMap<>();

    public SistemaBancario() {
        // Inicializar algunos clientes de ejemplo
        clientes.put("Cliente1", new Cliente("Cliente1", "Juan", 1000.0));
        clientes.put("Cliente2", new Cliente("Cliente2", "Ana", 2000.0));
        clientes.put("Cliente3", new Cliente("Cliente3", "Pedro", 1500.0));
    }

    public void procesarTransferencia(Transferencia transferencia) {
        Cliente origen = clientes.get(transferencia.getOrigen());
        Cliente destino = clientes.get(transferencia.getDestino());
        
        if (origen != null && destino != null) {
            origen.actualizarSaldo(-transferencia.getMonto());
            destino.actualizarSaldo(transferencia.getMonto());
        }
    }
}