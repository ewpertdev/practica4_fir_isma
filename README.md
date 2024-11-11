# Práctica 4 - Programación concurrente avanzada

### Entregables

- Es una práctica por parejas. Solo uno de la pareja deberá entregar el archivo .zip.
- El zip deberá llamarse P4_NombreApellidos_NombreApellidos.zip
- Deberás entregar un proyecto con 3 paquetes, un ejercicio en cada paquete.
- El proyecto deberá contener el nombre y el apellido de las dos personas.
- Se deberá crear un repositorio de git para esta práctica.
- La calificación será la misma para las 2 personas.
- Deberás generar el Javadoc de todos los ejercicios.

## Ejercicio 1: Hackeo de contraseñas por fuerza bruta

El objetivo de esta práctica es implementar un programa en Java que, utilizando
programación paralela (con hilos, pool de hilos ```[ExecutorService]```, ```ForkJoinPool```, o
cualquier otra técnica concurrente), encuentre la palabra de 4 letras minúsculas
correspondiente a un hash SHA-256 dado, utilizando un enfoque de fuerza bruta

```b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79```

### Descripción

1. Hash SHA-256: El algoritmo SHA-256 es una función criptográfica que toma una
entrada y genera un valor de hash de 256 bits (32 bytes). Para esta práctica, se
proporcionará un hash SHA-256 generado a partir de una palabra de 4 letras
minúsculas (por ejemplo, "abcd").
2. Fuerza Bruta: El método de fuerza bruta implica probar todas las combinaciones
posibles de 4 letras minúsculas ('a' a 'z') hasta encontrar la que, al aplicarle el
algoritmo SHA-256, produce el mismo hash proporcionado.
3. Programación Paralela: Dado que el proceso de prueba de combinaciones es
independiente entre sí, se puede dividir entre múltiples hilos o tareas concurrentes
para acelerar el proceso. Tu tarea será implementar este enfoque paralelo para
optimizar el tiempo de búsqueda.

### Requerimientos:

1. Entrada:
- Un String que representa el hash SHA-256 a encontrar (ver más arriba).
- La longitud de la palabra a encontrar. En este caso será de 4 letras
minúsculas (caracteres de 'a' a 'z').
2. Salida:
- La palabra que, al aplicarle SHA-256, corresponde al hash dado.
- El tiempo total de ejecución del programa en milisegundos.
3. Implementación:
- Usa programación paralela para distribuir la carga de trabajo entre varios
hilos.  Puedes utilizar:

  - Asegúrate de manejar correctamente la sincronización entre hilos para evitar
condiciones de carrera e interbloqueos.
  - Implemente un mecanismo para detener todos los hilos una vez que se haya
encontrado la palabra correcta.
4. Restricciones:
- No se permite el uso de librerías de hash que no sean las proporcionadas por
la biblioteca estándar de Java (```java.security.MessageDigest```).
- La implementación debe ser lo más eficiente posible en términos de tiempo.
Para ello, deberás medir el tiempo de ejecución que tarda.
5. Extras:

- Modifica el código para permitir una longitud variable, pero conocida, de la
contraseña.
- Implementa distintas formas de paralelismo y compáralas.

### Pistas:
- Haz primero una implementación secuencial, sin paralelismo para asegurar que la
base funciona. Cuando lo tengas, crea una clase nueva que implemente lo mismo en
paralelo. Puedes crear una clase “Descifrador” con el main y otras dos clases,
DescifradorSecuencial y DescifradorParalelo.
- Considera el espacio de búsqueda: todas las combinaciones posibles de 4 letras
minúsculas (26^4 combinaciones).
- El espacio de combinaciones se puede dividir entre los hilos, por ejemplo, asignando
a cada hilo un rango específico de combinaciones.
Utiliza el método MessageDigest.getInstance("SHA-256") para generar el hash de
una cadena.


```java

public byte[] getHash(String text) {
byte[] encodedhash = null;
MessageDigest digest;
try {
digest = MessageDigest.getInstance("SHA-256");
encodedhash = digest.digest(
text.getBytes(StandardCharsets.UTF_8));
} catch (NoSuchAlgorithmException e) {
//
e.printStackTrace();
}
return encodedhash;
}
```
- Recuerda que el método “.equals()” de un objeto compara si las 2 variables
contienen el mismo objeto. Si quieres comparar 2 hashes, no sería correcto hacer
hash1.equals(hash2) porque, con independencia del contenido de los hashes,
esa comparación solo compararía si son el mismo objeto en memoria. Para
comparar el contenido de dos arrays puedes utilizar
Arrays.equals(hash1,hash2)
### Entregables:
- Código fuente completo en Java (proyecto de Eclipse)
- Deberás incluir en los comentarios las decisiones de diseño que tomes.
### Comentarios (mis resultados)
- Secuencialmente, en encontrar “zzzz” tarda 386ms. En encontrar “zzzzz” tarda
6000ms y en encontrar “zzzzzz” tarda 124233.
- Con 26 hilos, en encontrar “zzzz” tarda más, 627. Pero en encontrar “zzzzz” tarda
763 o 2720. En encontrar “zzzzzz”, utilizando todos los recursos de mi portátil, ha
tardado 67519 ms.
