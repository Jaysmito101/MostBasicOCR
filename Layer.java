import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
public class Layer {
    float input[];
    float output[];
    float weights[];
    float dWeights[];
    Random random;
    public float[] getWeights() {
        return weights;
    }

    public void setWeights(float[] weights) {
        this.weights = weights;
    }

    public Layer(int inputSize, int outputSize, int mode, NeuralNetwrok netwrok){
        input=new float[inputSize+1];
        output=new float[outputSize];
        weights=new float[(inputSize+1)*outputSize];
        dWeights=new float[weights.length];
        random=new Random();
        if (mode==1)
            netwrok.initWeights(this);
    }

    public void initWeights(){
        for (int i=0;i<weights.length;i++)
            weights[i]=(random.nextFloat() - 0.5f) * 4f;
    }

    public float[] run(float inputArray[]){
        System.arraycopy(inputArray, 0 , input, 0, inputArray.length);
        input[input.length-1]=1;
        int offset=0;
        for (int i=0;i<output.length;i++){
            for (int j=0;j<input.length;j++){
                output[i] += input[j] * weights[offset+j];
            }
            output[i]=ActivationFunction.sigmoid(output[i]);
            offset += input.length;
        }
        return Arrays.copyOf(output, output.length);
    }


    public  float[] train(float error[], float learningRate, float momentum){
        int offset=0;
        float nextError[]=new float[input.length];

        for (int i=0;i<output.length;i++){
            float delta=error[i] * ActivationFunction.dSigmoid(output[i]);

            for (int j=0;j<input.length;j++){
                int weightIndex=offset + j;
                nextError[j] = nextError[j] + weights[weightIndex] * delta;
                float deltaWeight=input[j] * delta * learningRate;
                weights[weightIndex]+=dWeights[weightIndex] * momentum + deltaWeight;
                dWeights[weightIndex]=deltaWeight;
            }
            offset += input.length;
        }
        return nextError;
    }
}
