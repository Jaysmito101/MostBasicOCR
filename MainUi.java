import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.URL;
import java.util.Random;
public class MainUi extends JFrame implements ActionListener {
    private Container draw, show;
    private JPanel topPanel, bottomPanel, yesNoPanel, infoPanel;
    private JMenuBar menuBar;
    private JMenu tools, train, about;
    private JMenuItem manualTraining, automaticTraining, deleteTraining, createDataset, aboutSoftware, developersWebiste, moreSoftwares, changeTrainingIterations, getUsageAccuracy, calculateAccuracy, resetAccuracy;
    private JLabel drawHere, drawHereTwo, output, isTheGuessCorrect, currentMode;
    private JButton recognize, resetDrawingArea, exit, yes, no, changeMode;
    private DrawArea drawArea;
    private ShowArea showArea;
    private NeuralNetwrok nNet;
    public MainUi(NeuralNetwrok neuralNetwrok){
        nNet=neuralNetwrok;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2,20, 20));
        menuBar=new JMenuBar();
        tools=new JMenu("Tools");
        train=new JMenu("Train");
        manualTraining=new JMenuItem("Train Manually");
        manualTraining.addActionListener(this);
        train.add(manualTraining);
        automaticTraining=new JMenuItem("Train Automatically Using Dataset");
        automaticTraining.addActionListener(this);
        train.add(automaticTraining);
        tools.add(train);
        deleteTraining=new JMenuItem("Delete Current Training");
        deleteTraining.addActionListener(this);
        tools.add(deleteTraining);
        createDataset=new JMenuItem("Create Dataset");
        createDataset.addActionListener(this);
        tools.add(createDataset);
        changeTrainingIterations=new JMenuItem("Change Training Iteration Number");
        changeTrainingIterations.addActionListener(this);
        tools.add(changeTrainingIterations);
        getUsageAccuracy=new JMenuItem("Get Usage Accuracy");
        getUsageAccuracy.addActionListener(this);
        resetAccuracy=new JMenuItem("Reset Accuracy");
        resetAccuracy.addActionListener(this);
        tools.add(resetAccuracy);
        tools.add(getUsageAccuracy);
        calculateAccuracy=new JMenuItem("Calculate Accuracy");
        calculateAccuracy.addActionListener(this);
        tools.add(calculateAccuracy);
        menuBar.add(tools);
        about=new JMenu("About");
        aboutSoftware=new JMenuItem("About Software");
        aboutSoftware.addActionListener(this);
        about.add(aboutSoftware);
        developersWebiste=new JMenuItem("Visit Developer's Website");
        developersWebiste.addActionListener(this);
        about.add(developersWebiste);
        moreSoftwares=new JMenuItem("More Softwares ...");
        moreSoftwares.addActionListener(this);
        about.add(moreSoftwares);
        menuBar.add(about);
        setJMenuBar(menuBar);
        draw=new Container();
        draw.setLayout(new BorderLayout());
        infoPanel=new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1, 0, 0));
        drawHere=new JLabel("Draw the number here :");
        drawHere.setFont(new Font("Arial Black", Font.BOLD, 16));
        infoPanel.add(drawHere);
        drawHereTwo=new JLabel("[Draw using the Entire Area]");
        drawHereTwo.setFont(new Font("Arial", Font.PLAIN, 14));
        infoPanel.add(drawHereTwo);
        draw.add(infoPanel, BorderLayout.NORTH);
        drawArea=new DrawArea();
        drawArea.setMode(1);
        draw.add(drawArea,BorderLayout.CENTER);
        add(draw);
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode()==16 || e.getKeyCode()==17)   drawArea.isClicked=true;
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode()==16 || e.getKeyCode()==17)   drawArea.isClicked=false;
                }

            });
        topPanel=new JPanel();
        topPanel.setLayout(new GridLayout(4,1,2,2));
        recognize=new JButton("Recognize!!");
        recognize.addActionListener(this);
        recognize.setFont(new Font("Arial Black", Font.BOLD, 16));
        topPanel.add(recognize);
        changeMode=new JButton("Change Drawing Mode");
        changeMode.addActionListener(this);
        changeMode.setFont(new Font("Arial Black", Font.BOLD, 16));
        topPanel.add(changeMode);
        currentMode=new JLabel("           [ Mode 1 ]");
        currentMode.setFont(new Font("Arial Black", Font.BOLD, 16));
        topPanel.add(currentMode);
        resetDrawingArea=new JButton("Reset Drawing Area");
        resetDrawingArea.addActionListener(this);
        resetDrawingArea.setFont(new Font("Arial Black", Font.BOLD, 16));
        topPanel.add(resetDrawingArea);
        add(topPanel);
        show=new Container();
        show.setLayout(new BorderLayout());
        output=new JLabel("The Guessed Output is :");
        output.setFont(new Font("Arial Black", Font.BOLD, 16));
        show.add(output, BorderLayout.NORTH);
        showArea=new ShowArea();
        show.add(showArea, BorderLayout.CENTER);
        add(show);
        bottomPanel=new JPanel();
        bottomPanel.setLayout(new GridLayout(3,1,2,2));
        isTheGuessCorrect=new JLabel("Is the Guessed Output Correct??");
        isTheGuessCorrect.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(isTheGuessCorrect);
        yesNoPanel=new JPanel();
        yesNoPanel.setLayout(new GridLayout(1,2,1,1));
        yes=new JButton("Yes :)");
        yes.setFont(new Font("Arial Black", Font.BOLD, 16));
        yes.setEnabled(false);
        yes.addActionListener(this);
        yesNoPanel.add(yes);
        no=new JButton("No :(");
        no.setFont(new Font("Arial Black", Font.BOLD, 16));
        no.setEnabled(false);
        no.addActionListener(this);
        yesNoPanel.add(no);
        bottomPanel.add(yesNoPanel);
        exit=new JButton("Exit");
        exit.addActionListener(this);
        exit.setFont(new Font("Arial Black", Font.BOLD, 16));
        bottomPanel.add(exit);
        add(bottomPanel);
        setSize(530, 630);
        setResizable(false);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-340, Toolkit.getDefaultToolkit().getScreenSize().height/2-300);
        setVisible(true);
        setTitle("OCR - Optical Character Recognition ---Jaysmito Mukherjee");
    }

    public void actionPerformed(ActionEvent e){
        String command=e.getActionCommand();
        if (command.equals("Recognize!!")) recognizeNumeber(nNet);
        else if (command.equals("Reset Drawing Area")) {Toolkit.getDefaultToolkit().beep();drawArea.clearScreen();showArea.clearScreen();yes.setEnabled(false);no.setEnabled(false);}
        else if (command.equals("Yes :)") || command.equals("No :("))updateResultAccuracy(command);
        else if (command.equals("About Software")){JOptionPane.showMessageDialog(this,"This is a Optical Number Recognition\nSoftware developed by\nJaysmito Mukherjee\nYou can know more about\nme from my website\njaysmitomukherjee.xp3.biz");}
        else if (command.equals("Visit Developer's Website")){
            if (JOptionPane.showConfirmDialog(this, "Open Website?")==JOptionPane.YES_OPTION)
                try{
                    Desktop.getDesktop().browse(new URL("http://jaysmitomukherjee.xp3.biz").toURI());
                }catch (Exception ex){}
        }else if (command.equals("More Softwares ...")) {
            if (JOptionPane.showConfirmDialog(this, "Open Website?")==JOptionPane.YES_OPTION)
                try{
                    Desktop.getDesktop().browse(new URL("http://jaysmitomukherjee.xp3.biz/services.html").toURI());
                }catch (Exception ex){System.out.println(ex);}
        }else if (command.equals("Delete Current Training")){
            String s="";
            Random random=new Random();
            for (int l=0;l<nNet.get0Layer().weights.length;l++){
                s=s+((random.nextFloat() - 0.5f) * 4f)+"\n";
            }
            s=s+" \n";
            for (int l=0;l<nNet.get1Layer().weights.length;l++){
                s=s+((random.nextFloat() - 0.5f) * 4f)+"\n";
            }
            BufferedWriter bufferedWriter= null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter("weights.jmdata"));
                bufferedWriter.write(s);
                bufferedWriter.close();
            } catch (IOException ex) {}
            try {
                Main.loadLayerWeights(nNet);
            } catch (Exception ex) {
            }
        }
        else if (command.equals("Create Dataset")){
            DatasetCreator datasetCreator=new DatasetCreator();
        }
        else if (command.equals("Train Automatically Using Dataset")){
            JFileChooser fileChooser=new JFileChooser("%USERPROFILE%\\Desktop");
            fileChooser.setDialogTitle("Open Dataset");
            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();
                autoTrain(fileToOpen);
            }
        }
        else if (command.equals("Train Manually")){
            JOptionPane.showMessageDialog(this, "This feature is not available\nin this version of the software.\nAs this is a beta version.\nPlease check for updates.\n\nFor manual training now\n you can first create a dataset\nand then train automatically\nusing that dataset.");
        }
        else if (command.equals("Change Drawing Mode")){
            drawArea.setMode(drawArea.getMode()==1?2:1);
            currentMode.setText(drawArea.getMode()==1?"           [ Mode 1 ]":"           [ Mode 2 ]");
        }else if (command.equals("Change Training Iteration Number")) {
            String newValue=JOptionPane.showInputDialog(this, "Warning: HIGHER VALUE -> BETTER RESULT MORE TIME\nLOWER VALUE -> FAULTY RESULT LESS TIME\nRECOMMENDED VALUE -> 10000\n\nEnter the new Value:");
            try{
                BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("constants.jmdata"));
                bufferedWriter.write(newValue);
                bufferedWriter.close();
            }catch (Exception ex){}
            Constants.loadConstants();
        }
        else if (command.equals("Calculate Accuracy")){
            JOptionPane.showMessageDialog(this, "This feature is not available\nin this version of the software.\nAs this is a beta version.\nPlease check for updates.\n\nFor checking accuracy now\n you can use the\nGet Usage Accuracy\noption.");
        }else if (command.equals("Get Usage Accuracy")){
            long correctTries, totalTries;
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("accuracy.jmdata"));
                correctTries = Long.parseLong(bufferedReader.readLine());
                totalTries = Long.parseLong(bufferedReader.readLine());
            }catch (Exception ex){
                correctTries=0;
                totalTries=0;
            }
            double accuracy=(double)correctTries/(double)totalTries*100.0;
            JOptionPane.showMessageDialog(this, "Current Accuracy : " + accuracy  + "\n\nTo increase Accuracy\nTrain with more Datasets\nFor longer time.");
        }else if (command.equals("Reset Accuracy")){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("accuracy.jmdata"));
                bufferedWriter.write("0\n0");
                bufferedWriter.close();
            }catch (Exception ex){
            }
        }
        else if (command.equals("Exit")) {Toolkit.getDefaultToolkit().beep();System.exit(0);}
    }

    private void updateResultAccuracy(String command) {
        yes.setEnabled(false);
        no.setEnabled(false);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("accuracy.jmdata"));
            long correctTries = Long.parseLong(bufferedReader.readLine());
            long totalTries = Long.parseLong(bufferedReader.readLine());
            bufferedReader.close();
            if (command.equals("Yes :)")) correctTries = correctTries + 1;
            totalTries = totalTries + 1;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("accuracy.jmdata"));
            bufferedWriter.write((correctTries+"\n"));
            bufferedWriter.write((totalTries+""));
            bufferedWriter.close();
        }catch (Exception ex){System.out.println(ex);}
    }


    private void recognizeNumeber(NeuralNetwrok neuralNetwrok) {
        Integer image[]=drawArea.getImageData();
        float imageData[]=new float[image.length];
        for (int i=0;i<image.length;i++)
            imageData[i]=Float.valueOf(image[i]);
        float result[]=neuralNetwrok.run(imageData);
        int num=Main.getNum(result);
        if (num!=-1)
            showArea.setImage(Images.getImage(num));
        else
            showArea.setImage(Images.getImage(10));
        yes.setEnabled(true);
        no.setEnabled(true);
    }

    public void autoTrain(File file){
        NeuralNetwrok neuralNetwrok=nNet;
        int t=0;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            t=Integer.parseInt(bufferedReader.readLine());
        } catch (Exception e) {}
        float trainData[][] = new float[t][64];
        float trainResult[][]=new float[t][10];
        int tmp=t;
        for(int i=0;i<tmp;i++){
            String s= null;
            try {
                s = bufferedReader.readLine();
            } catch (IOException e) {}
            for (int j=0;j<64;j++){
                trainData[i][j]=Float.valueOf(s.charAt(j)+"");
            }
            t=63;
            if (Float.valueOf(s.charAt(t+1)+"")==0.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][0]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==1.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][1]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==2.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][2]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==3.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][3]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==4.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][4]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==5.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][5]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==6.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][6]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==7.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][7]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==8.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][8]=1;
            }else if (Float.valueOf(s.charAt(t+1)+"")==9.0f){
                for(int j=0;j<10;j++)
                    trainResult[i][j]=0;
                trainResult[i][9]=1;
            }
        }
        Loader loader=new Loader();
        Loading loading=loader.getObject();
        loader.start();
        TrianingIterator trianingIterator=new TrianingIterator(neuralNetwrok, trainData, trainResult, loading);
        trianingIterator.start();
    }
}
