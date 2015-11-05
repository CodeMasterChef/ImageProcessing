/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Dao Trung Duyet
 */
public class HistogramDuyet {

    private BufferedImage inputImage;
    private BufferedImage outputImage;

    public HistogramDuyet(BufferedImage inputImage) {
        this.inputImage = inputImage;
    }

    public void setInputImage(BufferedImage inputImage) {
        this.inputImage = inputImage;
    }

    public BufferedImage getOutputImage() {
//        outputImage = canBangHistoGram();
        return outputImage;
    }

    public void histogramEqualization() {
        // lay gia tri histogram cua anh.
        int[][] imageHist = imageHistogram(inputImage);
        int[][] imageHistNew = new int[3][256];

        int[] rhist = new int[256];
        int[] ghist = new int[256];
        int[] bhist = new int[256];

        for (int i = 0; i < rhist.length; i++) {
            rhist[i] = 0;
        }
        for (int i = 0; i < ghist.length; i++) {
            ghist[i] = 0;
        }
        for (int i = 0; i < bhist.length; i++) {
            bhist[i] = 0;
        }
        float sumr = 0;
        float sumg = 0;
        float sumb = 0;
        //tong so pixel
        int area = inputImage.getWidth() * inputImage.getHeight();
        // tinh ti? le cua 256 muc mau tren tong so diem anh
        float tiLe = (float) (256.0 / area);
        int GoR=0, GoG=0, GoB=0;
        for(int i=0; i<256;i++){
            if(imageHist[0][i]!=0){
                GoR+=1;
            }
            if(imageHist[1][i]!=0){
                GoG+=1;
            }
            if(imageHist[2][i]!=0){
                GoB+=1;
            }
        }
        for (int i = 0; i < 256; i++) {
            sumr = sumr + (float)(imageHist[0][i]/(float)area);
            int valr =  (int) (sumr*GoR);
            if (valr > 255) {
                rhist[i] = 255;
            } else {
                rhist[i] = valr;
            }
            sumg = sumg + (float)(imageHist[1][i]/(float)area);
            int valg = (int) (sumg*GoG);
            if (valg > 255) {
                ghist[i] = 255;
            } else {
                ghist[i] = valg;
            }
            sumb = sumb + (float)(imageHist[2][i]/(float)area);
            int valb = (int) (sumb*GoB);
            if (valb > 255) {
                bhist[i] = 255;
            } else {
                bhist[i] = valb;
            }
        }
        int red;
        int green;
        int blue;
        //in ra anh moi
        //can kich thuoc anh moi
        outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());

        for (int i = 0; i < inputImage.getWidth(); i++) {
            for (int j = 0; j < inputImage.getHeight(); j++) {

                red = new Color(inputImage.getRGB(i, j)).getRed();
                green = new Color(inputImage.getRGB(i, j)).getGreen();
                blue = new Color(inputImage.getRGB(i, j)).getBlue();

                red = rhist[red];
                green = ghist[green];
                blue = bhist[blue];

                Color m = new Color(red, green, blue);
                int rgb = m.getRGB();
                outputImage.setRGB(i, j, rgb);
            }
        }
    }

    // tinh histogram cua anh
    public int[][] imageHistogram(BufferedImage input) {   //ham dung de tinh histogram cua anh
        int[] rhist = new int[256]; //histogram cua kenh red
        int[] ghist = new int[256]; //histogram cua kenh green
        int[] bhist = new int[256]; //histogram cua kenh blue
        //khoi tao gia tri tai cac muc' mau` bang 0
        for (int i = 0; i < rhist.length; i++) {
            rhist[i] = 0;
        }
        for (int i = 0; i < ghist.length; i++) {
            ghist[i] = 0;
        }
        for (int i = 0; i < bhist.length; i++) {
            bhist[i] = 0;
        }
        //tinh gia tri diem mau` va` luu vao mang
        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {
                int red = new Color(input.getRGB(i, j)).getRed();
                int green = new Color(input.getRGB(i, j)).getGreen();
                int blue = new Color(input.getRGB(i, j)).getBlue();
                rhist[red]++;   //tang gia tri o muc mau tuong ung.
                ghist[green]++;
                bhist[blue]++;
            }
        }
        int[][] hist = new int[3][256];
        for (int i = 0; i < 256; i++) {
            hist[0][i] = rhist[i];    //0 tuong ung voi red
            hist[1][i] = ghist[i];    //green
            hist[2][i] = bhist[i];    //blue
        }

        return hist;
    }

    public static void main(String[] s) throws IOException {
        File sourceimage = new File("D:\\5-Java_File_With_Eclipse\\ImageProcessing\\src\\test6.jpg");

        try {
            BufferedImage image = ImageIO.read(sourceimage);
            HistogramDuyet hist = new HistogramDuyet(image);
            hist.histogramEqualization();
            BufferedImage output = hist.getOutputImage();
            JFrame frame = new JFrame();
            int w = output.getWidth();
            int h = output.getHeight();
            frame.setSize(w, h);
            frame.setVisible(true);
            frame.getContentPane().setBackground(Color.BLACK);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel lb = new javax.swing.JLabel();
            Image i = output.getScaledInstance(output.getWidth(), output.getHeight(), Image.SCALE_AREA_AVERAGING);
            ImageIcon imageIcon = new ImageIcon(i);
            lb.setIcon(imageIcon);
            frame.add(lb);
            frame.setVisible(true);

        } catch (IOException e) {
            System.out.println("Khong mo duoc anh");
        }
    }
}
