import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        try{
            Constants.loadConstants();
            NeuralNetwrok neuralNetwrok=new NeuralNetwrok(64, 32, 10);
            loadLayerWeights(neuralNetwrok);
            MainUi ob=new MainUi(neuralNetwrok);
        }catch(Exception ex){}
    }

    public static void loadLayerWeights(NeuralNetwrok neuralNetwrok) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("weights.jmdata"));
            ArrayList<Float> l0 = new ArrayList<Float>();
            String s;
            s = bufferedReader.readLine();
            while (!s.equals(" ")) {
                l0.add(Float.valueOf(s));
                s = bufferedReader.readLine();
            }
            float l0W[] = new float[l0.size()];
            for (int i = 0; i < l0.size(); i++)
                l0W[i] = l0.get(i);
            neuralNetwrok.set0LayerWeight(l0W);
            s = bufferedReader.readLine();
            ArrayList<Float> l1 = new ArrayList<Float>();
            while (s != null) {
                l1.add(Float.valueOf(s));
                s = bufferedReader.readLine();
            }
            float l1W[] = new float[l1.size()];
            for (int i = 0; i < l1.size(); i++)
                l1W[i] = l1.get(i);
            neuralNetwrok.set1LayerWeight(l1W);
        }catch (Exception ex){}
    }

    public static int getNum(float arr[]){
        int t=0;
        float f=0.0f;
        for (int i=0;i<arr.length;i++){
            if (Math.round(arr[i])>f){
                t=i;
                f=arr[i];
            }
        }
        if (f==0) t=-1;
        return t;
    }

    public static void printArray(float[] arr){
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}