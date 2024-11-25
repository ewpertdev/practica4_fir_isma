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