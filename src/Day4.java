import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * Created by Karl on 2017-12-18.
 */
public class Day4 {
    String path = "C:/Users/Karl/AdventOfCode/src/input.txt";

    void part1(){

        String input = readFile(path);
        String[] lines = input.split("\\r?\\n");

        int counter = 0;

        for (String line : lines) {

            String[] words = line.split("\\s+");

            if (!hasDuplicates(words)){
                counter++;
            }
        }
        System.out.println("Count: " + counter);
    }

    boolean hasDuplicates(String[] words){

        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        Collections.addAll(lhs, words);

        if(lhs.size() == words.length){
            return false;
        }

        return true;
    }


    void part2(){

        String input = readFile(path);
        String[] lines = input.split("\\r?\\n");

        int counter = 0;

        for (String line : lines) {

            String[] words = line.split("\\s+");

            if (!hasAnagrams(words)){
                counter++;
            }
        }
        System.out.println("Count: " + counter);
    }

    boolean hasAnagrams(String[] words){

        ArrayList<String> sortedWords = new ArrayList<>();

        for (String word : words){

           char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);
            sortedWords.add(sortedString);
        }

        String []sortArray = new String[sortedWords.size()];
        sortedWords.toArray(sortArray);



        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        Collections.addAll(lhs, sortArray);

        if(lhs.size() == words.length){
            return false;
        }

        return true;
    }







    public Day4() {
        part2();
    }

    public static void main(String[] args) throws IOException, IOException {
        new Day4();
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
