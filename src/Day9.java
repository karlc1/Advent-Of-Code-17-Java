import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Karl on 2018-01-15.
 */
public class Day9 {

    public Day9() {
        try {
            part1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part1() throws IOException {

        String path = "src/input.txt"; // Paste input in this file
        File file = new File(path);


        BufferedReader reader = null;
        reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        Charset.forName("UTF-8")));

        int c;

        char lastCharacter = ' ';
        boolean withinGarbage = false;
        int groupLevel = 0;
        long totalScore = 0;

        while ((c = reader.read()) != -1) {
            char character = (char) c;

            // Proceed if last character was not escape char
            if (lastCharacter != '!') {

                // If this char is escape char, set it as lastCharacter and continue.
                if (character == '!') {
                    lastCharacter = character;
                    continue;
                }


                // Proceed if not within garbage
                if (!withinGarbage) {

                    if (character == '{') {
                        groupLevel++;
                    }

                    else if (character == '}') {
                        totalScore += groupLevel--;
                    }

                    else if (character == '<') {
                        withinGarbage = true;
                    }


                }else { // If within garbage

                    // The only interesting char is if garbage is ended
                    if (character == '>') {
                        withinGarbage = false;
                        continue;
                    }
                }



            // If the last character was an escape char, the only thing happening is that the lastCharacter is reset
            } else {
                lastCharacter = ' ';
            }
        }


        System.out.println("Total Score: " + totalScore);


    }


    public static void main(String[] args) throws IOException, IOException {
        new Day9();
    }
}



