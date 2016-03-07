
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BinaryPicture {
	public static void binaryPicture(String inputfilepath, String outputfilepath) throws IOException {
		File inputfile = new File(inputfilepath);
		String fileName = inputfile.getName();
		File outputfile = new File(outputfilepath);
		if (!inputfile.isDirectory()) {
			try {
				BufferedImage image = ImageIO.read(inputfile);
				File outFile = new File(outputfile + "\\" + fileName);
				int width = image.getWidth();
				int height = image.getHeight();

				BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						int rgb = image.getRGB(i, j);
						grayImage.setRGB(i, j, rgb);
					}
				}
				ImageIO.write(grayImage, "png", outFile);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

		} else if (inputfile.isDirectory()) {
			String[] filelist = inputfile.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(inputfilepath + "\\" + filelist[i]);
				File outfile = new File(outputfilepath + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
					try {
						BufferedImage image = ImageIO.read(readfile);

						int width = image.getWidth();
						int height = image.getHeight();

						BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
						for (int j = 0; j < width; j++) {
							for (int k = 0; k < height; k++) {
								int rgb = image.getRGB(j, k);
								grayImage.setRGB(j, k, rgb);
							}
						}

						ImageIO.write(grayImage, "png", outfile);
					} catch (NullPointerException e) {
						e.printStackTrace();
					}

				} else if (readfile.isDirectory()) {
					binaryPicture(inputfilepath + "\\" + filelist[i], outputfilepath + "\\" + filelist[i]);
				}
			}

		}

	}

	public static void main(String args[]) throws IOException {
		binaryPicture("G:\\IOTlab\\第二次分割后归一化字符库", "G:\\IOTlab\\第二次分割后二值化字符库");
	}
}