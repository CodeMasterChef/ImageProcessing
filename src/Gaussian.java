/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author VanNinh
 */
public class Gaussian {

    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private int width ;
	private int height ;
    public Gaussian(BufferedImage parameterImage) {
        inputImage = parameterImage;
        width = inputImage.getWidth() ; 
        height = inputImage.getHeight() ; 
        outputImage = new BufferedImage( width , height , BufferedImage.TYPE_INT_ARGB);
    }


    public BufferedImage getOutputImage() {
        return outputImage;
    }

    public void processing(int size, double sigma) {
        float[] kernel = new float[size * size];
   
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int off = y * size + x;
                int xx = x - size / 2;
                int yy = y - size / 2;
                kernel[off] = (float) ((1 / (2 * Math.PI * sigma * sigma)) * (Math.pow(Math.E, -(xx * xx + yy * yy)
                        / (2 * (sigma * sigma)))));
            }
        }
        BufferedImageOp edge = new ConvolveOp(new Kernel(size, size, kernel));
        outputImage = edge.filter(inputImage, null);// không hỗ trợ ảnh PNG

      
    }

}
