import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestDrawRectangle {
	public static void drawRectangle(String inputFilePath, String outputFilePath, int x0, int y0, int x1, int y1)
			throws IOException {
		File inputFile = new File(inputFilePath);
		BufferedImage image = ImageIO.read(inputFile);
		for (int i = x0; i < x1; i++) {
			image.setRGB(i, y0, Color.red.getRGB());
			image.setRGB(i, y1, Color.red.getRGB());
		}

		for (int i = y0; i < y1; i++) {
			image.setRGB(x0, i, Color.red.getRGB());
			image.setRGB(x1, i, Color.red.getRGB());
		}
		File outputFile = new File(outputFilePath);
		ImageIO.write(image, "jpg", outputFile);
	}

	public static void main(String args[]) throws IOException {
		drawRectangle("C:\\Users\\Administrator\\Desktop\\1.jpg", "C:\\Users\\Administrator\\Desktop\\2.jpg", 200, 400,
				300, 500);
	}
}
