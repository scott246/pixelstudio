package pixelstudio;

import java.util.ArrayList;

public class Utils {

	public static String rgbToHex(int red, int green, int blue, int alpha) {
		String hex = rgbToHex(red, green, blue);
		if (hex == null) return null;
		if (alpha >= 0 && alpha < 256) {
			hex += twoDigitHex(alpha);
			return hex;
		}
		return null;
	}
	
	public static String rgbToHex(int red, int green, int blue) {
		String hex = "#";
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
	
	public static ArrayList<Integer> hexToRGB(String hex) {
		ArrayList<Integer> rgb = new ArrayList<Integer>();
		if (!hex.contains("#")) return null;
		if (hex.length() != 7 && hex.length() != 9) return null;
		try {
			rgb.add(Integer.decode("0x" + hex.substring(1, 3)));
			rgb.add(Integer.decode("0x" + hex.substring(3, 5)));
			rgb.add(Integer.decode("0x" + hex.substring(5, 7)));
			if (hex.length() == 9)
				rgb.add(Integer.decode("0x" + hex.substring(7, 9)));
		} catch (NumberFormatException e) {
			return null;
		}
		return rgb;
	}
}
