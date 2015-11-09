import java.awt.Color;
import java.awt.image.BufferedImage;

public class BitPlaneSlicing {
	  private BufferedImage inputImage;
	    private BufferedImage outputImage;
	    private int width ;
		private int height ;
		
		  int [] mangcatlop = new int[8];
		
	    public BitPlaneSlicing(BufferedImage parameterImage) {
	        inputImage = parameterImage;
	        width = inputImage.getWidth() ; 
	        height = inputImage.getHeight() ; 
	        outputImage = new BufferedImage( width , height , BufferedImage.TYPE_INT_ARGB);
	    }
	    public int[] binaryNumber(int number)
	    {
	        int[] np = new int[9];
	         for(int i= 0; i<8;i++){
	           np[i]=0;
	        }
	        int sodu;
	        for(int i= 0; i<8;i++){
	           sodu =number%2;
	           np[7-i]=sodu;
	           number=(number-sodu)/2;
	        }
	        return np;
	    }

	    public BufferedImage getOutputImage() {
	        return outputImage;
	    }
	    public void processing(int[] PlanceSlicingArray){
	    	
	    	
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < height; j++) {
	                    int alpha = 255;
	                    int pi = inputImage.getRGB(i,j);
	                            alpha = (pi >> 24) & 0xff;
	                            int r = (pi >> 16) & 0xff;
	                            int g = (pi >> 8) & 0xff;
	                            int b = (pi) & 0xff;
	                            int[] binaryValueOfRed = binaryNumber(r);
	                            int[] binaryValueOfBlue = binaryNumber(g);
	                            int[] binaryValueOfGreen = binaryNumber(b);
	                            
	                            int r1=Catlopt(binaryValueOfRed, PlanceSlicingArray);
	                            int g1=Catlopt(binaryValueOfBlue, PlanceSlicingArray);
	                            int b1=Catlopt(binaryValueOfGreen, PlanceSlicingArray);
	                                                       
	                            Color cx = new Color(r1,g1,b1, alpha);
	                            int tx = cx.getRGB();
	                            outputImage.setRGB(i, j, tx);
	                            
	                        }
	                    }          
	    }
	    public int binaryToDecimal(int[] mang){
	         int tong=0;
	         for(int i= 7; i>-1 ; i--){
	             tong=(int) (tong+(Math.pow(2, (7-i)))*mang[i]);
	         }
	         return tong;
	     }
	     
	     public int Catlopt(int[] mang, int[] mangcatlop){
	        int[] manglop = new int[8];
	         for(int i = 0; i<8;i++){
	         manglop[i]=0;
	        }
	        for(int i = 0;i<8;i++){
	         if(mangcatlop[i]!=0){
	             manglop[i]=mang[i];
	         }
	     }
	         
	         return binaryToDecimal(manglop);
	     }
	    
}
