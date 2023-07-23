
import java.io.File;
import java.io.IOException;
import java.util.*;
/*
 * artist Kevin Chiloane
 * 23/04/2020
 */
public class ReadFromFile
{
	String[][] fileList = new String[5000][2];
	public ReadFromFile()
	{
	}
	
	public String[][] getList()
	{ 
		try
		{
			int i=0;
			Scanner in = new Scanner(new File("data/oklist.txt"));
			while(in.hasNextLine())
			{
				String dissect = in.nextLine();
				String ID = dissect.split(" ")[0];
				String Name = dissect.split(" ")[1];
				String lastName = dissect.split(" ")[2];
				String student = Name + " " + lastName;
				fileList[i][0] = ID;
				fileList[i][1] = student;
				i=i+1;
		    }
		}
		catch (IOException e)
		{
			System.out.println("insert correct file");
			e.printStackTrace();
		}
		return fileList;
	}
}
