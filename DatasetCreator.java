import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
public class DatasetCreator extends JFrame{
    private DrawArea drawArea;
    private JLabel drawHere, drawHereTwo, choose;
    private JButton next, done, resetDrawingArea;
    private JComboBox list;
    private String numbers[];
    private ArrayList<Integer[]> data;
    private ArrayList<Integer> dataTarget;
    private Integer currentNumber;
    private JFileChooser fileChooser;
    private JPanel topLeft, topRight, midRight;
    private Container top;
    public DatasetCreator(){
        data=new ArrayList<Integer[]>();
        dataTarget=new ArrayList<Integer>();
        currentNumber=0;
        setLayout(new BorderLayout());
        top=new Container();
        top.setLayout(new GridLayout(1,2,0,0));
        topLeft=new JPanel();
        topLeft.setLayout(new GridLayout(2,1,0,0));
        drawHere=new JLabel("Draw the number here :");
        drawHere.setFont(new Font("Arial Black", Font.BOLD, 12));
        topLeft.add(drawHere);
        drawHereTwo=new JLabel("[Draw using the Entire Area]");
        drawHereTwo.setFont(new Font("Arial", Font.PLAIN, 12));
        topLeft.add(drawHereTwo);
        top.add(topLeft);
        topRight=new JPanel();
        topRight.setLayout(new GridLayout(2,1,0,0));
        choose=new JLabel("Create Dataset for : ");
        choose.setFont(new Font("Arial Black", Font.BOLD, 12));
        topRight.add(choose);
        numbers=new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        list=new JComboBox(numbers);
        list.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentNumber=Integer.valueOf(list.getItemAt(list.getSelectedIndex()).toString());
            }
        });
        list.setSize(100,30);
        topRight.add(list);
        top.add(topRight);
        add(top, BorderLayout.NORTH);
        drawArea=new DrawArea();
        drawArea.setMode(1);
        add(drawArea, BorderLayout.CENTER);

        midRight=new JPanel();
        midRight.setLayout(new GridLayout(3,1,0,0));
        resetDrawingArea=new JButton("Reset Drawing Area");
        resetDrawingArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.clearScreen();
            }
        });
        midRight.add(resetDrawingArea);
        next=new JButton("Next ->");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                done.setEnabled(true);
                data.add(drawArea.getImageData());
                dataTarget.add(currentNumber);
                drawArea.clearScreen();
            }
        });
        midRight.add(next);
        done=new JButton("Done !");
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s="";
                s = s + data.size() + "\n";
                for(int i=0;i<data.size();i++){
                    for(int j=0;j<data.get(i).length;j++){
                        s=s+data.get(i)[j];
                    }
                    s=s+Integer.valueOf(dataTarget.get(i));
                    s=s+"\n";
                }
                fileChooser=new JFileChooser("%USERPROFILE%\\Desktop");
                fileChooser.setDialogTitle("Specify a Location");
                int userSelection = fileChooser.showSaveDialog(new JFrame());
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String path=fileToSave.getPath();
                    if (path.indexOf(".")==-1) path=path+".jmdata";
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
                        bufferedWriter.write(s);
                        bufferedWriter.close();
                    } catch (Exception ex) {
                    }
                }
                dispose();
            }
        });
        done.setEnabled(false);
        midRight.add(done);
        add(midRight, BorderLayout.EAST);
        setTitle("Create Dataset");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(380, 300);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-250, Toolkit.getDefaultToolkit().getScreenSize().height/2-220);
    }
}