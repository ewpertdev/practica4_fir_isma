# Descifrador de Contraseñas (Descrifrador por Fuerza Bruta)

Sistema de descifrado de contraseñas que implementa tres estrategias diferentes de procesamiento para encontrar contraseñas a partir de su hash SHA-256.

## Características Principales

- Descifra contraseñas de longitud configurable
- Utiliza hash SHA-256
- Soporta solo letras minúsculas (a-z)
- Implementa tres estrategias de procesamiento diferentes
- Incluye sistema de benchmarking

## Implementaciones

### 1. Secuencial (`DescifradorSecuencial`)
- Implementación básica mono-hilo
- Búsqueda recursiva por fuerza bruta
- Útil como base de comparación

### 2. Paralela (`DescifradorParalelo`)
- Utiliza `ExecutorService` para gestión de hilos
- Paralelización por primera letra de la contraseña
- Número de hilos configurable
- Sincronización mediante `AtomicReference`

### 3. ForkJoin (`DescifradorForkJoin`)
- Implementa el framework Fork/Join
- División recursiva del trabajo
- Umbral configurable para procesamiento secuencial
- Aprovecha el algoritmo work-stealing

## Utilidades

### HashUtils
- Generación de hashes SHA-256
- Conversión entre bytes y hexadecimal
- Gestión de instancias MessageDigest

### Benchmark
- Pruebas con diferentes longitudes de contraseña
- Comparación de implementaciones
- Medición de tiempos de ejecución
- Soporte para múltiples configuraciones de hilos

## Estructura del Proyecto

```
src/descifradorFuerzaBruta/
│
├── Descifrador.java           # Clase principal
├── IDescifrador.java          # Interfaz común
├── DescifradorSecuencial.java # Implementación secuencial
├── DescifradorParalelo.java   # Implementación paralela
├── DescifradorForkJoin.java   # Implementación fork-join
│
└── utils/
    ├── Benchmark.java         # Utilidad de pruebas
    ├── BenchmarkRunner.java   # Ejecutor de benchmarks
    └── HashUtils.java         # Utilidades de hash
```


## Detalles Técnicos

### Hash por Defecto

`b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79`
