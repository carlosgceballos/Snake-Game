package model;

import java.awt.Color;
import java.awt.Graphics;

// Abstraccion: clase base para todas las entidades del juego
public abstract class Entity {
  protected Position position;
  protected Color color;

  public Entity(Position position, Color color) {
    this.position = position;
    this.color = color;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Color getColor() {
    return color;
  }

  // Polimorfismo: cada entidad se dibuja de manera diferente
  public abstract void render(Graphics g, int cellSize);
    
  // Abstraccion: logica de actualizacion
  public abstract void update();
}
