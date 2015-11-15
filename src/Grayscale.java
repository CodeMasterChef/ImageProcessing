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
public class Grayscale {

    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private int width ;
	private int height ;
    public Grayscale(BufferedImage parameterImage) {
        inputImage = parameterImage;
        width = inputImage.getWidth() ; 
        height = inputImage.getHeight() ; 
        outputImage = new BufferedImage( width , height , BufferedImage.TYPE_INT_ARGB);
    }


    public BufferedImage getOutputImage() {
        return outputImage;
    }

    public void processing() {
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                int pixel = inputImage.getRGB(i, j);
                int alpha = (pixel >> 24) & 0xff;
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = (pixel) & 0xff;
                
       
               int average = (r + g + b) / 3;
              //  int average = (int) (0.299*r + 0.587 * g + 0.114 * b ); 
                
                Color c = new Color(average, average, average, alpha);
                int k = c.getRGB();
                outputImage.setRGB(i, j, k);
            }
        }
    }

}
