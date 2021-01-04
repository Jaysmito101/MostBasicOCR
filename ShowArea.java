import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JComponent;
public class ShowArea extends JComponent {
    private Image image;
    private Graphics2D g2;
    public ShowArea() {
        setDoubleBuffered(false);
        setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(256, 256);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clearScreen(){
        image=null;
        repaint();
    }

    public void setImage(Image image){
        clearScreen();
        this.image=image;
        repaint();
    }

    public void clear() {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, 256, 256);
        g2.setPaint(Color.black);
        repaint();
    }
}