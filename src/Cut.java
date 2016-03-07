
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class Cut {
	static int a = 0;

	public static void cut(String inputFilePath, String outputFilePath) throws IOException {
		File inputFile = new File(inputFilePath);
		BufferedImage image = ImageIO.read(inputFile);

		int num = -1;
		int width = image.getWidth();
		int height = image.getHeight();
		int row[] = new int[height];
		int column[] = new int[width];
		int cutrow[][] = new int[height][width];
		int cutcolumn[][] = new int[width][height];
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		Raster ra = image.getData();
		Rectangle rect = ra.getBounds();

		int imagePixels[] = new int[rect.height * rect.width];
		imagePixels = ra.getPixels(0, 0, rect.width, rect.height, imagePixels);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				num++;
				// cutrow[i][j] = image.getRGB(j, i);
				cutrow[i][j] = imagePixels[num];
			}
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cutcolumn[i][j] = cutrow[j][i];
				// System.out.println(cutcolumn[i][j]);
			}
		}

		for (int i = 0; i < height; i++) {
			int line = 0;
			for (int j = 0; j < width; j++) {
				if (cutrow[i][j] == 0) {
					line++;
				}
			}
			row[i] = line;
		}

		for (int i = 0; i < width; i++) {
			int list = 0;
			for (int j = 0; j < height; j++) {
				if (cutcolumn[i][j] == 0) {
					list++;
				}
			}
			column[i] = list;
		}

		for (int i = 1; i < row.length; i++) {
			boolean leftlimit = row[i - 1] == 0 && row[i] != 0;
			boolean rightlimit = row[i - 1] != 0 && row[i] == 0;
			if (leftlimit || rightlimit) {
				x.add(i);
			}
		}

		for (int i = 1; i < column.length; i++) {
			boolean leftlimit = column[i - 1] == 0 && column[i] != 0;
			boolean rightlimit = column[i - 1] != 0 && column[i] == 0;
			if (leftlimit || rightlimit) {
				y.add(i);
			}
		}
		int xLimits[] = new int[x.size()];
		int yLimits[] = new int[y.size()];

		for (int i = 0; i < xLimits.length; i++) {
			xLimits[i] = x.get(i);
			// System.out.println(xLimits[i]);
		}

		for (int i = 0; i < yLimits.length; i++) {
			yLimits[i] = y.get(i);
			System.out.println(yLimits[i]);
		}

		if (x.size() != 0 && x.size() != 1) {
			for (int l = 1; l < xLimits.length; l++) {
				int xStart = xLimits[l - 1];// 186
				int xEnd = xLimits[l];// 301
				for (int m = 1; m < yLimits.length; m++) {

					int yStart = yLimits[m - 1];// 544
					int yEnd = yLimits[m];// 650

					BufferedImage cutImage = new BufferedImage(yEnd - yStart, xEnd - xStart,
							BufferedImage.TYPE_BYTE_BINARY);

					a++;
					File pfile = new File(outputFilePath + "\\" + a + ".jpg");

					double all = 0.0;
					double white = 0.0;
					for (int j = xStart; j < xEnd; j++) {
						for (int k = yStart; k < yEnd; k++) {

							int rgb = image.getRGB(k, j);
							cutImage.setRGB(k - yStart, j - xStart, rgb);
							if (rgb == -1) {
								white++;
							}
							all++;
						}
					}
					if (white / all <= 0.9) {
						ImageIO.write(cutImage, "jpg", pfile);
					}

				}

			}
		} else {
			for (int m = 1; m < yLimits.length; m++) {

				int yStart = yLimits[m - 1];// 544
				int yEnd = yLimits[m];// 650

				BufferedImage cutImage = new BufferedImage(yEnd - yStart, height - 0, BufferedImage.TYPE_BYTE_BINARY);

				a++;
				File pfile = new File(outputFilePath + "\\" + a + ".jpg");

				double all = 0.0;
				double white = 0.0;
				for (int j = 0; j < height; j++) {
					for (int k = yStart; k < yEnd; k++) {

						int rgb = image.getRGB(k, j);
						cutImage.setRGB(k - yStart, j - 0, rgb);
						if (rgb == -1) {
							white++;
						}
						all++;
					}
				}
				if (white / all <= 0.9) {
					ImageIO.write(cutImage, "jpg", pfile);
				}

			}

		}
	}

	public static void main(String args[]) throws IOException {

		cut("C:\\Users\\Administrator\\Desktop\\¶þÖµ»¯×Ö·û¿â\\Agency FB.png", "G:\\IOTlab\\×Ö·û¿â");

	}
}
