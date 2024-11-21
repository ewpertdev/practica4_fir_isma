package ej3.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import ej3.model.Client;
import ej3.model.Transfer;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase utilitaria para operaciones JSON.
 * Proporciona métodos para leer y escribir objetos desde/hacia archivos JSON.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor privado para evitar instanciación.
     */
    private JsonUtils() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Lee un cliente desde un archivo JSON.
     *
     * @param path Ruta del archivo JSON
     * @return Cliente leído del archivo
     * @throws IOException Si hay errores de lectura
     */
    public static Client leerCliente(String path) throws IOException {
        return mapper.readValue(new File(path), Client.class);
    }

    /**
     * Lee una lista de transferencias desde un archivo JSON.
     *
     * @param path Ruta del archivo JSON
     * @return Lista de transferencias leídas
     * @throws IOException Si hay errores de lectura
     */
    public static List<Transfer> leerTransferencias(String path) throws IOException {
        CollectionType tipoLista = mapper.getTypeFactory()
            .constructCollectionType(List.class, Transfer.class);
        return mapper.readValue(new File(path), tipoLista);
    }

    /**
     * Guarda un cliente en un archivo JSON.
     *
     * @param client Cliente a guardar
     * @param path Ruta donde guardar el archivo
     * @throws IOException Si hay errores de escritura
     */
    public static void guardarCliente(Client client, String path) throws IOException {
        mapper.writeValue(new File(path), client);
    }

    /**
     * Guarda una lista de transferencias en un archivo JSON.
     *
     * @param transfers Lista de transferencias a guardar
     * @param path Ruta donde guardar el archivo
     * @throws IOException Si hay errores de escritura
     */
    public static void guardarTransferencias(List<Transfer> transfers, String path) throws IOException {
        mapper.writeValue(new File(path), transfers);
    }
} 