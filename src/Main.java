import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BruteForcer bruteForcer = new BruteForcer("C:\\Users\\patry\\Desktop\\Uni stuff\\4th semester\\NAI\\KnapsackBruteForce\\12");
            bruteForcer.Load();
            bruteForcer.BruteForce();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
