package pixelstudio;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Window {
    private static JFrame window = new JFrame("Pixel Studio");
    
    private static JPanel windowContentPanel = new JPanel();
    
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenu fileMenu = new JMenu("File");
    private static JMenuItem newMenuItem = new JMenuItem("New");
    private static JMenuItem openMenuItem = new JMenuItem("Open");
    private static JMenuItem saveMenuItem = new JMenuItem("Save");
    private static JMenuItem saveAsMenuItem = new JMenuItem("Save As");
    private static JMenuItem preferencesMenuItem = new JMenuItem("Preferences");
    private static JMenuItem exportMenuItem = new JMenuItem("Export");
    private static JMenuItem exitMenuItem = new JMenuItem(new ExitAction());
    
    private static JMenu editMenu = new JMenu("Edit");
    private static JMenuItem undoMenuItem = new JMenuItem("Undo");
    private static JMenuItem redoMenuItem = new JMenuItem("Redo");
    private static JMenuItem propertiesMenuItem = new JMenuItem("Properties");
    private static JMenuItem fillMenuItem = new JMenuItem("Fill");
    private static JMenuItem replaceColorMenuItem = new JMenuItem("Replace Color");
    private static JMenuItem dupeMenuItem = new JMenuItem("Dupe");
    private static JMenu flipMenu = new JMenu("Flip");
    private static JMenuItem flipHorizontalMenuItem = new JMenuItem("Horizontally");
    private static JMenuItem flipVerticalMenuItem = new JMenuItem("Vertically");
    private static JMenuItem flipDiagonalAscMenuItem = new JMenuItem("Diagonally (Ascending)");
    private static JMenuItem flipDiagonalDescMenuItem = new JMenuItem("Diagonally (Descending)");
    private static JMenu rotateMenu = new JMenu("Rotate");
    private static JMenuItem rotate90MenuItem = new JMenuItem("90°");
    private static JMenuItem rotate180MenuItem = new JMenuItem("180°");
    private static JMenuItem rotate270MenuItem = new JMenuItem("270°");
    private static JMenuItem shiftMenuItem = new JMenuItem("Shift");
    private static JMenuItem cropMenuItem = new JMenuItem("Crop");
    private static JMenuItem clearMenuItem = new JMenuItem("Clear");
    
    private static JMenu helpMenu = new JMenu("Help");
    private static JMenuItem helpGuideMenuItem = new JMenuItem("Help Guide");
    private static JMenuItem aboutMenuItem = new JMenuItem("About");
    
    private static JPanel editorPanel = new JPanel();
    
    private static JPanel infoPanel = new JPanel();
    private static JLabel helpTextLabel = new JLabel("Help text");
    private static JTextField titleTextField = new JTextField();
    private static JButton saveButton = new JButton("Save");
    
    private static JPanel controlPanel = new JPanel();
    private static JSpinner zoomSpinner = new JSpinner();
    private static JPanel mapOverviewPanel = new JPanel();
    private static JButton colorChooserButton = new JButton();
    private static JPanel colorHistoryPanel = new JPanel();
    private static JLabel selectedPaintColorLabel = new JLabel("#000000");
    private static JButton selectModeButton = new JButton("Select");
    private static JButton paintModeButton = new JButton("Paint");
    private static JLabel selectedPixelColorLabel = new JLabel("#FFFFFF");
    private static JLabel selectedPixelRangeLabel = new JLabel("0,0 : 1,0");
    
    private static Border panelBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR);

    public Window() {
    	initializeWindowContentPanel();
    }
    
    private static void initializeWindowContentPanel() {
    	initializeMenuBar();
    	initializeEditorPanel();
        initializeInfoPanel();
        initializeControlPanel();
        
    	windowContentPanel.setBackground(Constants.BACKGROUND_COLOR);
        windowContentPanel.setLayout(new BorderLayout());
    	windowContentPanel.setBorder(panelBorder);
        windowContentPanel.add(editorPanel, BorderLayout.CENTER);
        windowContentPanel.add(infoPanel, BorderLayout.NORTH);
        windowContentPanel.add(controlPanel, BorderLayout.EAST);
        windowContentPanel.setOpaque(true);
    }
    
    private static void initializeMenuBar() {
    	fileMenu.add(newMenuItem);
    	fileMenu.add(openMenuItem);
    	fileMenu.add(saveMenuItem);
    	fileMenu.add(saveAsMenuItem);
    	fileMenu.addSeparator();
    	fileMenu.add(preferencesMenuItem);
    	fileMenu.add(exportMenuItem);
    	fileMenu.addSeparator();
    	fileMenu.add(exitMenuItem);
    	menuBar.add(fileMenu);
    	
    	editMenu.add(undoMenuItem);
    	editMenu.add(redoMenuItem);
    	editMenu.addSeparator();
    	editMenu.add(fillMenuItem);
    	editMenu.add(replaceColorMenuItem);
    	editMenu.add(dupeMenuItem);
    	flipMenu.add(flipHorizontalMenuItem);
    	flipMenu.add(flipVerticalMenuItem);
    	flipMenu.add(flipDiagonalAscMenuItem);
    	flipMenu.add(flipDiagonalDescMenuItem);
    	editMenu.add(flipMenu);
    	rotateMenu.add(rotate90MenuItem);
    	rotateMenu.add(rotate180MenuItem);
    	rotateMenu.add(rotate270MenuItem);
    	editMenu.add(rotateMenu);
    	editMenu.add(shiftMenuItem);
    	editMenu.add(cropMenuItem);
    	editMenu.add(clearMenuItem);
    	editMenu.addSeparator();
    	editMenu.add(propertiesMenuItem);
    	menuBar.add(editMenu);
    	
    	helpMenu.add(helpGuideMenuItem);
    	helpMenu.add(aboutMenuItem);
    	menuBar.add(helpMenu);
    	
    	window.setJMenuBar(menuBar);
    }

	private static void initializeEditorPanel() {
    	editorPanel.setBackground(Constants.EDITOR_BACKGROUND_COLOR);
    	editorPanel.setBorder(panelBorder);
    	editorPanel.setPreferredSize(Constants.EDITOR_DIMENSION);
    }
    
    private static void initializeInfoPanel() {
    	infoPanel.setBackground(Constants.INFO_BACKGROUND_COLOR);
        infoPanel.setBorder(panelBorder);
        infoPanel.setPreferredSize(Constants.INFO_DIMENSION);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        
        infoPanel.add(helpTextLabel);
        infoPanel.add(titleTextField);
	    infoPanel.add(saveButton);
    }
    
    private static void initializeControlPanel() {
    	controlPanel.setBackground(Constants.CONTROL_BACKGROUND_COLOR);
        controlPanel.setBorder(panelBorder);
        controlPanel.setPreferredSize(Constants.CONTROL_DIMENSION);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        
        controlPanel.add(zoomSpinner);
        controlPanel.add(mapOverviewPanel);
        controlPanel.add(colorChooserButton);
        controlPanel.add(colorHistoryPanel);
        controlPanel.add(selectedPaintColorLabel);
        controlPanel.add(selectModeButton);
        controlPanel.add(paintModeButton);
        controlPanel.add(selectedPixelColorLabel);
        controlPanel.add(selectedPixelRangeLabel);
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
        new Window();
        
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializeWindow();
            }
        });
    }
}