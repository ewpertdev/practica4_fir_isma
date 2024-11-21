package ej1;

/**
 * Interfaz que define el comportamiento común para los descifradores de contraseñas.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public interface IDescifrador {
    /**
     * Busca la contraseña que corresponde al hash SHA-256 proporcionado.
     * 
     * @param hashObjetivo El hash SHA-256 en formato hexadecimal de la contraseña a encontrar
     * @param longitud La longitud de la contraseña a buscar
     */
    void encontrarPassword(String hashObjetivo, int longitud);
} 