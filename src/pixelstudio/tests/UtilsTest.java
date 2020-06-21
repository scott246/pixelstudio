package pixelstudio.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import pixelstudio.Utils;

class UtilsTest {

	@Test
	void testRgbToHex() {
		assertEquals("#000000", Utils.rgbToHex(new Color(0,0,0)));
		assertEquals("#ffffff", Utils.rgbToHex(new Color(255,255,255)));
		assertEquals("#00ffaa", Utils.rgbToHex(new Color(0,255,170)));
	}

}
