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
        return mapper.readValue(new File(rutaArchivo), Cliente.class);
    }

    public static List<Transferencia> leerTransferencias(String rutaArchivo) throws IOException {
        return mapper.readValue(
            new File(rutaArchivo),
            mapper.getTypeFactory().constructCollectionType(List.class, Transferencia.class)
        );
    }
} 