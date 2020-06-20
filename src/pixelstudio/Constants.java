package pixelstudio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Constants {
	
	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 900;
	public static final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	public static final Dimension EDITOR_DIMENSION = new Dimension(3*WINDOW_WIDTH/4, 3*WINDOW_HEIGHT/4);
	public static final Dimension INFO_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT/4);
	public static final Dimension CONTROL_DIMENSION = new Dimension(WINDOW_WIDTH/4, WINDOW_HEIGHT-INFO_DIMENSION.height);
	
	public static final Color BACKGROUND_COLOR = new Color(32, 32, 32, 255);
	public static final Color EDITOR_BACKGROUND_COLOR = new Color(64, 64, 64, 255);
	public static final Color INFO_BACKGROUND_COLOR = new Color(64, 64, 64, 255);
	public static final Color CONTROL_BACKGROUND_COLOR = new Color(64, 64, 64, 255);
	public static final Color FOREGROUND_COLOR = new Color(255, 255, 255, 255);
	public static final Color ACCENT_COLOR = new Color(255, 128, 0, 255);
}
