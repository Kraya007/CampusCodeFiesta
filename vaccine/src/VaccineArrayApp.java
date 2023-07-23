
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class VaccineArrayApp 

{
	 public static float startTime = 0;
	/*
	 * initial time defined runTime
	 * with gabbage collector
	 */
    public static void start() {
        System.gc();
        startTime = System.nanoTime();
    }
    
    /*
	 * final time defined end RunTime
	 */
    public static float stop() {
        return (System.nanoTime() - startTime) / 1_000_000;
    }
    /**
     * writes the output to a file
     * @param outputFileName
     * @param array
     */
    public static void writeToFile(String outputFileName, String[] array, float time) {
        try {
            FileWriter fileWriter = new FileWriter(outputFileName);
            fileWriter.write("Time taken: " + time + " milliseconds\n \n");
            for (int index = 0; index < array.length; index++) {
                fileWriter.write(array[index] + "\n");
            }
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    

	public static void main(String[] args)
	{
		
		String path = "C:\\Users\\kndub\\eclipse-workspace\\BinarySearch2022\\src\\vaccinations.csv";
		String line = "";
		String[] array;
		try {
			
			BufferedReader read = new BufferedReader(new FileReader(path));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Scanner in = new Scanner(System.in);
			System.out.println("Enter the date:");
			String date = reader.readLine();
			System.out.println("Enter the list of countries (end with an empty line):");
			String[] array2 = new String [100];
			String outputFileName="result1";
			int k = 0;
			while(k<array2.length)
			{
				String input = in.nextLine();
				if(input!=null && input!="")
				{
					
					array2[k]=input;
					k++;
				
				}
				else {
					k=102;
				}
				
				
			}
		
			//System.out.println(date+" "+region);
			try {
				while(  (line = read.readLine()) != null)
				{
					array = line.split(",");
					String[] place = new String[100000];
					String[] number= new String[10000];
					for(String t : array2)
					{
						String region = t;
						region = '"'+region;
						
  
						if(array[1].equalsIgnoreCase(date) && array[0].equalsIgnoreCase(region))
						{
							
							
							{
								
								/*
						   		  * time the running code
						   		  * clean the data
						   		  */
						            start();
						            
						          

									System.out.println(array[0]+" = "+array[2]);
									float time = stop();
									/** write the cleaned output to a file */ 
									
							        writeToFile(outputFileName, array, time);
							}
							
						
					}
					
					
			
					}
				
				}
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
}
