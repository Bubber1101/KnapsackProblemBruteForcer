import javafx.util.Pair;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BruteForcer {

    // private final String DATA_FILE_PATH;
    private final Scanner SCANNER;
    private final int CAPACITY;
    private final int N;
    public int[] values;
    public int[] weights;

    public BruteForcer(String path) throws IOException {
        //     DATA_FILE_PATH = path;
        SCANNER = new Scanner(new File(path));
        CAPACITY = Integer.parseInt(SCANNER.nextLine());
        Reader reader = new LineNumberReader(new FileReader(new File(path)));
        while ((((LineNumberReader) reader).readLine()) != null);
        N = ((LineNumberReader) reader).getLineNumber()-1;
        values = new int[N];
        weights = new int[N];
        Load();

    }

    public void BruteForce() {
        long start = System.nanoTime();
        Integer bestValue = 0;
        boolean[] bestVector = new boolean[N];
        int x = (int) Math.pow(2, N);

        for (int i = 0; i < x; i++) {
            boolean[] tempVector = generateCharacteristicVector(i);
            Integer tempValue = getSumByCharacteristicVector(tempVector, true);
            if (tempValue != null) {
                if (tempValue > bestValue) {
                    bestValue = tempValue;
                    bestVector = tempVector;
                }
            }
          //  if (i % 600000 == 0) {
           //     System.out.println((double) i * 100 / x + "%   " );

          //  }
        }


        long end = System.nanoTime();
        long period = end - start;
        System.out.println("It took " + TimeUnit.SECONDS.convert(period, TimeUnit.NANOSECONDS) + "Seconds");
        System.out.println(CharacteristicVectorToString(bestVector));
        System.out.println("Value: " + bestValue);
        System.out.println("Weight: " + getSumByCharacteristicVector(bestVector, false));
    }

    public String CharacteristicVectorToString(boolean[] vector){
        String result = "";
        for (int i = 0; i <vector.length ; i++) {
            if (vector[i])result +="1";
            else result+="0";
        }
        return result;
    }

    public void Load() {
        int i = 0;
        while (SCANNER.hasNextLine()) {
            String temp[] = SCANNER.nextLine().split(" ");
            values[i] = Integer.parseInt(temp[0]);
            weights[i] = Integer.parseInt(temp[1]);
            i++;
        }


    }

    public boolean[] generateCharacteristicVector(int i) {
        boolean[] result = new boolean[N];
        // System.out.print("// "+ i + " ");
        for (int z = 0;z<N; z++ ) {
            result[z] = i % 2 == 1;
            i = i / 2; //integer division
        }

        // System.out.println(result + " ||");
        return result;
    }

    public Integer getSumByCharacteristicVector(boolean[] vector, boolean returnValue) {
        int value = 0;
        int weight = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (vector[i]) {
                value += values[i];
                weight += weights[i];
                if (weight > CAPACITY) return null;
            }

        }
        if (returnValue) return value;
        return weight;
    }
//    public Pair<Integer, Integer> getSumByCharacteristicVector(String vector) {
//        int value = 0;
//        int weight = 0;
//        String arr[] = vector.split("");
//        for (int i = 0; i < N; i++) {
//            if (arr[i].equals("1")) {
//                Pair<Integer, Integer> temp = items.get(i);
//                value += temp.getKey();
//                weight += temp.getValue();
//                if(weight>CAPACITY) return null;
//            }
//
//        }
//        return new Pair<>(value, weight);
//    }


}
