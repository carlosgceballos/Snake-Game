# Snake Game

Juego clásico de Snake desarrollado en Java con interfaz gráfica usando Swing.

## Estructura del Proyecto

```
src/
├── App.java
├── model/
│   ├── Direction.java
│   ├── Entity.java
│   ├── Food.java
│   ├── Game.java
│   ├── GameState.java
│   ├── Position.java
│   └── Snake.java
├── view/
│   ├── GamePanel.java
│   └── GameWindow.java
└── controller/
    └── GameController.java
```

## Arquitectura MVC

### Model (Modelo)
- **Game**: Gestiona el estado general del juego, puntuación y lógica principal
- **Entity**: Clase abstracta base para todas las entidades del juego
- **Snake**: Representa la serpiente, hereda de Entity
- **Food**: Representa la comida, hereda de Entity
- **Position**: Encapsula coordenadas x, y
- **Direction**: Enum para las direcciones de movimiento
- **GameState**: Enum para los estados del juego

### View (Vista)
- **GameWindow**: Ventana principal del juego (JFrame)
- **GamePanel**: Panel donde se renderiza el juego (JPanel)

### Controller (Controlador)
- **GameController**: Maneja la entrada del usuario, actualiza el modelo y controla el flujo del juego

## Pilares de POO Implementados

### 1. Encapsulación
- Atributos privados en todas las clases
- Getters y setters para acceso controlado
- Lógica interna protegida (ej: body en Snake, position en Entity)

### 2. Herencia
- `Entity` es la clase base abstracta
- `Snake` y `Food` heredan de `Entity`
- Comparten atributos comunes (position, color)

### 3. Polimorfismo
- Método `render()` implementado de forma diferente en Snake y Food
- Método `update()` con comportamiento específico para cada entidad
- Referencias tipo Entity pueden apuntar a Snake o Food

### 4. Abstracción
- Clase `Entity` define contrato para todas las entidades
- Métodos abstractos obligan implementación específica
- Oculta detalles de implementación complejos

## Controles

- **Flechas / WASD**: Mover la serpiente
- **SPACE**: Iniciar juego / Reiniciar después de Game Over
- **P / ESC**: Pausar / Reanudar

## Reglas del Juego

1. La serpiente se mueve constantemente en la dirección indicada
2. Come la comida roja para crecer y ganar puntos
3. No puedes moverte en dirección opuesta a la actual
4. Pierdes si chocas con las paredes o contigo mismo
5. Cada comida vale 10 puntos

## Características

- Sistema de puntuación
- Pantalla de menú
- Pausa del juego
- Pantalla de Game Over con puntuación final
- Grid visual para mejor jugabilidad
- Colores diferenciados para cabeza y cuerpo de la serpiente
