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

 # Sistema Bancario

## Descripción
Sistema bancario desarrollado en Java que permite gestionar clientes y procesar transferencias bancarias mediante archivos JSON. El sistema procesa las transferencias de forma secuencial y mantiene la consistencia de los datos mediante sincronización.

## Estructura del Código

### Clases Principales

#### Cliente (`Cliente.java`)
- Representa un cliente bancario con:
  - ID único
  - Nombre
  - Saldo
  - Número de cuenta
  - Dirección
- Métodos sincronizados para operaciones monetarias:
  - `realizarTransferencia()`: Ejecuta transferencias salientes
  - `recibirTransferencia()`: Procesa transferencias entrantes
  - `getSaldo()` y `setSaldo()`: Acceso thread-safe al saldo

#### Transferencia (`Transferencia.java`) 
- Modela una transferencia bancaria con:
  - Cliente origen
  - Cliente destino  
  - Monto a transferir
- Utiliza anotaciones Jackson para mapeo JSON

#### GestorJSON (`GestorJSON.java`)
- Gestiona la lectura de archivos JSON
- Métodos principales:
  - `leerCliente()`: Deserializa datos de cliente
  - `leerTransferencias()`: Deserializa lista de transferencias

#### ServicioTransferencias (`ServicioTransferencias.java`)
- Coordina las operaciones bancarias
- Funcionalidades:
  - Mantiene registro de clientes activos
  - Carga clientes desde JSON
  - Procesa transferencias individuales y por lotes

## Funcionamiento Actual

### 1. Carga de Datos
- Lee 6 archivos de clientes (Cliente1.json a Cliente6.json)
- Almacena clientes en un Map<String, Cliente>

### 2. Procesamiento de Transferencias
- Lee 5 archivos de transferencias secuencialmente
- Por cada transferencia:
  1. Valida existencia de clientes
  2. Verifica saldo suficiente
  3. Ejecuta la transferencia si es válida
  4. Muestra resultado por consola

### 3. Validaciones Implementadas
- Monto positivo
- Saldo suficiente
- Existencia de clientes origen y destino

## Formato de Archivos

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


## Requisitos
- Java 8 o superior
- Biblioteca Jackson para JSON
- Archivos JSON en directorio data/

## Uso

1. Preparar archivos JSON:
   - 6 archivos de clientes en data/Cliente[1-6].json
   - Archivos de transferencias en data/

2. Ejecutar la clase Main

## Mensajes del Sistema

### Éxito
```
Cliente cargado: Cliente{id='1', nombre='Juan', saldo=1000.00...}
Transferencia realizada: 1 -> 2: 100.00€
```

### Errores

```
Error: Saldo insuficiente para la transferencia
Error: El monto debe ser positivo
Error: Cliente no encontrado
```

## Características de Seguridad

### Sincronización
- Métodos críticos marcados como `synchronized`:
  - Operaciones de saldo
  - Transferencias
  - Recepciones de dinero

### Validaciones
- Montos positivos
- Saldos suficientes
- Existencia de clientes

## Limitaciones Actuales
1. Procesamiento secuencial (no concurrente)
2. No persiste cambios en archivos JSON
3. Rutas de archivos hardcodeadas
 
## Autores
- Mohd Firdaus Bin Abdullah
- Ismael Lozano
