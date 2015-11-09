

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


public class LaplaceOfGaussian {

    private BufferedImage inputImage;
    private BufferedImage outputImage;
    private int width ;
   	private int height ;
   
    public BufferedImage getOutputImage(){
        return outputImage;
    }
    public LaplaceOfGaussian(BufferedImage input) {
    	inputImage = input;
    	 width = inputImage.getWidth() ; 
         height = inputImage.getHeight() ; 
         outputImage = new BufferedImage( width , height , BufferedImage.TYPE_INT_ARGB);
	}
    
    public BufferedImage processing(  int size, double sigma)  {
        double deviation = sigma;

        double[][] ret = new double[size][size];
        int half = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double r = Math.sqrt((i - half) * (i - half) + (j - half) * (j - half));
                ret[i][j]
                        = ((r * r - 2 * deviation * deviation) / Math.pow(deviation, 4)) * Math.exp(-r * r / (2 * deviation * deviation));
            }
        }
        int c = 0;
        float tam[] = new float[size * size];
        for (int q = 0; q < size; q++) {
            for (int w = 0; w < size; w++) {
                tam[c] = (float) ret[q][w];
                c++;
            }
        }
        BufferedImageOp edge = new ConvolveOp(new Kernel(size, size, tam)); 
        outputImage = edge.filter(inputImage, null);// không hỗ trợ ảnh PNG
        return outputImage;
    }
}

