package descifradorFuerzaBruta.utils;

/**
 * Clase ejecutora para las pruebas de rendimiento.
 * Proporciona un punto de entrada simple para ejecutar los benchmarks
 * desde un IDE o línea de comandos.
 * 
 * @author Mohd Firdaus Bin Abdullah
 * @author Ismael Lozano
 */
public class BenchmarkRunner {
    
    /**
     * Constructor por defecto.
     * No se requiere inicialización especial ya que la clase solo contiene un método estático.
     */
    public BenchmarkRunner() {
        // Constructor vacío
    }

    /**
     * Punto de entrada principal para ejecutar los benchmarks.
     * Inicia una serie de pruebas de rendimiento para diferentes
     * implementaciones del descifrador.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("Iniciando benchmarks...");
        Benchmark.ejecutarBenchmarks();
    }
} 