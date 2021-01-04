public class NeuralNetwrok {
    private Layer layers[];
    public NeuralNetwrok(int inputSize, int hiddenSize, int outputSize){
        layers=new Layer[2];
        layers[0]=new Layer(inputSize, hiddenSize, 2, this);
        layers[1]=new Layer(hiddenSize, outputSize, 2, this);
    }

    public  void initWeights(Layer layer){
        for (int i=0;i<layer.weights.length;i++)
            layer.weights[i]=(layer.random.nextFloat() - 0.5f) * 4f;
    }

    public void set0LayerWeight(float[] weights){
        layers[0].setWeights(weights);
    }

    public void set1LayerWeight(float[] weights){
        layers[1].setWeights(weights);
    }

    public Layer get0Layer(){
        return layers[0];
    }

    public Layer get1Layer(){
        return layers[1];
    }

    public float[] run(float[] input){
        float activations[]=input;

        for (int i=0;i<layers.length;i++){
            activations=layers[i].run(activations);
        }
        return  activations;
    }

    public void train(float input[], float targetOutput[], float learningRate, float momentum){
        float calculatedOutput[]=run(input);
        float error[]=new float[calculatedOutput.length];
        for (int i=0;i<error.length;i++)
            error[i]=targetOutput[i]-calculatedOutput[i];
        for (int i=layers.length-1;i>=0;i--)
            error=layers[i].train(error, learningRate, momentum);
    }
}