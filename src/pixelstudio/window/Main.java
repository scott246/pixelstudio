package pixelstudio.window;

import pixelstudio.Constants;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Main {
    private static JFrame window = new JFrame("Pixel Studio");
    private static JPanel windowContentPanel = new JPanel();
    private static JPanel editorPanel = new JPanel();
    private static JPanel infoControlPanel = new JPanel();
    private static JPanel infoPanel = new JPanel();
    private static JPanel controlPanel = new JPanel();
    
    private static Border panelBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR);

    public Main() {
        editorPanel.setBackground(Constants.EDITOR_BACKGROUND_COLOR);
    	editorPanel.setBorder(panelBorder);
    	editorPanel.setPreferredSize(Constants.EDITOR_DIMENSION);

        infoPanel.setBackground(Constants.INFO_BACKGROUND_COLOR);
        infoPanel.setBorder(panelBorder);
        infoPanel.setPreferredSize(Constants.INFO_DIMENSION);

        controlPanel.setBackground(Constants.CONTROL_BACKGROUND_COLOR);
        controlPanel.setBorder(panelBorder);
        controlPanel.setPreferredSize(Constants.CONTROL_DIMENSION);

        infoControlPanel.setLayout(new BoxLayout(infoControlPanel, BoxLayout.PAGE_AXIS));
        infoControlPanel.add(infoPanel);
        infoControlPanel.add(controlPanel);
        
    	windowContentPanel.setBackground(Constants.BACKGROUND_COLOR);
        windowContentPanel.setLayout(new BoxLayout(windowContentPanel, BoxLayout.LINE_AXIS));
    	windowContentPanel.setBorder(panelBorder);
        windowContentPanel.add(editorPanel);
        windowContentPanel.add(infoControlPanel);
        windowContentPanel.setOpaque(true);
    }
    
    private static void createAndShowGUI() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(Constants.WINDOW_DIMENSION);
        window.setContentPane(windowContentPanel);
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
        
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}