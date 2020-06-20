package pixelstudio;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Window {
    private static JFrame window = new JFrame("Pixel Studio");
    
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenu fileMenu = new JMenu("File");
    private static JMenuItem newMenuItem = new JMenuItem("New");
    private static JMenuItem openMenuItem = new JMenuItem("Open");
    private static JMenuItem saveMenuItem = new JMenuItem("Save");
    private static JMenuItem saveAsMenuItem = new JMenuItem("Save as");
    private static JMenuItem exitMenuItem = new JMenuItem(new ExitAction());
    
    private static JPanel windowContentPanel = new JPanel();
    
    private static JPanel editorPanel = new JPanel();
    
    private static JPanel infoControlPanel = new JPanel();
    
    private static JPanel infoPanel = new JPanel();
    private static JLabel titleLabel = new JLabel();
    
    private static JPanel controlPanel = new JPanel();
    private static JButton newPixelArtButton = new JButton("New");
    private static JButton savePixelArtButton = new JButton("Save");
    private static JButton saveAsPixelArtButton = new JButton("Save As");
    private static JButton openPixelArtButton = new JButton("Open");
    
    private static Border panelBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR);

    public Window() {
    	initializeWindowContentPanel();
    }
    
    private static void initializeWindowContentPanel() {
    	initializeMenuBar();
    	initializeEditorPanel();
        initializeInfoControlPanel();
        
    	windowContentPanel.setBackground(Constants.BACKGROUND_COLOR);
        windowContentPanel.setLayout(new BoxLayout(windowContentPanel, BoxLayout.LINE_AXIS));
    	windowContentPanel.setBorder(panelBorder);
        windowContentPanel.add(editorPanel);
        windowContentPanel.add(infoControlPanel);
        windowContentPanel.setOpaque(true);
    }
    
    private static void initializeMenuBar() {
        
    	fileMenu.add(newMenuItem);
    	fileMenu.add(openMenuItem);
    	fileMenu.add(saveMenuItem);
    	fileMenu.add(saveAsMenuItem);
    	fileMenu.addSeparator();
    	fileMenu.add(exitMenuItem);
    	
    	menuBar.add(fileMenu);
    	window.setJMenuBar(menuBar);
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
    	titleLabel.setForeground(Constants.ACCENT_COLOR);
    	titleLabel.setFont(Constants.APPLICATION_FONT);
    	
    	infoPanel.add(titleLabel);
    }
    
    private static void initializeControlPanel() {
    	controlPanel.setBackground(Constants.CONTROL_BACKGROUND_COLOR);
        controlPanel.setBorder(panelBorder);
        controlPanel.setPreferredSize(Constants.CONTROL_DIMENSION);
        controlPanel.setLayout(new GridLayout(2, 2, 5, 5));
        
        controlPanel.add(newPixelArtButton);
        controlPanel.add(openPixelArtButton);
        controlPanel.add(savePixelArtButton);
        controlPanel.add(saveAsPixelArtButton);
    }
    
    private static void initializeWindow() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(Constants.WINDOW_DIMENSION);
        window.setContentPane(windowContentPanel);
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        
        new Window();
        
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializeWindow();
            }
        });
    }
}