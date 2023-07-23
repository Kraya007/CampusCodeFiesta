
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;



public class VaccineBSTApp
{

	public static float startTime = 0;
	Node root;
	//operation count wrote to text file
	public static void countFile(String file)
	{
		File file1 = new File(file);
	}
	
	//text file created to write count operations
	public static void writeToile(String file, int currentNode) 
	{	
		try
		{
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(currentNode);
			fileWriter.close();
		}
		catch (IOException e)
		{
			
		}
	}
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
     * @param data
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
    

	public void printAll(Node currentNode)
	{
		if(currentNode != null)
		{
			printAll(currentNode.left);
			System.out.println(currentNode);
			printAll(currentNode.right);
			
		}
	}
	
	public  Node printVaccine(String region,String a,String b)
	{
		countFile("data\\VaccineBStApp.txt");
		Node currentNode = root;
		
		while(!(currentNode == null))
		{
			if(currentNode.region.compareTo(region)> 0)
			{
				currentNode = currentNode.left;
			}
			else
			{
				currentNode = currentNode.right;
			}
			if(currentNode == null)
			{
				continue;
			}
			
			
		}
		System.out.println(a+" = "+b);
		return currentNode;
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
			VaccineBSTApp vaccine = new VaccineBSTApp();
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
					String outputFileName = "result";
					for(String t : array2)
					{
						String region = t;
						region = '"'+region;
						
  
						if(array[1].equalsIgnoreCase(date) && array[0].equalsIgnoreCase(region))
						{
							{
								//vaccine.insertNode(array[0],array[2]);
								{
					            /*
						   		  * time the running code
						   		  * clean the data
						   		  */
						            start();
									
									System.out.println(vaccine.printVaccine(array[0], array[0], array[2]));
									float time = stop();
									/** write the cleaned output to a file */ 	
							        writeToFile(outputFileName, array, time); 
								}
								
							}
							
						
					}
				
					//System.out.println(vaccine.printVaccine("Botswana"));
					
					
			
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
	
	
	public void insertNode(String region, String number)
	{
		Node node = new Node(region, number);
		
		if(root == null)
		{
			root = node;
		}       
		
		else
		{
			Node currentNode = root;
			Node parent;
			while(true)
			{
				parent = currentNode;
				if(region.compareTo(currentNode.region) < 0)
				{
					currentNode = currentNode.left;
				
					if(currentNode == null)
					{
						currentNode.left = node;
						return;
					}
				}
				else
				{
					currentNode = currentNode.right;
					
					if(currentNode == null)
					{
						currentNode.right =node;
						return;
					}
				}
		}
		
	 }
	
}
}

class Node
{
	String region;
	Object number;
	String lastname;
	Node left;
	Node right;
	
	public Node(String region, Object number )
	{
		this.region =region;
		this.number = number;
		//this.lastname = lastname;
	}
	
}



