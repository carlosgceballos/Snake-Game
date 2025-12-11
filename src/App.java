import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controller.GameController;
import model.Game;
import view.GameWindow;

public class App {
    public static void main(String[] args) throws Exception {
        // Configurar Look and Feel
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        
        // Inicializar MVC
        Game game = new Game();
        GameWindow window = new GameWindow(game);
        GameController controller = new GameController(game, window.getGamePanel());
        
        // Iniciar juego
        controller.initGame();
    }
}