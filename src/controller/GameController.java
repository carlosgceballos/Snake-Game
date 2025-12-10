package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import model.Direction;
import model.Game;
import model.GameState;
import model.Snake;
import model.Food;
import view.GamePanel;

public class GameController implements KeyListener, ActionListener {
  private Game game;
  private Snake snake;
  private Food food;
  private GamePanel gamePanel;
  private Timer timer;
  
  private static final int GAME_SPEED = 100; // ms entre updates

  public GameController(Game game, GamePanel gamePanel) {
      this.game = game;
      this.gamePanel = gamePanel;
      this.timer = new Timer(GAME_SPEED, this);
  }

  public void initGame() {
      // Inicializar entidades
      snake = new Snake(Game.GRID_WIDTH / 2, Game.GRID_HEIGHT / 2);
      food = new Food();
      food.respawn(Game.GRID_WIDTH, Game.GRID_HEIGHT, snake);
      
      gamePanel.setSnake(snake);
      gamePanel.setFood(food);
      gamePanel.addKeyListener(this);
  }

  public void startGame() {
      game.startGame();
      snake = new Snake(Game.GRID_WIDTH / 2, Game.GRID_HEIGHT / 2);
      food.respawn(Game.GRID_WIDTH, Game.GRID_HEIGHT, snake);
      gamePanel.setSnake(snake);
      timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      if (game.getState() == GameState.PLAYING) {
          updateGame();
      }
      gamePanel.repaint();
  }

  private void updateGame() {
      // Actualizar serpiente
      snake.update();
      
      // Verificar colision con comida
      if (snake.getHead().equals(food.getPosition())) {
          snake.grow();
          game.addScore(10);
          food.respawn(Game.GRID_WIDTH, Game.GRID_HEIGHT, snake);
      }
      
      // Verificar colisiones fatales
      if (snake.checkWallCollision(Game.GRID_WIDTH, Game.GRID_HEIGHT) || 
          snake.checkSelfCollision()) {
          game.gameOver();
          timer.stop();
      }
  }

  @Override
  public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();

      if (game.getState() == GameState.MENU) {
          if (key == KeyEvent.VK_SPACE) {
              startGame();
          }
      } else if (game.getState() == GameState.PLAYING) {
          switch (key) {
              case KeyEvent.VK_UP:
              case KeyEvent.VK_W:
                  snake.setDirection(Direction.UP);
                  break;
              case KeyEvent.VK_DOWN:
              case KeyEvent.VK_S:
                  snake.setDirection(Direction.DOWN);
                  break;
              case KeyEvent.VK_LEFT:
              case KeyEvent.VK_A:
                  snake.setDirection(Direction.LEFT);
                  break;
              case KeyEvent.VK_RIGHT:
              case KeyEvent.VK_D:
                  snake.setDirection(Direction.RIGHT);
                  break;
              case KeyEvent.VK_P:
              case KeyEvent.VK_ESCAPE:
                  game.pauseGame();
                  break;
          }
      } else if (game.getState() == GameState.PAUSED) {
          if (key == KeyEvent.VK_P || key == KeyEvent.VK_ESCAPE) {
              game.pauseGame();
          }
      } else if (game.getState() == GameState.GAME_OVER) {
          if (key == KeyEvent.VK_SPACE) {
              game.resetGame();
              startGame();
          }
      }
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}
}