package patientDataUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.image.*;
import javax.imageio.ImageIO;
import patientDataUtils.FFT;
import patientDataUtils.FFT.Complex;

public class image {

    public static int[] img_resolution = new int[] {512, 512};
    public static ArrayList<Complex> img = null;
    public static BufferedImage buff_img = null;
    public static FFT fast_fourier = new FFT();
    public static ArrayList<Complex> fft_image;
    
    public image(String image_path) {
        img = read(image_path);
    }

    public static ArrayList<Complex> read(String path) {

        // read image from its path

        try {
            File img_path = new File(path);
            buff_img = new BufferedImage(img_resolution[0], img_resolution[1], BufferedImage.TYPE_INT_RGB);
            buff_img = ImageIO.read(img_path);

            // All data in getDataBuffer() seems to be negative
            byte[] pixels = ((DataBufferByte)buff_img.getRaster().getDataBuffer()).getData();
            Complex[] cinput = new Complex[pixels.length];
            for (int i = 0; i < pixels.length; i++) {
                cinput[i] = new Complex(pixels[i], 0.0);
            }

            ArrayList<Complex> image = new ArrayList<Complex>(Arrays.asList(cinput));
            
            fft_image = fast_fourier.fft(image, false);

        } catch(IOException e) {
            System.out.println("Error: " + e + " " + path);
        }

        return fft_image;
    }
}
