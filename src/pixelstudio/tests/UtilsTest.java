package pixelstudio.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import pixelstudio.Utils;

class UtilsTest {

	@Test
	void testRgbToHex() {
		assertEquals("#00000000", Utils.rgbToHex(0, 0, 0, 0));
		assertEquals("#ffffffff", Utils.rgbToHex(255, 255, 255, 255));
		assertEquals("#00ff01", Utils.rgbToHex(0, 255, 1));
		assertEquals(null, Utils.rgbToHex(256, 233, 132, 2));
		assertEquals(null, Utils.rgbToHex(1, 2, 3, -1));
	}

	@Test
	void testHexToRGB() {
		assertEquals(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0)), Utils.hexToRGB("#00000000"));
		assertEquals(new ArrayList<Integer>(Arrays.asList(255, 255, 255, 255)), Utils.hexToRGB("#ffffffff"));
		assertEquals(new ArrayList<Integer>(Arrays.asList(170, 0, 255)), Utils.hexToRGB("#aa00ff"));
		assertEquals(null, Utils.hexToRGB("#0123456"));
		assertEquals(null, Utils.hexToRGB("012345"));
	}

}
