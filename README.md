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
 
# Sistema Bancario - Documentación Técnica

## Descripción General
Este sistema simula operaciones bancarias básicas, específicamente centrado en transferencias entre cuentas de clientes. El sistema está diseñado para manejar múltiples transferencias de forma segura y concurrente.

## ¿Cómo Funciona?

### 1. Estructura de Datos Principal

#### Cliente (`Cliente.java`)
- Representa un cliente bancario con:
  - ID único
  - Nombre
  - Saldo actual
  - Número de cuenta
  - Dirección
- Incluye métodos sincronizados para garantizar operaciones thread-safe:
  - `realizarTransferencia()`: Verifica y ejecuta transferencias
  - `recibirTransferencia()`: Actualiza el saldo del destinatario

#### Transferencia (`Transferencia.java`)
- Representa una operación de transferencia con:
  - ID del cliente origen
  - ID del cliente destino
  - Monto a transferir

### 2. Gestión de Datos

#### GestorJSON (`GestorJSON.java`)
- Maneja la lectura de archivos JSON
- Utiliza Jackson para deserialización
- Métodos principales:
  - `leerCliente()`: Lee datos de un cliente desde JSON
  - `leerTransferencias()`: Lee lista de transferencias desde JSON

### 3. Lógica de Negocio

#### ServicioTransferencias (`ServicioTransferencias.java`)
- Coordina todas las operaciones bancarias
- Mantiene un mapa de clientes activos
- Funciones principales:
  - `cargarClientes()`: Carga 6 clientes desde archivos JSON
  - `procesarTransferencia()`: Ejecuta una transferencia individual
  - `procesarArchivoTransferencias()`: Procesa múltiples transferencias

### 4. Punto de Entrada

#### Main (`Main.java`)
- Inicia el sistema
- Proceso de ejecución:
  1. Crea una instancia de `ServicioTransferencias`
  2. Carga los clientes
  3. Procesa múltiples archivos de transferencias
  4. Muestra el estado final de todos los clientes

## Flujo de una Transferencia

1. **Inicio**
   - El sistema lee un archivo de transferencias

2. **Validación**
   - Verifica que existan tanto el origen como el destino
   - Comprueba que el monto sea positivo
   - Confirma que haya saldo suficiente

3. **Ejecución**
   - Resta el monto de la cuenta origen
   - Suma el monto a la cuenta destino
   - Todo el proceso es sincronizado para evitar condiciones de carrera

4. **Registro**
   - Muestra mensajes de éxito o error
   - Actualiza los saldos en memoria

## Características de Seguridad

1. **Sincronización**
   - Métodos críticos marcados como `synchronized`
   - Previene problemas de concurrencia en transferencias

2. **Validaciones**
   - Montos positivos
   - Saldo suficiente
   - Existencia de clientes

3. **Manejo de Errores**
   - Captura y manejo de excepciones IO
   - Mensajes de error descriptivos

## Ejemplo de Uso

1. **Preparar Archivos JSON**
   ```json
   // Cliente1.json
   {
       "id": "1",
       "nombre": "Juan Pérez",
       "saldo": 1000.00,
       "numeroCuenta": "ES1234567890",
       "direccion": "Calle Principal 123"
   }

   // Transferencias1.json
   [
       {
           "origen": "1",
           "destino": "2",
           "monto": 100.00
       }
   ]
   ```

2. **Ejecutar el Sistema**
   - El sistema procesará automáticamente:
     - 6 archivos de clientes
     - 5 archivos de transferencias
   - Mostrará el resultado de cada operación
   - Presentará el estado final de todas las cuentas

## Consideraciones Técnicas

- Utiliza Jackson para procesamiento JSON
- Implementa patrones de diseño:
  - Singleton (GestorJSON)
  - Service Layer (ServicioTransferencias)
- Manejo thread-safe de operaciones monetarias
- Documentación JavaDoc completa



## Autores
- Mohd Firdaus Bin Abdullah
- Ismael Lozano
