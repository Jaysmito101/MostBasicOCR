import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class TrianingIterator implements  Runnable {
    private NeuralNetwrok neuralNetwrok;
    private float[][] trainData, trainResult;
    private Thread t;
    private Loading loading;
    public TrianingIterator(NeuralNetwrok netwrok, float[][] data, float[][] result, Loading loading){
        this.loading=loading;
        this.neuralNetwrok=netwrok;
        this.trainData=data;
        this.trainResult=result;
    }

    @Override
    public void run() {
        for (long i=0;i<Constants.ITERATIONS;i++){
            for (int j=0;j<trainResult.length;j++)
                neuralNetwrok.train(trainData[j], trainResult[j], Constants.LEARNING_RATE, Constants.MOMENTUM);
            loading.updateProgressbar(1);
        }
        loading.updateProgressbar(1);
        String s="";
        float l0[]=neuralNetwrok.get0Layer().weights;
        for (int l=0;l<l0.length;l++){
            s=s+l0[l]+"\n";
        }
        s=s+" \n";
        float l1[]=neuralNetwrok.get1Layer().weights;
        for (int l=0;l<l1.length;l++){
            s=s+l1[l]+"\n";
        }
        BufferedWriter bufferedWriter= null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("weights.jmdata"));
            bufferedWriter.write(s);
            bufferedWriter.close();
        } catch (IOException e) {}
        loading.dispose();
        JOptionPane.showMessageDialog(new JFrame(),"Training Done");
        try {
            Main.loadLayerWeights(neuralNetwrok);
        } catch (Exception e) {
        }
    }

    public void start(){
        t=new Thread(this, "trainer");
        t.start();
    }

    public Thread getThread(){
        return t;
    }
}
