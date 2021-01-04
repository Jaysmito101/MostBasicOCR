public class ActivationFunction {
    public static float sigmoid(float x){
        return (float)(1/(1+Math.exp(x*(-1))));
    }

    public static float dSigmoid(float x){
        return x*(1-x);
    }
}
