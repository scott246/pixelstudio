package pixelstudio.window;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    private static JFrame window = new JFrame("Pixel Studio");
    private static JPanel windowContentPane = new JPanel();
    private static GridBagLayout windowLayout = new GridBagLayout();
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 255);

    private static final void INITIALIZE_WINDOW() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(800, 600);

        windowContentPane.setBackground(BACKGROUND_COLOR);
        windowContentPane.setLayout(windowLayout);
        window.add(windowContentPane);

        window.setVisible(true);
    }

    public static void main(String[] args) {
        INITIALIZE_WINDOW();
    }
}