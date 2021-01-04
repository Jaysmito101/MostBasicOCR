import javax.swing.*;
import java.awt.*;
public class DrawBox extends JButton {
    private boolean state;
    public DrawBox(String actionCommand){
        setBackground(Color.WHITE);
        setBorderPainted(false);
        setActionCommand(actionCommand);
        setSize(32, 32);
        setBounds(0, 0, 32, 32);
    }

    public boolean getState(){
        return this.state;
    }

    public void setState(boolean state){
        this.state=state;
        if (state)  this.setBackground(Color.black);
        else this.setBackground(Color.white);
    }
}
