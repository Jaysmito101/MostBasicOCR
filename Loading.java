import javax.swing.*;
import java.awt.*;
public class Loading extends JFrame {
    private JLabel label;
    private JProgressBar progressBar;
    public Loading(){
        Container base=getContentPane();
        setLayout(new GridLayout(2, 1, 0, 0));
        label=new JLabel("Please Wait ...");
        base.add(label);
        progressBar=new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum((int) Constants.ITERATIONS);
        base.add(progressBar);
        setSize(300, 100);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-50);
        setVisible(true);
        setResizable(false);
    }

    public void setText(String text){
        label.setText(text);
    }

    public void updateProgressbar(int incrementValue){
        int currentValue=progressBar.getValue();
        progressBar.setValue(currentValue + incrementValue);
    }
}