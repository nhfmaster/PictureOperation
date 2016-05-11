import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestDrawRectangle {
	public static void mark(String srcImgPath, String outImgPath, Color markContentColor, String waterMarkContent,
			int x, int y) {
		try {
			File srcImgFile = new File(srcImgPath);
			Image srcImg = ImageIO.read(srcImgFile);
			int srcImgWidth = srcImg.getWidth(null);
			int srcImgHeight = srcImg.getHeight(null);
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			Font font = new Font("ËÎÌå", Font.PLAIN, 50);
			g.setColor(markContentColor);

			g.setFont(font);
			g.drawString(waterMarkContent, x, y);
			g.dispose();
			// Êä³öÍ¼Æ¬
			FileOutputStream outImgStream = new FileOutputStream(outImgPath);
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		mark("C:\\Users\\Administrator\\Desktop\\2.jpg", "C:\\Users\\Administrator\\Desktop\\3.jpg", Color.red, "Ë®",
				200, 400);
	}
}
