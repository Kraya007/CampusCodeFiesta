
import java.util.Scanner;
import java.lang.Math;

public class triangle 
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the length of the first side: ");
		int a = in.nextInt(); 
		System.out.print("Enter the length of the second side: ");
		int b = in.nextInt();
		System.out.print("Enter the length of the third side: ");
		int c = in.nextInt();
		double s = (a+b+c)/2;
		double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
		System.out.println("The area of the triangle with sides of length "+a+" and "+b+" and "+c+" is "+ area+".");
		
	}
}
