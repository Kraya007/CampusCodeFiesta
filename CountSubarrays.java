import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountSubarrays {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read the number of test cases
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            // Read the size of array A
            int N = Integer.parseInt(br.readLine());
            
            // Read the elements of array A
            String[] elements = br.readLine().split(" ");
            
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(elements[i]);
            }
            
            // Count the number of subarrays with odd number of set bits in XOR
            int count = 0;
            
            // Iterate over all subarrays
            for (int i = 0; i < N; i++) {
                int xor = 0;
                
                // Calculate XOR of each subarray
                for (int j = i; j < N; j++) {
                    xor ^= A[j];
                    
                    // Check if the number of set bits in XOR is odd
                    if (Integer.bitCount(xor) % 2 == 1) {
                        count++;
                    }
                }
            }
            
            // Print the result for the current test case
            System.out.println(count);
        }
        
        br.close();
    }
}
