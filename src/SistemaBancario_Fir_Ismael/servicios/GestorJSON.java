package SistemaBancario_Fir_Ismael.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GestorJSON {
    private static final ObjectMapper mapper = new ObjectMapper();

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
        return mapper.readValue(file, 
            mapper.getTypeFactory().constructCollectionType(List.class, Transferencia.class));
    }
} 