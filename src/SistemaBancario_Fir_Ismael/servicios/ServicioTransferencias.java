package SistemaBancario_Fir_Ismael.servicios;

import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio que gestiona las transferencias entre clientes del sistema bancario.
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class ServicioTransferencias {
    /** Mapa que almacena los clientes por su ID */
    private Map<String, Cliente> clientes;
    
    /**
     * Constructor que inicializa el mapa de clientes.
     */
    public ServicioTransferencias() {
        this.clientes = new HashMap<>();
    }
    
    /**
     * Carga los clientes desde archivos JSON.
     * @throws IOException Si hay error al leer los archivos
     */
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
    
    /**
     * Procesa una transferencia individual entre dos clientes.
     * @param transferencia La transferencia a procesar
     */
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
    
    /**
     * Procesa un archivo que contiene múltiples transferencias.
     * @param rutaArchivo Ruta del archivo JSON con las transferencias
     * @throws IOException Si hay error al leer el archivo
     */
    public void procesarArchivoTransferencias(String rutaArchivo) throws IOException {
        List<Transferencia> transferencias = GestorJSON.leerTransferencias(rutaArchivo);
        for (Transferencia transferencia : transferencias) {
            procesarTransferencia(transferencia);
        }
    }
    
    /**
     * Obtiene el mapa de clientes del sistema.
     * @return Mapa con todos los clientes registrados
     */
    public Map<String, Cliente> getClientes() {
        return clientes;
    }
} 