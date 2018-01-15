import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Karl on 2018-01-15.
 *
 * Part 1 and 2 are ran simultaneously as part 2 only involves checking if each new change becomes larger
 * than the previous largest value, no need for separate methods
 */
public class Day8 {

    String path = "src/input.txt"; // Paste input in this file
    File file = new File(path);

    HashMap<String, Integer> registers = new HashMap<>();
    int highestValueEver = Integer.MIN_VALUE;

    public Day8() {


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                // Split each part of the line by whitespace
                String[] parts = line.split("\\s+");

                // Store each part of the line separately
                String changeReg    = parts[0];
                String changeOp     = parts[1];
                int changeVal       = Integer.parseInt(parts[2]);
                String compReg      = parts[4];
                String compOp       = parts[5];
                int compVal         = Integer.parseInt(parts[6]);

                if (checkCondition(compReg, compOp, compVal)) {
                    changeRegisterValue(changeReg, changeOp, changeVal);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Largest Value at End: " + getLargestValue());
        System.out.println("Largest Value Ever: " + highestValueEver);


    }


    private boolean checkCondition(String compReg, String compOp, int compVal){
        int regVal = 0;

        // If the register with the comparison key does not exist, create it
        // Otherwise, use the registered value for comparison
        if (!registers.containsKey(compReg)){
            registers.put(compReg, 0);
        }else {
            regVal = registers.get(compReg);
        }

        switch (compOp) {
            case (">"):
                return (regVal > compVal);
            case ("<"):
                return (regVal < compVal);
            case (">="):
                return (regVal >= compVal);
            case ("<="):
                return (regVal <= compVal);
            case ("=="):
                return (regVal == compVal);
            case ("!="):
                return (regVal != compVal);
            default:
                System.err.println("Some operator missed!");
                System.err.println("Missing: " + compOp);
                System.exit(0);
        }
        return false;   // Should never reach
    }


    private void changeRegisterValue(String changeReg, String operation, int changeVal) {

        int regVal = 0;

        // If the register with the comparison key does not exist, create it
        if (!registers.containsKey(changeReg)){
            registers.put(changeReg, 0);
        }else {
            regVal = registers.get(changeReg);
        }

        // Init new value like this to compare it to largest previous value
        int newVal = 0;

        // Increase or decrease, or break program is neither (should never happen)
        if (operation.equals("inc")) {
            newVal = regVal + changeVal;
        }else if (operation.equals("dec")) {
            newVal = regVal - changeVal;
        }else {
            System.err.println("Operator string broken");
            System.exit(0);
        }

        // Add new value to register
        registers.put(changeReg, newVal);


        // If the new value is larger than the largest value ever, set it as such
        // This is the only part needed for part 2 of the challenge
        if (newVal > highestValueEver) {
            highestValueEver = newVal;
        }
    }


    /**
     * Get largest value in the registers
     * Call after entire input has been parsed and evaluated
     * @return
     */
    private int getLargestValue () {
        int largest = Integer.MIN_VALUE;

        for (Integer value : registers.values()) {
            if (value > largest) {
                largest = value;
            }
        }
        return largest;
    }



    public static void main(String[] args) throws IOException, IOException {
        new Day8();
    }


}
