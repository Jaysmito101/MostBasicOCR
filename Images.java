import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Images {
   public static BufferedImage getImage(int num){
       BufferedImage bufferedImage=new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
       try {
           bufferedImage= ImageIO.read(new File(num + ".png"));
       } catch (Exception e) {
       }
       return bufferedImage;
   }
}
