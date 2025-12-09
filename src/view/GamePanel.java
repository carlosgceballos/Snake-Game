package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.Game;
import model.GameState;
import model.Snake;
import model.Food;

public class GamePanel extends JPanel {
  private Game game;
  private Snake snake;
  private Food food;

  public GamePanel(Game game) {
    this.game = game;
    setPreferredSize(new Dimension(Game.GRID_WIDTH * Game.CELL_SIZE, Game.GRID_HEIGHT * Game.CELL_SIZE));
    setBackground(Color.BLACK);
    setFocusable(true);
  }

  public void setSnake(Snake snake) {
    this.snake = snake;
  }

  public void setFood(Food food) {
    this.food = food;
  }

  @Override
  protected void paintComponent(Graphics g) {
  super.paintComponent(g);

    if (game.getState() == GameState.MENU) { 
    drawMenu(g);
    } else if (game.getState() == GameState.PLAYING || game.getState() == GameState.PAUSED) {
    drawGame(g);
  
    if (game.getState() == GameState.PAUSED) {
    drawPaused(g);
    }
    } else if (game.getState() == GameState.GAME_OVER) {
      drawGame(g);
      drawGameOver(g);
    }
  }

  private void drawGame(Graphics g) {
    // Dibujar grid
    g.setColor(new Color(30, 30, 30));
    for (int i = 0; i <= Game.GRID_WIDTH; i++) {
     g.drawLine(i * Game.CELL_SIZE, 0, i * Game.CELL_SIZE, Game.GRID_HEIGHT * Game.CELL_SIZE);
    }
  
    for (int i = 0; i <= Game.GRID_HEIGHT; i++) {
      g.drawLine(0, i * Game.CELL_SIZE, Game.GRID_WIDTH * Game.CELL_SIZE, i * Game.CELL_SIZE);
    }

    // Renderizar entidades
    if (food != null) {
      food.render(g, Game.CELL_SIZE);
    }
        
    if (snake != null) {
      snake.render(g, Game.CELL_SIZE);
    }

    // Dibujar puntuacion
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 20));
    g.drawString("Score: " + game.getScore(), 10, 20);
  }

  private void drawMenu(Graphics g) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 48));
    String title = "SNAKE";
    int titleWidth = g.getFontMetrics().stringWidth(title);
    g.drawString(title, (getWidth() - titleWidth) / 2, getHeight() / 2 - 50);

    g.setFont(new Font("Arial", Font.PLAIN, 24));
    String instructions = "Press SPACE to start";
    int instructionsWidth = g.getFontMetrics().stringWidth(instructions);
    g.drawString(instructions, (getWidth() - instructionsWidth) / 2, getHeight() / 2 + 20);

    g.setFont(new Font("Arial", Font.PLAIN, 18));
    String controls = "Use arrow keys to move";
    int controlsWidth = g.getFontMetrics().stringWidth(controls);
    g.drawString(controls, (getWidth() - controlsWidth) / 2, getHeight() / 2 + 60);
  }

  private void drawPaused(Graphics g) {
    g.setColor(new Color(0, 0, 0, 180));
    g.fillRect(0, 0, getWidth(), getHeight());
        
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 48));
    String text = "PAUSED";
    int textWidth = g.getFontMetrics().stringWidth(text);
    g.drawString(text, (getWidth() - textWidth) / 2, getHeight() / 2);
  }

  private void drawGameOver(Graphics g) {
    g.setColor(new Color(0, 0, 0, 180));
    g.fillRect(0, 0, getWidth(), getHeight());
        
    g.setColor(Color.RED);
    g.setFont(new Font("Arial", Font.BOLD, 48));
    String text = "GAME OVER";
    int textWidth = g.getFontMetrics().stringWidth(text);
    g.drawString(text, (getWidth() - textWidth) / 2, getHeight() / 2 - 40);

    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.PLAIN, 24));
    String scoreText = "Final Score: " + game.getScore();
    int scoreWidth = g.getFontMetrics().stringWidth(scoreText);
    g.drawString(scoreText, (getWidth() - scoreWidth) / 2, getHeight() / 2 + 10);

    g.setFont(new Font("Arial", Font.PLAIN, 20));
    String restart = "Press SPACE to restart";
    int restartWidth = g.getFontMetrics().stringWidth(restart);
    g.drawString(restart, (getWidth() - restartWidth) / 2, getHeight() / 2 + 50);
  }
}