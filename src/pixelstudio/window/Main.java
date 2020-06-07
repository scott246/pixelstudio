package pixelstudio.window;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {
    private static JFrame window = new JFrame("Pixel Studio");
    private static JPanel windowContentPanel = new JPanel();
    private static JPanel editorPanel = new JPanel();
    private static JPanel infoControlPanel = new JPanel();
    private static JPanel infoPanel = new JPanel();
    private static JPanel controlPanel = new JPanel();
    
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 400;
    private static final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    
    private static final Color BACKGROUND_COLOR = new Color(64, 64, 64, 255);
    private static final Color EDITOR_BACKGROUND_COLOR = new Color(0, 0, 255, 255);
    private static final Color INFO_BACKGROUND_COLOR = new Color(0, 255, 0, 255);
    private static final Color CONTROL_BACKGROUND_COLOR = new Color(255, 0, 0, 255);

    private static final void INITIALIZE_WINDOW() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(WINDOW_DIMENSION);
        
        windowContentPanel.setBackground(BACKGROUND_COLOR);
        windowContentPanel.setLayout(new BoxLayout(windowContentPanel, BoxLayout.X_AXIS));
        windowContentPanel.setSize(WINDOW_DIMENSION);
        
        editorPanel.setBackground(EDITOR_BACKGROUND_COLOR);
        windowContentPanel.add(editorPanel);
        
        infoControlPanel.setLayout(new BoxLayout(infoControlPanel, BoxLayout.Y_AXIS));
        
        infoPanel.setBackground(INFO_BACKGROUND_COLOR);
        infoControlPanel.add(infoPanel);
        
        controlPanel.setBackground(CONTROL_BACKGROUND_COLOR);
        infoControlPanel.add(controlPanel);
        
        windowContentPanel.add(infoControlPanel);
        
        window.add(windowContentPanel);

        window.setVisible(true);
    }

    public static void main(String[] args) {
        INITIALIZE_WINDOW();
    }
}