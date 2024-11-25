package SistemaBancario_Fir_Ismael.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Gestiona la lectura y escritura de datos en formato JSON.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 * @version 1.0
 */
public class GestorJSON {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Lee los datos de un cliente desde un archivo JSON.
     * 
     * @param rutaArchivo Ruta del archivo JSON que contiene los datos del cliente
     * @return Cliente con los datos leídos
     * @throws IOException Si hay un error al leer el archivo
     */
    public static Cliente leerCliente(String rutaArchivo) throws IOException {
        return mapper.readValue(new File(rutaArchivo), Cliente.class);
    }

    public static List<Transferencia> leerTransferencias(String rutaArchivo) throws IOException {
        return mapper.readValue(
            new File(rutaArchivo), 
            new TypeReference<List<Transferencia>>() {}
        );
    }
} 