package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

// Herencia: Food extiende de Entity
public class Food extends Entity {
  private Random random;

  public Food() {
    super(new Position(0, 0), Color.RED);
    this.random = new Random();
  }

  @Override
  public void update() {
    // La comida no se actualiza por si sola
  }

  @Override
  public void render(Graphics g, int cellSize) {
    g.setColor(color);
    g.fillOval(position.getX() * cellSize, position.getY() * cellSize, cellSize, cellSize);
    g.setColor(Color.DARK_GRAY);
    g.drawOval(position.getX() * cellSize, position.getY() * cellSize, cellSize, cellSize);
  }

    // Generar nueva posicion aleatoria
  public void respawn(int gridWidth, int gridHeight, Snake snake) {
  Position newPosition;
  boolean validPosition;

  do {
    validPosition = true;
    newPosition = new Position(random.nextInt(gridWidth),random.nextInt(gridHeight));

    // Verificar que no aparezca sobre la serpiente
    for (Position segment : snake.getBody()) {
      if (newPosition.equals(segment)) {
        validPosition = false;
        break;
      }
    }
  } while (!validPosition);

    this.position = newPosition;
  }
}