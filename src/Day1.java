import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Karl on 2017-12-18.
 */
public class Day1 {
    String path = "C:/Users/Karl/AdventOfCode/src/input.txt";


    public Day1(){
        part2();
    }

    void part1(){
        String input = readFile(path);

        int sum = 0;

        for (int i = 0; i < input.length(); i++){
            int curr;
            int next;

            if (i < input.length() - 1){
                curr = toInt(input.charAt(i));
                next = toInt(input.charAt(i+1));
            }

            else{
                curr = toInt(input.charAt(input.length() - 1));
                next = toInt(input.charAt(0));
            }

            if (curr == next){
                sum += curr;
            }
        }

        System.out.println(sum);
    }

    void part2(){
        String input = readFile(path);

        int sum = 0;
        int steps = input.length() / 2;

        for (int i = 0; i < input.length(); i++){
            int curr;
            int next;

            if (i + steps < input.length()){
                curr = toInt(input.charAt(i));
                next = toInt(input.charAt(i+steps));
            }

            else{
                curr = toInt(input.charAt(i));
                next = toInt(input.charAt(i - steps));
            }

            if (curr == next){
                sum += curr;
            }
        }

        System.out.println(sum);
    }





    static int toInt(char c){
        return Character.getNumericValue(c);
    }





    public static void main(String[]args) throws IOException, IOException {
        new Day1();
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
