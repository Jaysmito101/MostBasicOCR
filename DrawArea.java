import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import javax.swing.*;
public class DrawArea extends JPanel{
    DrawBox drawBox[][];
    public boolean isClicked;
    private int mode;
    public DrawArea(){
        setLayout(new GridLayout(8,8, 0, 0));
        drawBox=new DrawBox[8][8];
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                drawBox[i][j]=new DrawBox(i+""+j);
                drawBox[i][j].addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        if(mode==1)
                        if (isClicked)
                        ((DrawBox)e.getSource()).setState(true);
                    }
                });
                drawBox[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (mode==1)
                        ((DrawBox)e.getSource()).setState(((DrawBox)e.getSource()).getState()?false:true);
                        else if (mode==2)   ((DrawBox)e.getSource()).setState(true);
                    }
                });
                this.add(drawBox[i][j]);
            }
        }
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void clearScreen() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                drawBox[i][j].setState(false);
            }
        }
    }

    public Integer[] getImageData(){
        Integer data[]=new Integer[64];
        int offset=0;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                data[offset+j]=(drawBox[i][j].getState()?1:0);
            }
            offset+=8;
        }
        return data;
    }
}