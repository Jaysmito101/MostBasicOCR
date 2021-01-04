import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
public class Constants {
    private Constants(){}
    
    public static float LEARNING_RATE=0.1f;
    public static float MOMENTUM=0.6f;
    public static long ITERATIONS=10000;
    public static void loadConstants(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Jaysmito Mukherjee\\Desktop\\Constants.txt"));
            ITERATIONS = Long.parseLong(bufferedReader.readLine());
        }catch (Exception ex){
            ITERATIONS=10000;
        }
    }
}
