import java.util.Scanner;

/*
*

* Program to convert an amount of minutes into an 
* equivalent amount  of days, hours and minutes.
*
* Name: Stephan Jamieson
* Student Number: JMSSTE001
*/
public class time
{
	 public static void main(final String[] args)
	 {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the hours: ");
		int hours = in.nextInt();
		System.out.print("Enter the minutes: ");
		int minutes = in.nextInt();
		System.out.print("Enter the seconds: ");
		int seconds = in.nextInt();
		if((hours>= 0 && hours <= 23)&&(minutes>=0 && minutes <= 59)&&(seconds>=0&&seconds<=59))
			
		{
			System.out.println("Your time is valid.");
		}
		else
		{
			System.out.println("Your time is invalid.");
		}
		
	 }
}
        




	

   