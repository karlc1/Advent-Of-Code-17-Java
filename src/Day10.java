import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Karl on 2018-01-15.
 */
public class Day10 {

    String path = "src/input.txt"; // Paste input in this file
    File file = new File(path);


    public Day10() throws IOException {
        part2();
    }

    private void part1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        ArrayList<Integer> inputList = new ArrayList<>();

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

            // Copy the current segment into a new list
            for (int i = 0; i < input; i++) {
                int index = (currentPos + i) % (rope.length); // wrapping array
                segment[segmentIndex++] = rope[index];
            }

            // Put the copied list back in the original rope but backwards
            for (int i = 0; i < input; i++) {
                int index = (currentPos + i) % (rope.length);
                rope[index] = segment[segment.length - 1 - i];
            }

            currentPos = (currentPos + input + skipSize) % (rope.length);
            skipSize++;
        }

        System.out.println("Part 1 output: " + (rope[0] * rope[1]));
    }

    private void part2() throws IOException {

        //////////// POST-PROCESS INPUT //////////

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        List<Character> charList = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        ArrayList<Integer> inputList = new ArrayList<>();

        for (char c : charList) {
            inputList.add((int)c);
        }
        int[] suffixValues = {17, 31, 73, 47, 23};
        for (int i : suffixValues) {
            inputList.add(i);
        }

        ////////// SETTING UP INITIAL STATE /////////////

        int ropeSize = 256;

        int[] rope = new int[ropeSize];
        for (int i = 0; i < ropeSize; i++) {
            rope[i] = i;
        }

        // starting point
        int currentPos = 0;
        int skipSize = 0;


        ///////// PERFORM KNOTTING ROUNDS ////////

        for (int round = 0; round < 64; round++) {
            for (int input : inputList) {

                int[] segment = new int[input];
                int segmentIndex = 0;

                // Copy the current segment into a new list
                for (int i = 0; i < input; i++) {
                    int index = (currentPos + i) % (rope.length); // wrapping array
                    segment[segmentIndex++] = rope[index];
                }

                // Put the copied list back in the original rope but backwards
                for (int i = 0; i < input; i++) {
                    int index = (currentPos + i) % (rope.length);
                    rope[index] = segment[segment.length - 1 - i];
                }

                currentPos = (currentPos + input + skipSize) % (rope.length);
                skipSize++;
            }
        }

        ////////// XOR THE VALUES ///////////






    }




    public static void main(String[] args) throws IOException, IOException {
        new Day10();
    }
}
