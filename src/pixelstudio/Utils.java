package pixelstudio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Utils {
	
	public static String rgbToHex(Color rgb) {
		String hex = "#";
		int red = rgb.getRed();
		int green = rgb.getGreen();
		int blue = rgb.getBlue();
		if ((red >= 0 && red < 256) && (green >= 0 && green < 256) && (blue >= 0 && blue < 256)) {
			hex += twoDigitHex(red);
			hex += twoDigitHex(green);
			hex += twoDigitHex(blue);
			return hex;
		}
		return null;
	}
	
	private static String twoDigitHex(int integer) {
		String formatted = Integer.toString(integer, 16);
		return formatted.length() == 2 ? formatted : "0" + formatted;
	}
	
	public static void formatButton(JButton button, String iconPath, String toolTipText) {
		button.setBackground(View.TOOLBAR_BACKGROUND_COLOR);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(25, 25));
		button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		ImageIcon icon = new ImageIcon(iconPath);
		icon.setImage(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		button.setIcon(icon);
		button.setToolTipText(toolTipText);
	}
	
}
