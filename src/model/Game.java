package model;

public class Game {
  public static final int GRID_WIDTH = 30;
  public static final int GRID_HEIGHT = 20;
  public static final int CELL_SIZE = 25;
    
  private GameState state;
  private int score;

  public Game() {
    this.state = GameState.MENU;
    this.score = 0;
  }

  public void startGame() {
    this.state = GameState.PLAYING;
    this.score = 0;
  }

  public void pauseGame() {
    if (state == GameState.PLAYING) {
      state = GameState.PAUSED;
    } else if (state == GameState.PAUSED) {
      state = GameState.PLAYING;
    }
  }

  public void gameOver() {
    this.state = GameState.GAME_OVER;
  }

  public void addScore(int points) {
    this.score += points;
  }

  public GameState getState() {
    return state;
  }

  public void setState(GameState state) {
    this.state = state;
  }

  public int getScore() {
    return score;
  }

  public void resetGame() {
    this.score = 0;
    this.state = GameState.PLAYING;
  }
}