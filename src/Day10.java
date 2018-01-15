import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Karl on 2018-01-15.
 */
public class Day10 {


    public Day10() throws IOException {

        String path = "src/input.txt"; // Paste input in this file
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();

        System.out.println(line);

    }




    public static void main(String[] args) throws IOException, IOException {
        new Day10();
    }
}
