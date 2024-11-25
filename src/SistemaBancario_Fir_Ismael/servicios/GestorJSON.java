package SistemaBancario_Fir_Ismael.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import SistemaBancario_Fir_Ismael.modelo.Cliente;
import SistemaBancario_Fir_Ismael.modelo.Transferencia;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GestorJSON {
    private static final ObjectMapper mapper = new ObjectMapper();

    // Constructor por defecto
    public GestorJSON() {}

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