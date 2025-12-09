package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

// Herencia: Snake extiende de Entity
public class Snake extends Entity {
  private List<Position> body;
  private Direction direction;
  private Direction nextDirection;
  private boolean growing;

  public Snake(int startX, int startY) {
    super(new Position(startX, startY), new Color(34, 139, 34));
    this.body = new ArrayList<>();
    this.body.add(new Position(startX, startY));
    this.body.add(new Position(startX - 1, startY));
    this.body.add(new Position(startX - 2, startY));
    this.direction = Direction.RIGHT;
    this.nextDirection = Direction.RIGHT;
    this.growing = false;
  }

  @Override
  public void update() {
    // Actualizar direccion si no es opuesta
    if (!nextDirection.isOpposite(direction)) {
      direction = nextDirection;
    }

    // Calcular nueva posicion de la cabeza
    Position head = body.get(0);
    Position newHead = new Position(
      head.getX() + direction.getDx(),
      head.getY() + direction.getDy()
    );

    // Insertar nueva cabeza
    body.add(0, newHead);

    // Si no esta creciendo, eliminar cola
    if (!growing) {
      body.remove(body.size() - 1);
    } else {
      growing = false;
    }

     // Actualizar posicion de la entidad
    this.position = newHead;
  }

  @Override
  public void render(Graphics g, int cellSize) {
  // Dibujar cabeza con color diferente
  Position head = body.get(0);
  g.setColor(new Color(0, 100, 0));
  g.fillRect(head.getX() * cellSize, head.getY() * cellSize, cellSize, cellSize);
  g.setColor(Color.BLACK);
  g.drawRect(head.getX() * cellSize, head.getY() * cellSize, cellSize, cellSize);

  // Dibujar cuerpo
  g.setColor(color);
    for (int i = 1; i < body.size(); i++) {
      Position segment = body.get(i);
      g.fillRect(segment.getX() * cellSize, segment.getY() * cellSize, cellSize, cellSize); 
      g.setColor(Color.BLACK);
      g.drawRect(segment.getX() * cellSize, segment.getY() * cellSize, cellSize, cellSize);
      g.setColor(color);
    }
  }

  public void grow() {
    this.growing = true;
  }

  public void setDirection(Direction direction) {
    this.nextDirection = direction;
  }

  public Direction getDirection() {
    return direction;
  }

  public List<Position> getBody() {
    return body;
  }

  public Position getHead() {
    return body.get(0);
  }

  // Verificar colision con el propio cuerpo
  public boolean checkSelfCollision() {
    Position head = getHead();
    for (int i = 1; i < body.size(); i++) {
      if (head.equals(body.get(i))) {
        return true;
      }
    }
    return false;
  }

  // Verificar colision con paredes
  public boolean checkWallCollision(int gridWidth, int gridHeight) {
    Position head = getHead();
    return head.getX() < 0 || head.getX() >= gridWidth ||head.getY() < 0 || head.getY() >= gridHeight;
  }
}