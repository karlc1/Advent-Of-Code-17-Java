import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Karl on 2017-12-18.
 */
public class Day2 {


    void part1() {
        String path = "C:/Users/Karl/AdventOfCode/src/input.txt";
        String input = readFile(path);

        String[] lines = input.split("\\r?\\n");

        int total = 0;

        for (String line : lines) {

            String[] numbers = line.split("\\s+");

            int lineMin = Integer.MAX_VALUE;
            int lineMax = Integer.MIN_VALUE;

            for (String s : numbers) {

                int currentVal = Integer.parseInt(s);

                if (currentVal < lineMin) {
                    lineMin = currentVal;
                }

                if (currentVal > lineMax) {
                    lineMax = currentVal;
                }
            }

            total += lineMax - lineMin;
        }

        System.out.println(total);
    }


    public void part2() {

        String path = "C:/Users/Karl/AdventOfCode/src/input.txt";
        String input = readFile(path);

        String[] lines = input.split("\\r?\\n");

        int total = 0;

        for (String line : lines) {

            String[] numbers = line.split("\\s+");

            boolean found = false;

            for (int i = 0; i < numbers.length; i++) {

                int currentVal = Integer.parseInt(numbers[i]);

                for (int j = 0; j < numbers.length; j++) {
                    int testVal = Integer.parseInt(numbers[j]);

                    if (currentVal != testVal && (currentVal % testVal == 0)) {
                        total += currentVal / testVal;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    break;
                }
            }
        }

        System.out.println(total);
    }


    public Day2() {
        part2();
    }

    public static void main(String[] args) throws IOException, IOException {
        new Day2();
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
