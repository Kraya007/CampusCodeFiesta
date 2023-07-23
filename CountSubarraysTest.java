import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class CountSubarraysTest {
    public static void main(String[] args) {
        // Test case 1
        String input1 = "1\n5\n1 2 3 4 5\n";
        String expectedOutput1 = "9\n";
        testSolution(input1, expectedOutput1);

        // Test case 2
        String input2 = "2\n3\n1 3 5\n4\n1 2 3 4\n";
        String expectedOutput2 = "6\n10\n";
        testSolution(input2, expectedOutput2);

        // Add more test cases as needed
    }

    private static void testSolution(String input, String expectedOutput) {
        // Redirect System.in and System.out to enable testing
        InputStream originalInput = System.in;
        PrintStream originalOutput = System.out;

        try {
            // Set the input
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Capture the output
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            // Run the main method of the solution class
            CountSubarrays.main(null);

            // Get the actual output
            String actualOutput = output.toString();

            // Compare the actual and expected output
            if (actualOutput.equals(expectedOutput)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
                System.out.println("Expected Output:\n" + expectedOutput);
                System.out.println("Actual Output:\n" + actualOutput);
            }
        } finally {
            // Restore System.in and System.out
            System.setIn(originalInput);
            System.setOut(originalOutput);
        }
    }
}
