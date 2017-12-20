import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Karl on 2017-12-18.
 */
public class Day6 {

    final static String path = "C:/Users/Karl/AdventOfCode/src/input.txt";




    void part1(){
        String input = readFile(path);
        String[] blocks = input.split("\\s+");

        int[] banks = new int[blocks.length];

        for (int i = 0; i < blocks.length; i++){
            banks[i] = Integer.parseInt(blocks[i]);
        }

        ArrayList<int[]> previousStates = new ArrayList<>();


        int cycles = 0;

        while (true){

            cycles++;

            int bankIndex = 0;
            int largestAmount = Integer.MIN_VALUE;

            for (int i = 0; i < banks.length; i++){
                if (banks[i] > largestAmount){
                    largestAmount = banks[i];
                    bankIndex = i;
                }
            }

            int changeIndex = bankIndex;
            banks[bankIndex] = 0;
            for (int i = 0; i < largestAmount; i++){

                if (changeIndex + 1 >= banks.length){
                    changeIndex = -1;
                }

                changeIndex++;
                banks[changeIndex]++;
            }

            boolean prevFound = false;

            for (int i = 0; i < previousStates.size(); i++){

                for (int j = 0; j < previousStates.get(i).length; j++){

                    int currBlock = previousStates.get(i)[j];

                    if (currBlock != banks[j]){
                        break;
                    }

                    else if (currBlock == banks[j] && j == banks.length-1){
                        prevFound = true;
                        break;
                    }
                }

                if (prevFound){
                    break;
                }

            }

            if(prevFound){
                break;
            }

            previousStates.add(banks.clone());
        }

        System.out.println(cycles);
    }

    void part2(){
        String input = readFile(path);
        String[] blocks = input.split("\\s+");

        int[] banks = new int[blocks.length];

        for (int i = 0; i < blocks.length; i++){
            banks[i] = Integer.parseInt(blocks[i]);
        }

        ArrayList<int[]> previousStates = new ArrayList<>();


        int cycles = 0;

        boolean prevFound = false;



        while (true){

            cycles++;

            int bankIndex = 0;
            int largestAmount = Integer.MIN_VALUE;

            for (int i = 0; i < banks.length; i++){
                if (banks[i] > largestAmount){
                    largestAmount = banks[i];
                    bankIndex = i;
                }
            }

            int changeIndex = bankIndex;
            banks[bankIndex] = 0;
            for (int i = 0; i < largestAmount; i++){

                if (changeIndex + 1 >= banks.length){
                    changeIndex = -1;
                }

                changeIndex++;
                banks[changeIndex]++;
            }



            for (int i = 0; i < previousStates.size(); i++){

                for (int j = 0; j < previousStates.get(i).length; j++){

                    int currBlock = previousStates.get(i)[j];

                    if (currBlock != banks[j]){
                        break;
                    }

                    else if (currBlock == banks[j] && j == banks.length-1){
                        prevFound = true;

                        int loops = previousStates.size() - i;

                        System.out.println("Loops: " + loops);

                        break;
                    }
                }

                if (prevFound){
                    break;
                }

            }

            if(prevFound){
                break;
            }

            previousStates.add(banks.clone());
        }

        System.out.println(cycles);
    }


    public Day6() {
        part2();
    }

    public static void main(String[] args) throws IOException, IOException {
        new Day6();
    }

    static String readFile(String path) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(encoded, StandardCharsets.UTF_8);
    }


}
