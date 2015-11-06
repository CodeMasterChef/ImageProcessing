
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author VanNinh
 */
public class OilPainting {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width;
	private int height;

	public OilPainting(BufferedImage parameterImage) {
		inputImage = parameterImage;
		width = inputImage.getWidth();
		height = inputImage.getHeight();
		outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	public void processing(int radius, int intensityLevels) {

		int averageR[] = new int[intensityLevels + 1];
		int averageG[] = new int[intensityLevels + 1];
		int averageB[] = new int[intensityLevels + 1];
		int intensityCount[] = new int[intensityLevels + 1];

		for (int x = 0; x < width; x++) { // for (each pixel)
			int left = Math.max(0, x - radius);
			int right = Math.min(x + radius, width - 1);

			for (int y = 0; y < height; y++) {
				int top = Math.max(0, y - radius);
				int bottom = Math.min(y + radius, height - 1);

				Arrays.fill(averageR, 0);
				Arrays.fill(averageG, 0);
				Arrays.fill(averageB, 0);
				Arrays.fill(intensityCount, 0);
				int maxIndex = -1;

				for (int j = top; j <= bottom; j++) { // for (each pixel,
														// within radius r
														// of pixel)

					for (int i = left; i <= right; i++) {

						if (!inRange(x, y, i, j, radius)) {
							continue;
						}

						int pixel = inputImage.getRGB(i, j);
						//int alpha = (pixel >> 24) & 0xff;
						int r = (pixel >> 16) & 0xff;
						int g = (pixel >> 8) & 0xff;
						int b = (pixel) & 0xff;

						int curIntensity = (int) ((((r + g + b) / 3.0) / 255.0) * intensityLevels);
						intensityCount[curIntensity]++;
						averageR[curIntensity] += r;
						averageG[curIntensity] += g;
						averageB[curIntensity] += b;

						if (maxIndex == -1 || intensityCount[maxIndex] < intensityCount[curIntensity]) {
							maxIndex = curIntensity;
						}

					}
				}
				// Step 3, calculate the final value
				int curMax = intensityCount[maxIndex];
				int r = averageR[maxIndex] / curMax;
				int g = averageG[maxIndex] / curMax;
				int b = averageB[maxIndex] / curMax;

				Color newColor = new Color(r, g, b, 255);
				int newColorValue = newColor.getRGB();
				outputImage.setRGB(x, y, newColorValue);

			}
		}
	}

	private boolean inRange(int cx, int cy, int i, int j, int radius) {
		double d = Point2D.distance(i, j, cx, cy);
		return d < radius;
	}

}
