package SistemaBancario_Fir_Ismael.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GestorJSON {
    private static final ObjectMapper mapper;
    
    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Cliente leerCliente(String rutaArchivo) throws IOException {
        File file = new File(rutaArchivo);
        if (!file.exists()) {
            throw new IOException("El archivo no existe: " + rutaArchivo);
        }
        return mapper.readValue(file, Cliente.class);
    }

    public static List<Transferencia> leerTransferencias(String rutaArchivo) throws IOException {
        File file = new File(rutaArchivo);
        if (!file.exists()) {
            throw new IOException("El archivo no existe: " + rutaArchivo);
        }
        try {
            return mapper.readValue(file, new TypeReference<List<Transferencia>>() {});
        } catch (IOException e) {
            System.err.println("Error al leer archivo " + rutaArchivo + ": " + e.getMessage());
            throw e;
        }
    }
} 