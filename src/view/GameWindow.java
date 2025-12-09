package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.Game;

public class GameWindow extends JFrame {
  private GamePanel gamePanel;

  public GameWindow(Game game) {
    super("Snake Game");
        
    this.gamePanel = new GamePanel(game);
        
    add(gamePanel);
    pack();
        
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
  }

  public GamePanel getGamePanel() {
    return gamePanel;
  }
}