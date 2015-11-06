/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author VanNinh
 */
public class Sobel {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width;
	private int height;

	public Sobel(BufferedImage parameterImage) {
		inputImage = parameterImage;
		outputImage = new BufferedImage(parameterImage.getWidth(), parameterImage.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		width = parameterImage.getWidth();
		height = parameterImage.getHeight();
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	public void processing() {
		int[][] Gx = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
		int[][] Gy = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				int pixelGx[] = new int[4];
				int pixelGy[] = new int[4];
				int pixelSobel[] = new int[4];

				int k, m, channel;
				for (k = -1; k <= 1; k++) {
					for (m = -1; m <= 1; m++) {
						if (i + m < 0 || j + k < 0 || i + m > width - 1 || j + k > height - 1) {
							break;
						}
						int pi = inputImage.getRGB(i + m, j + k);
						int alpha = (pi >> 24) & 0xff;
						int r = (pi >> 16) & 0xff;
						int g = (pi >> 8) & 0xff;
						int b = (pi) & 0xff;
						
						// tiến hành nhân chập: tọa độ: 2-(k+1) = 1-k
						pixelGx[0] += Gx[1 - k][1 - m] * r;
						pixelGx[1] += Gx[1 - k][1 - m] * g;
						pixelGx[2] += Gx[1 - k][1 - m] * b;
						pixelGx[3] += Gx[1 - k][1 - m] * alpha;

						// tiến hành nhân chập: tọa độ: 2-(k+1) = 1-k
						pixelGy[0] += Gy[1 - k][1 - m] * r;
						pixelGy[1] += Gy[1 - k][1 - m] * g;
						pixelGy[2] += Gy[1 - k][1 - m] * b;
						pixelGy[3] += Gy[1 - k][1 - m] * alpha;

					}
				}
				for (channel = 0; channel < 4; channel++) // xet tren 4 kenh mau
				{

					// cong 2 ma tran lai
					pixelSobel[channel] = pixelGx[channel] + pixelGy[channel];
					pixelSobel[channel] = (pixelSobel[channel] < 0) ? 0 : pixelSobel[channel];
					pixelSobel[channel] = (pixelSobel[channel] > 255) ? 255 : pixelSobel[channel];

				}
				// sau khi xu ly xong thi bo vao anh
			
				Color newColor = new Color(pixelSobel[0], pixelSobel[1], pixelSobel[2] , 255); // set alpha = 255
				int newColorValue = newColor.getRGB();
				outputImage.setRGB(i, j, newColorValue);

			}
		}

	}

}