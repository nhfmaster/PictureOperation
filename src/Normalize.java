
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class Normalize {
	public static void normalizePicture(int width, int height, String inputfilepath, String outputfilepath)
			throws IOException {
		File inputfile = new File(inputfilepath);
		File outputfile = new File(outputfilepath);
		if (!inputfile.isDirectory()) {
			OutputStream opt = new FileOutputStream(outputfile);

			BufferedImage prevImage = ImageIO.read(inputfile);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

			Graphics graphics = image.createGraphics();
			graphics.drawImage(prevImage, 0, 0, width, height, null);
			ImageIO.write(image, "jpg", opt);

			opt.flush();
			opt.close();

		} else if (inputfile.isDirectory()) {
			String[] filelist = inputfile.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(inputfilepath + "\\" + filelist[i]);
				File outfile = new File(outputfilepath + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
					OutputStream opt = new FileOutputStream(outfile);

					BufferedImage prevImage = ImageIO.read(readfile);
					BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

					Graphics graphics = image.createGraphics();
					graphics.drawImage(prevImage, 0, 0, width, height, null);
					ImageIO.write(image, "jpg", opt);

					opt.flush();
					opt.close();

				} else if (readfile.isDirectory()) {
					normalizePicture(width, height, inputfilepath + "\\" + filelist[i],
							outputfilepath + "\\" + filelist[i]);
				}
			}

		}

	}

	public static void main(String args[]) throws IOException {
		normalizePicture(28, 28, "G:\\IOTlab\\第二次分割字符库", "G:\\IOTlab\\第二次分割后归一化字符库");
	}
}
