
import java.io.File;
import java.io.IOException;

public class TestCut {
	public static void main(String args[]) throws IOException {
		String fileName = "C:\\Users\\Administrator\\Desktop\\新字符库";
		String firstBinaryDest = "C:\\Users\\Administrator\\Desktop\\temp";
		String firstCutDest = "G:\\IOTlab\\第一次分割字符库";
		String secondCutDest = "G:\\IOTlab\\第二次分割字符库";
		String secondBinaryDest = "G:\\IOTlab\\第一次分割后二值化字符库";

		File file = new File(fileName);
		if (!file.isDirectory()) {
			BinaryPicture.binaryPicture(fileName, firstBinaryDest);
		} else if (file.isDirectory()) {
			String[] filelist = file.list();

			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(fileName + "\\" + filelist[i]);

				if (!readfile.isDirectory()) {
					BinaryPicture.binaryPicture(fileName + "\\" + filelist[i], firstBinaryDest);
				}
			}
		}

		File binaryFile = new File(firstBinaryDest);
		if (!binaryFile.isDirectory()) {
			Cut.cut(firstBinaryDest, firstCutDest);
		} else if (binaryFile.isDirectory()) {
			String[] filelist = binaryFile.list();

			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(firstBinaryDest + "\\" + filelist[i]);
				Cut.cut(firstBinaryDest + "\\" + filelist[i], firstCutDest);
				if (!readfile.isDirectory()) {
					Cut.cut(firstBinaryDest + "\\" + filelist[i], firstCutDest);
				}
			}
		}

		File firstCutFile = new File(firstCutDest);
		if (!firstCutFile.isDirectory()) {
			BinaryPicture.binaryPicture(firstCutDest, secondBinaryDest);
		} else if (firstCutFile.isDirectory()) {
			String[] filelist = firstCutFile.list();

			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(firstCutDest + "\\" + filelist[i]);
				BinaryPicture.binaryPicture(firstCutDest + "\\" + filelist[i], secondBinaryDest);
				if (!readfile.isDirectory()) {
					BinaryPicture.binaryPicture(firstCutDest + "\\" + filelist[i], secondBinaryDest);
				}
			}
		}

		File secondBinaryFile = new File(secondBinaryDest);
		if (!secondBinaryFile.isDirectory()) {
			Cut.cut(secondBinaryDest, secondCutDest);
		} else if (secondBinaryFile.isDirectory()) {
			String[] filelist = secondBinaryFile.list();

			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(firstBinaryDest + "\\" + filelist[i]);
				Cut.cut(secondBinaryDest + "\\" + filelist[i], secondCutDest);
				if (!readfile.isDirectory()) {
					Cut.cut(secondBinaryDest + "\\" + filelist[i], secondCutDest);
				}
			}
		}
	}
}
