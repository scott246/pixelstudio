package pixelstudio.window;

import pixelstudio.Constants;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Window {
    private static JFrame window = new JFrame("Pixel Studio");
    
    private static JPanel windowContentPanel = new JPanel();
    private static JPanel editorPanel = new JPanel();
    private static JPanel infoControlPanel = new JPanel();
    private static JPanel infoPanel = new JPanel();
    private static JPanel controlPanel = new JPanel();
    
    private static JLabel titleLabel = new JLabel();
    
    private static Border panelBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR);

    public Window() {
    	initializeWindowContentPanel();
    }
    
    private static void initializeWindowContentPanel() {
    	initializeEditorPanel();
        initializeInfoControlPanel();
        
    	windowContentPanel.setBackground(Constants.BACKGROUND_COLOR);
        windowContentPanel.setLayout(new BoxLayout(windowContentPanel, BoxLayout.LINE_AXIS));
    	windowContentPanel.setBorder(panelBorder);
        windowContentPanel.add(editorPanel);
        windowContentPanel.add(infoControlPanel);
        windowContentPanel.setOpaque(true);
    }
    
    private static void initializeEditorPanel() {
    	editorPanel.setBackground(Constants.EDITOR_BACKGROUND_COLOR);
    	editorPanel.setBorder(panelBorder);
    	editorPanel.setPreferredSize(Constants.EDITOR_DIMENSION);
    }
    
    private static void initializeInfoControlPanel() {
    	initializeInfoPanel();
        initializeControlPanel();
        infoControlPanel.setLayout(new BoxLayout(infoControlPanel, BoxLayout.PAGE_AXIS));
        infoControlPanel.add(infoPanel);
        infoControlPanel.add(controlPanel);
    }
    
    private static void initializeInfoPanel() {
    	infoPanel.setBackground(Constants.INFO_BACKGROUND_COLOR);
        infoPanel.setBorder(panelBorder);
        infoPanel.setPreferredSize(Constants.INFO_DIMENSION);
        titleLabel.setText("Pixel Studio");
    	titleLabel.setSize(infoPanel.getWidth(), 100);
    	titleLabel.setForeground(Constants.ACCENT_COLOR);
    	infoPanel.add(titleLabel);
    }
    
    private static void initializeControlPanel() {
    	controlPanel.setBackground(Constants.CONTROL_BACKGROUND_COLOR);
        controlPanel.setBorder(panelBorder);
        controlPanel.setPreferredSize(Constants.CONTROL_DIMENSION);
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
        new Window();
        
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}