/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerfilter;
//Submitted to Cal Hacks 3.0
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
        System.out.print("This is a Test");
                        
    BufferedImage originalimage = ImageIO.read(imageFile);
    if (originalimage == null) {
        System.out.println("The Value DNE");
    }
    BufferedImage originaloverlay = ImageIO.read(overlayFile);
    if (originaloverlay == null) {
        System.out.println("The Value DNE");
    }
    int type1 = originalimage.getType();
    int type2 = originaloverlay.getType();
    BufferedImage image = resizeImage(originalimage, type1);
    BufferedImage overlay = resizeImage(originaloverlay, type2);
  //  System.out.println(image.getHeight());
    // create the new image, canvas size is the max. of both image sizes
   // int w = Math.min(image.getWidth(), overlay.getWidth());
   // int h = Math.min(image.getHeight(), overlay.getHeight());
   int w = 500;
   int h = 500;
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
    Graphics g = combined.getGraphics();
    g.drawImage(image, 0, 0, null);
    float opacity = 0.5f;
    Graphics2D g2 = (Graphics2D) g; 
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    g.drawImage(overlay, 0, 0, null);

// Save as new image
    ImageIO.write(combined, "PNG", new File("C:\\Users\\admin\\Desktop", "combined.png"));
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
    
    
}    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();

	return resizedImage;
    }
    
    
    
}
