# Nota Importante sobre el Repositorio

## Reorganización del Repositorio (22/11/2024)

Durante un intento de reorganizar el repositorio para los ejercicios 2 y 3, se realizó una operación de `git rebase` que afectó inadvertidamente las fechas de todos los commits del proyecto. 

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


### Consideraciones de Rendimiento

#### Secuencial
- Mejor para contraseñas cortas (< 4 caracteres)
- Sin overhead de sincronización
- Uso de memoria predecible

#### Paralelo
- Óptimo para contraseñas medias
- Escalable según núcleos disponibles
- Overhead por gestión de hilos
- Sincronización en acceso a MessageDigest

#### ForkJoin
- Eficiente para contraseñas largas
- Balanceo automático de carga
- Menor overhead que ExecutorService
- Umbral ajustable para optimización

### Limitaciones
- Solo letras minúsculas (a-z)
- Sin soporte para caracteres especiales
- Rendimiento dependiente del hardware

# Simulador de Minas y Oro

Un juego multijugador concurrente donde varios jugadores compiten por encontrar oro mientras evitan las minas en un terreno compartido.

## Descripción

Este proyecto implementa un juego donde múltiples jugadores exploran simultáneamente un terreno en busca de oro. Los jugadores se mueven de forma aleatoria por el tablero, y el juego termina cuando un jugador encuentra oro (victoria) o una mina (derrota).

### Características

- Terreno de juego configurable (por defecto 8x8)
- Soporte para múltiples jugadores simultáneos (por defecto 4 jugadores)
- Implementación concurrente usando hilos de Java
- Visualización en tiempo real del estado del terreno
- Sistema de turnos aleatorios

## Estructura del Proyecto

El proyecto está organizado en las siguientes clases principales:

- `Principal.java`: Clase principal que inicia y coordina el juego
- `Terreno.java`: Representa el tablero de juego y gestiona su estado
- `Jugador.java`: Implementa la lógica de cada jugador

## Reglas del Juego

- Los jugadores se mueven de forma aleatoria por el terreno
- El terreno contiene:
  - Oro (O): Al encontrarlo, el jugador gana
  - Minas (M): Al encontrarlas, el jugador pierde
  - Casillas vacías (.): No tienen efecto
  - Jugadores (P): Indica la posición actual de un jugador

# Sistema Bancario - Procesamiento Concurrente de Transferencias

## Descripción
Sistema bancario que procesa transferencias entre cuentas de clientes de forma concurrente, utilizando archivos JSON como fuente de datos. El sistema implementa sincronización thread-safe y procesamiento paralelo de transferencias.

## Estructura del Proyecto
```
src/SistemaBancario_Fir_Ismael/
├── Main.java # Punto de entrada
├── modelo/
│ ├── Cliente.java # Entidad Cliente
│ └── Transferencia.java # Entidad Transferencia
└── servicios/
├── GestorJSON.java # Manejo de archivos JSON
├── ServicioTransferencias.java # Lógica de negocio
└── ProcesadorConcurrente.java # Gestión de concurrencia
```

## Características Implementadas

### Procesamiento Concurrente
- Pool de 3 hilos para procesamiento paralelo
- Control de ciclo de vida de hilos
- Timeout de 1 minuto para operaciones
- Logging detallado de operaciones concurrentes

### Gestión de Datos
- Lectura de archivos JSON de clientes y transferencias
- Validación de operaciones bancarias
- Sincronización de operaciones monetarias
- Manejo de errores y excepciones

### Seguridad y Sincronización
- Métodos críticos sincronizados
- Acceso thread-safe a saldos
- Control de condiciones de carrera
- Validación de transferencias

## Requisitos
- Java 8 o superior
- Biblioteca Jackson para JSON
- IDE compatible con Java (Eclipse recomendado)

## Configuración de Archivos

### Cliente (data/ClienteX.json)
```json
{
"id": "1",
"nombre": "Nombre Cliente",
"saldo": 1000.00,
"numeroCuenta": "ES1234567890",
"direccion": "Calle Example 123"
}
```

### Transferencias (data/TransferenciasX.json)
```json
[
{
"origen": "1",
"destino": "2",
"monto": 100.00
}
]
```

## Uso del Sistema

1. **Preparación**
   - Colocar archivos de clientes en `data/Cliente[1-6].json`
   - Colocar archivos de transferencias en `data/Transferencias*.json`

2. **Monitoreo**
   - El sistema mostrará mensajes de:
     - Carga de clientes
     - Procesamiento de transferencias por hilo
     - Estado final de las cuentas

## Detalles de Implementación

### Concurrencia (`ProcesadorConcurrente.java`)
- Utiliza `ExecutorService` con pool fijo de hilos
- Procesa múltiples archivos simultáneamente
- Maneja timeout y cierre ordenado
- Proporciona logging detallado

### Sincronización (`Cliente.java`)
```
java
public synchronized boolean realizarTransferencia(Cliente destino, double monto)
public synchronized void setSaldo(double saldo)
public synchronized double getSaldo()
```


### Gestión JSON (`GestorJSON.java`)
- Deserialización de clientes y transferencias
- Manejo de errores de parseo
- Utiliza Jackson ObjectMapper

## Mensajes del Sistema

### Éxito
```
Hilo pool-1-thread-1 procesando archivo: data/Transferencias1.json
Transferencia realizada: 1 -> 2: 100.00€
```

### Errores
```
Error: Saldo insuficiente para la transferencia
Error en hilo pool-1-thread-2: Archivo no encontrado
```

## Validaciones Implementadas
1. Saldo suficiente para transferencia
2. Monto positivo
3. Existencia de clientes origen y destino
4. Timeout en operaciones concurrentes

## Autores
- Mohd Firdaus Bin Abdullah
- Ismael Lozano

## Notas de Desarrollo
- Implementación basada en principios SOLID
- Código documentado con JavaDoc
- Manejo completo de excepciones
- Logging detallado de operaciones
 
## Autores
- Mohd Firdaus Bin Abdullah
- Ismael Lozano
