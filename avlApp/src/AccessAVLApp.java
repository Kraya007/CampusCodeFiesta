import java.util.*;
import java.io.*;


public class AccessAVLApp 
{
	public static void opCountFile(String fileRedirect)
	{
		File file1 = new File(fileRedirect);
	}
	public static void  writeToFile(String fileRedirect, String info)
	{
		try
		{
			FileWriter file2write = new FileWriter(fileRedirect);
			file2write.write(info);
			file2write.close();
		}
		catch(IOException e)
		{
		}
	}
	
	public static void printAllStudent(AVLTree<Student> aStudent)
	{
		aStudent.treeOrder();
	}
	
	
	
	public static void printStudent(AVLTree<Student> aStudent, Student o)
	{	
		opCountFile("data/AccessAVLAppCount.txt");
		if(aStudent.find(o) != null)
		{
			writeToFile("data/AccessAVLAppCount.txt",Integer.toString(aStudent.getopCount()));
			System.out.println(aStudent.find(o).getInfo().getaName());
			System.out.println("Operation count: "+aStudent.opCount);
		}
		else
		{
			System.out.println("Access denied!");
		}
	}
	
	public static AVLTree<Student> aList(ReadFromFile file)
	{ 
		String[][] aList = file.getList();
		AVLTree<Student> anStudent = new AVLTree<Student>();
		for(int i=0; i < 5_000; i++ )
		{	if(aList[i][0] ==null)
			{
				break;
			}
			String aId = aList[i][0];
			String aName = aList[i][1];
			anStudent.insert(new Student(aId,aName));
		}
		return anStudent;
		
	}
	
	public static void main(String[] args)
	{
		AccessAVLApp control = new AccessAVLApp();
		AVLTree<Student> aStudent = new AVLTree<Student>();
		ReadFromFile file = new ReadFromFile();
		aStudent = aList(file);
		
		try
		{
			Student aStudentId = new Student(args[0]);
			printStudent(aStudent, aStudentId);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			printAllStudent(aStudent);
		}	 
	}
}				