package SistemaBancario_Fir_Ismael.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase utilitaria para gestionar la lectura de archivos JSON.
 * Proporciona métodos estáticos para la deserialización de objetos.
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class GestorJSON {
    /** Mapper de Jackson para la serialización/deserialización JSON */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor privado para evitar instanciación.
     * Esta clase solo contiene métodos estáticos.
     */
    private GestorJSON() {}

    /**
     * Lee un cliente desde un archivo JSON.
     * @param rutaArchivo Ruta del archivo a leer
     * @return Cliente leído del archivo
     * @throws IOException Si hay error al leer el archivo
     */
    public static Cliente leerCliente(String rutaArchivo) throws IOException {
        return mapper.readValue(new File(rutaArchivo), Cliente.class);
    }

    /**
     * Lee una lista de transferencias desde un archivo JSON.
     * @param rutaArchivo Ruta del archivo a leer
     * @return Lista de transferencias leídas
     * @throws IOException Si hay error al leer el archivo
     */
    public static List<Transferencia> leerTransferencias(String rutaArchivo) throws IOException {
        return mapper.readValue(
            new File(rutaArchivo), 
            new TypeReference<List<Transferencia>>() {}
        );
    }
} 