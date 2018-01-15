import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Karl on 2018-01-15.
 */
public class Day10 {


    public Day10() throws IOException {

        String path = "src/input.txt"; // Paste input in this file
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        ArrayList<Integer> inputList = new ArrayList<>();

        System.out.println(line);
        for (String str : line.split(",")) {
            inputList.add(Integer.parseInt(str));
        }

        int ropeSize = 256;

        int[] rope = new int[ropeSize];
        for (int i = 0; i < ropeSize; i++) {
            rope[i] = i;
        }


        // starting point
        int currentPos = 0;
        int skipSize = 0;

        for (int input : inputList) {

            int[] segment = new int[input];
            int segmentIndex = 0;

            // Put the current segment into a new list
            for (int i = 0; i < input; i++) {
                int index = (currentPos + i) % (rope.length); // wrapping array
                segment[segmentIndex] = rope[index];
                segmentIndex++;
            }

            for (int i = 0; i < input; i++) {
                int index = (currentPos + i) % (rope.length);
                rope[index] = segment[segment.length - 1 - i];
            }



            currentPos = (currentPos + input + skipSize) % (rope.length);
            skipSize++;
        }

        System.out.println("Part 1 output: " + (rope[0] * rope[1]));


    }




    public static void main(String[] args) throws IOException, IOException {
        new Day10();
    }
}
