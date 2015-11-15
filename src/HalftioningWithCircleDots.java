
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author VanNinh
 */
public class HalftioningWithCircleDots {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width;
	private int height;

	public HalftioningWithCircleDots(BufferedImage parameterImage) {
		inputImage = parameterImage;
		width = inputImage.getWidth();
		height = inputImage.getHeight();
		outputImage = new BufferedImage(width *2  , (int) height *2, BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	public void processing() {

		Graphics2D g2d = outputImage.createGraphics();
		 g2d.setColor(Color.WHITE);
	     g2d.fillRect(0, 0, outputImage.getWidth(),outputImage.getHeight());
		
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = inputImage.getRGB(i, j);

				int alpha = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel) & 0xff;

				double sum = (+0.30 * r + 0.59 * g + 0.11 * b) / 255.0;
				sum = 1.0 - sum;
				double size = sum * Math.sqrt(2);
				g2d.fill(new Ellipse2D.Double((double) i *2  - size,
                        (double) j *2  - size,
                        size *2 , size *2 ));

			}
		}
		// outputImage = inputImage ;
	}

}
