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

## Autores

- Ismael Lozano
- Mohd Firdaus Bin Abdullah

