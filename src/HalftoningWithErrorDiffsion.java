/*
 * Use ERROR DIFFUSION for Halftoning
 * 
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author VanNinh
 */
public class HalftoningWithErrorDiffsion {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width;
	private int height;

	public HalftoningWithErrorDiffsion(BufferedImage parameterImage) {
		inputImage = parameterImage;
		width = inputImage.getWidth();
		height = inputImage.getHeight();
		outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	public void putPixel(int i, int j, int newColorValue) {

		Color c = new Color(newColorValue, newColorValue, newColorValue, 255);
		int k = c.getRGB();
		outputImage.setRGB(i, j, k);
	}

	public void processing() {
		int[][] matrix = new int[height][width];
		// transform to gray scale mode
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = inputImage.getRGB(i, j);
				//int alpha = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel) & 0xff;

				int average = (r + g + b) / 3;
				matrix[j][i] = average;
			}
		}
		// use kernel
		int threshold = 127 ; 
		int nearestColor , actualColour  ; 
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				if(matrix[j][i] < threshold) {
					putPixel(i, j, 0);
					nearestColor = 0 ;
					actualColour = matrix[j][i] ; 	
					matrix[j][i] = 0 ; 
				} else { 
					putPixel(i, j, 255);
					nearestColor = 255 ; 
					actualColour  = matrix[j][i] ; 
					matrix[j][i] = 255 ;
				}
				putPixel(i, j, nearestColor);
				
				// calculate for neighbor 
				int error = actualColour - nearestColor ; 
				if(i+1 < width ) { 
					int newColorValue = (int) (matrix[j][i+1] + 0.5 * error) ; 
					newColorValue = (newColorValue > 255) ? 255 : newColorValue;
					newColorValue = (newColorValue < 0) ? 0 : newColorValue;
					putPixel(i + 1, j, newColorValue);
					matrix[j][i+1] = newColorValue ; 
				}
				if(j+1 < height) { 
					int newColorValue = (int) (matrix[j+1][i] + 0.5 * error) ; 
					newColorValue = (newColorValue > 255) ? 255 : newColorValue;
					newColorValue = (newColorValue < 0) ? 0 : newColorValue;
					putPixel(i , j+1, newColorValue);
					matrix[j+1][i] = newColorValue ; 
				}
			}
		}

	}

}
