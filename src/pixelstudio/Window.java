package pixelstudio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static Window windowInstance;
	
    protected static final int WINDOW_WIDTH = 600;
    protected static final int WINDOW_HEIGHT = 600;
    protected static final int BORDER_SIZE = 5;
    protected static final int PADDING = BORDER_SIZE * 2;
    protected static final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    protected static final Dimension TOOLBAR_DIMENSION = new Dimension(WINDOW_WIDTH, 30+PADDING);
    protected static final Dimension EDITOR_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT-(TOOLBAR_DIMENSION.height)*2);
    protected static final Dimension BUTTON_DIMENSION = new Dimension(100, 30);
	
    protected static final Color BACKGROUND_COLOR = new Color(32, 32, 32);
    protected static final Color EDITOR_BACKGROUND_COLOR = new Color(64, 64, 64);
    protected static final Color TOOLBAR_BACKGROUND_COLOR = new Color(64, 64, 64);
    protected static final Color FOREGROUND_COLOR = new Color(255, 255, 255);
    protected static final Color ACCENT_COLOR = new Color(255, 128, 0, 255);
    protected static final Color SELECTION_COLOR = new Color(255, 128, 0, 64);
    protected static final Color TRANSPARENT_COLOR = Color.LIGHT_GRAY;
    protected static final Color WARNING_COLOR = new Color(255, 255, 0);
	
    protected static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 12);
    protected static final Font MONO_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	
	protected static JFrame window = new JFrame("Pixel Studio");
    protected static JPanel windowContentPanel = new JPanel();
    
    protected static JMenuBar menuBar = new JMenuBar();
    protected static JMenu fileMenu = new JMenu("File");
    protected static JMenuItem newMenuItem = new JMenuItem("New");
    protected static JMenuItem openMenuItem = new JMenuItem("Open");
    protected static JMenuItem saveMenuItem = new JMenuItem("Save");
    protected static JMenuItem saveAsMenuItem = new JMenuItem("Save As");
    protected static JMenuItem preferencesMenuItem = new JMenuItem("Preferences");
    protected static JMenuItem exportMenuItem = new JMenuItem("Export");
    protected static JMenuItem exitMenuItem = new JMenuItem(new ExitAction());
    
    protected static JMenu editMenu = new JMenu("Edit");
    protected static JMenuItem undoMenuItem = new JMenuItem("Undo");
    protected static JMenuItem redoMenuItem = new JMenuItem("Redo");
    protected static JMenuItem propertiesMenuItem = new JMenuItem("Properties");
    protected static JMenuItem fillMenuItem = new JMenuItem("Fill");
    protected static JMenuItem replaceColorMenuItem = new JMenuItem("Replace Color");
    protected static JMenuItem dupeMenuItem = new JMenuItem("Dupe");
    protected static JMenu flipMenu = new JMenu("Flip");
    protected static JMenuItem flipHorizontalMenuItem = new JMenuItem("Horizontally");
    protected static JMenuItem flipVerticalMenuItem = new JMenuItem("Vertically");
    protected static JMenuItem flipDiagonalAscMenuItem = new JMenuItem("Diagonally (Ascending)");
    protected static JMenuItem flipDiagonalDescMenuItem = new JMenuItem("Diagonally (Descending)");
    protected static JMenu rotateMenu = new JMenu("Rotate");
    protected static JMenuItem rotate90MenuItem = new JMenuItem("90°");
    protected static JMenuItem rotate180MenuItem = new JMenuItem("180°");
    protected static JMenuItem rotate270MenuItem = new JMenuItem("270°");
    protected static JMenuItem shiftMenuItem = new JMenuItem("Shift");
    protected static JMenuItem cropMenuItem = new JMenuItem("Crop");
    protected static JMenuItem clearMenuItem = new JMenuItem("Clear");
    
    protected static JMenu helpMenu = new JMenu("Help");
    protected static JMenuItem helpGuideMenuItem = new JMenuItem("Help Guide");
    protected static JMenuItem aboutMenuItem = new JMenuItem("About");
    
    protected static JPanel editorPanel = new JPanel();
    protected static JPanel editorCanvasPanel = new JPanel();
    //TODO: make below variables dynamic
    protected static int editorPixelCountX = 10;
    protected static int editorPixelCountY = 10;
    protected static Dimension editorSize = new Dimension(300, 300);
    protected static int editorPixelWidth = editorSize.width/editorPixelCountX;
    protected static int editorPixelHeight = editorSize.height/editorPixelCountY;

	protected static boolean leftMouseDown = false;
	protected static boolean rightMouseDown = false;
	protected static boolean isPaintMode = true;
    
    protected static JPanel upperToolbarPanel = new JPanel();
    protected static JLabel helpTextLabel = new JLabel("Help text");
    protected static JLabel titleLabel = new JLabel("Title");
    protected static JButton saveButton = new JButton("Save");
    protected static JLabel mousePositionLabel = new JLabel("0:0");
    
    protected static JPanel lowerToolbarPanel = new JPanel();
    protected static JButton paintModeButton = new JButton("Paint");
    protected static JButton selectModeButton = new JButton("Select");
    protected static JButton chooseColorButton = new JButton(" ");
    protected static JLabel selectedPaintColorLabel = new JLabel("#000000");
    protected static Color currentBrushColor = new Color(0, 0, 0, 0);
    
    protected static final Border PANEL_BORDER = BorderFactory.createMatteBorder(
    		BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BACKGROUND_COLOR);
    protected static final Border EDITOR_PANEL_BORDER = BorderFactory.createMatteBorder(
    		BORDER_SIZE, 0, BORDER_SIZE, 0, BACKGROUND_COLOR);
    protected static final Border INNER_PANEL_BORDER = BorderFactory.createEmptyBorder(
    		BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
    protected static final Border PIXEL_BORDER = BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(1, 1, 1, 1), 
			BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
    protected static final Border SELECTED_PIXEL_BORDER = BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(1, 1, 1, 1), 
			BorderFactory.createMatteBorder(1, 1, 1, 1, ACCENT_COLOR));
    
    protected static ArrayList<Pixel> selectedPixels = new ArrayList<Pixel>();
    protected static ArrayList<Pixel> allPixels = new ArrayList<Pixel>();

    public Window() {
    	initializeWindowContentPanel();
    }
    
    private static void initializeWindowContentPanel() {
    	initializeMenuBar();
    	initializeEditorPanel();
        initializeUpperToolbarPanel();
        initializeLowerToolbarPanel();
        
    	windowContentPanel.setBackground(BACKGROUND_COLOR);
        windowContentPanel.setLayout(new BorderLayout());
    	windowContentPanel.setBorder(PANEL_BORDER);
        windowContentPanel.add(editorPanel, BorderLayout.CENTER);
        windowContentPanel.add(upperToolbarPanel, BorderLayout.NORTH);
        windowContentPanel.add(lowerToolbarPanel, BorderLayout.SOUTH);
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
    	editorPanel.setBackground(EDITOR_BACKGROUND_COLOR);
    	editorPanel.setBorder(EDITOR_PANEL_BORDER);
    	editorPanel.setPreferredSize(EDITOR_DIMENSION);
    	editorCanvasPanel.setPreferredSize(editorSize);
    	editorCanvasPanel.setLayout(new GridLayout(editorPixelCountX, editorPixelCountY));
    	for (int i = 0; i < editorPixelCountX; i++) {
    		for (int j = 0; j < editorPixelCountY; j++) {
    			editorCanvasPanel.add(new Pixel(i, j));
    		}
    	}
    	editorPanel.add(editorCanvasPanel);
    }
    
    private static void initializeUpperToolbarPanel() {
    	upperToolbarPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
    	upperToolbarPanel.setBorder(INNER_PANEL_BORDER);
    	upperToolbarPanel.setPreferredSize(TOOLBAR_DIMENSION);
    	upperToolbarPanel.setLayout(new BoxLayout(upperToolbarPanel, BoxLayout.X_AXIS));

    	paintModeButton.setBackground(ACCENT_COLOR);
        paintModeButton.setPreferredSize(BUTTON_DIMENSION);
        paintModeButton.setFocusPainted(false);
        paintModeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Actions.setPaintState(true);
			}
        });
        upperToolbarPanel.add(paintModeButton);
    	selectModeButton.setFocusPainted(false);
    	selectModeButton.setPreferredSize(BUTTON_DIMENSION);
    	selectModeButton.setBackground(TRANSPARENT_COLOR);
    	selectModeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Actions.setPaintState(false);
			}
    	});
    	upperToolbarPanel.add(selectModeButton);
        
    	upperToolbarPanel.add(Box.createHorizontalGlue());

        selectedPaintColorLabel.setFont(MONO_FONT);
        selectedPaintColorLabel.setForeground(FOREGROUND_COLOR);
        selectedPaintColorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        upperToolbarPanel.add(selectedPaintColorLabel);
        upperToolbarPanel.add(Box.createRigidArea(new Dimension(BORDER_SIZE, 0)));
        currentBrushColor = Color.BLACK;
        chooseColorButton.setBackground(currentBrushColor);
        chooseColorButton.setFocusPainted(false);
		chooseColorButton.setPreferredSize(BUTTON_DIMENSION);
        chooseColorButton.addActionListener(new ColorChoiceAction());
        upperToolbarPanel.add(chooseColorButton);
    }
    
    private static void initializeLowerToolbarPanel() {
    	lowerToolbarPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
    	lowerToolbarPanel.setBorder(INNER_PANEL_BORDER);
    	lowerToolbarPanel.setPreferredSize(TOOLBAR_DIMENSION);
    	lowerToolbarPanel.setLayout(new BoxLayout(lowerToolbarPanel, BoxLayout.X_AXIS));
    	
    	saveButton.setFocusPainted(false);
    	saveButton.setPreferredSize(BUTTON_DIMENSION);
    	lowerToolbarPanel.add(saveButton);
        lowerToolbarPanel.add(Box.createRigidArea(new Dimension(BORDER_SIZE, 0)));
        titleLabel.setFont(LABEL_FONT);
        titleLabel.setForeground(FOREGROUND_COLOR);
        lowerToolbarPanel.add(titleLabel);
        
        lowerToolbarPanel.add(Box.createHorizontalGlue());
        
        helpTextLabel.setFont(LABEL_FONT);
        helpTextLabel.setForeground(WARNING_COLOR);
        helpTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lowerToolbarPanel.add(helpTextLabel);
        lowerToolbarPanel.add(Box.createRigidArea(new Dimension(BORDER_SIZE, 0)));
        mousePositionLabel.setFont(LABEL_FONT);
        mousePositionLabel.setForeground(FOREGROUND_COLOR);
        mousePositionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lowerToolbarPanel.add(mousePositionLabel);
    }
    
    static void initializeWindow() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(WINDOW_DIMENSION);
        window.setContentPane(windowContentPanel);
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        windowInstance = new Window();
        
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	initializeWindow();
            }
        });
    }
}