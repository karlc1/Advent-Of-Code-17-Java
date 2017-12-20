import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Karl on 2017-12-18.
 */
public class Day5 {

   final static String path = "C:/Users/Karl/AdventOfCode/src/input.txt";


    void part1(){


        String input = readFile(path);
        String[] lines = input.split("\\r?\\n");

        ArrayList<Integer> values = new ArrayList<>();


        for (String line : lines){
            int val = Integer.parseInt(line);
            values.add(val);
        }

        int cursor = 0;
        int counter = 0;

        while (cursor <= values.size()){
            int temp = cursor;
            cursor = cursor + values.get(cursor);
            values.set(temp, values.get(temp) + 1);
            counter++;
            System.out.println(counter);

        }


    }

    void partOld2(){


        String input = readFile(path);
        String[] lines = input.split("\\r?\\n");

        ArrayList<Integer> values = new ArrayList<>();


        for (String line : lines){
            int val = Integer.parseInt(line);
            values.add(val);
        }

        int cursor = 0;
        int counter = 0;

        while (cursor <= values.size()){
            int temp = cursor;
            cursor = cursor + values.get(cursor);

            if (values.get(temp) >= 3){
                values.set(temp, values.get(temp) - 1);
            }else {
                values.set(temp, values.get(temp) + 1);
            }
            counter++;
            System.out.println(counter);

        }


    }

    void part2(){


        String input = readFile(path);
        String[] lines = input.split("\\r?\\n");

        //ArrayList<Integer> values = new ArrayList<>();

        int [] values = new int[lines.length];


        for (int i = 0; i < lines.length; i++){
            int val = Integer.parseInt(lines[i]);
            values[i] = (val);
        }

        int cursor = 0;
        int counter = 0;

        while (cursor <= values.length){
            int temp = cursor;
            cursor = cursor + values[cursor];

            if (values[temp] >= 3){
                values[temp] = values[temp] - 1;
            }else {
                values[temp] = values[temp] + 1;
            }
            counter++;
        }


    }





    public Day5() {
        part2();
    }

    public static void main(String[] args) throws IOException, IOException {
        new Day5();
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
