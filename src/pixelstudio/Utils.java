package pixelstudio;

import java.awt.Color;

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
	
	public static boolean mouseDown = false;
}
