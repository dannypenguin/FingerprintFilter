/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerfilter;

//import statemtents

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author admin
 */



public class FingerFilter {
    private static final int IMG_WIDTH = 500;
    private static final int IMG_HEIGHT = 500;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        //first, load the images
        //loading the fingerprint
        //find most recent fingerprint
        // load source images
        File imageFile = lastFileModified("C:\\Users\\admin\\Desktop\\Camera Roll");
        File overlayFile = lastFileModified("C:\\Images");
        //  File imageFile = new File("C:\\Users\\admin\\Desktop\\Camera Roll\\imageimage.jpg");
        //  File overlayFile = new File("C:\\Images\\black1_on_silver.bmp");
        
        BufferedImage image = ImageIO.read(imageFile);
        if (image == null) {
            System.out.println("The Value DNE");
        }
        BufferedImage overlay = ImageIO.read(overlayFile);
        if (overlay == null) {
            System.out.println("The Value DNE");
        }
        
        
        
        int w = 140;
        int h = 140;
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        
        // paint both images, preserving the alpha channels
        Graphics2D g2 = combined.createGraphics();
        
        g2.drawImage(overlay, 0, 0, null);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 0.5F);
        g2.setComposite(ac);
        g2.drawImage(overlay, 0, 0, null);
        ImageIO.write(combined, "PNG", new File("C:\\Users\\admin\\Desktop", "combined.png"));
        
        g2.dispose();
        
    }
    
    //to find the most recent file
    public static File lastFileModified(String dir) {
        File fl = new File(dir);
        File[] files = fl.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file1 : files) {
            if (file1.lastModified() > lastMod) {
                choice = file1;
                lastMod = file1.lastModified();
            }
        }
        return choice;
        
        
    }   
}
