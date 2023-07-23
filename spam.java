import java.util.Scanner;

public class spam
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter first name:");
		String name = in.next();
		System.out.println("Enter last name:");
		String lastName = in.next();
		System.out.println("Enter sum of money in USD:");
		int money = in.nextInt();
		System.out.println("Enter country name:");
		in.nextLine();
		String country = in.nextLine();
		System.out.println();
		double percentage = money*0.3;
		
		
		System.out.println("Dearest "+name);
		System.out.println("It is with a heavy heart that I inform you of the death of my father,");
		System.out.println("General Fayk "+lastName+", your long lost relative from Mapsfostol.");
		System.out.println("My father left the sum of "+money+"USD for us, your distant cousins.");
		System.out.println("Unfortunately, we cannot access the money as it is in a bank in "+country+".");
		System.out.println("I desperately need your assistance to access this money.");
		System.out.println("I will even pay you generously, 30% of the amount - "+percentage+"USD,");
		System.out.println("for your help.  Please get in touch with me at this email address asap.");
		System.out.println("Yours sincerely");
		System.out.println("Frank "+lastName);
		
		
		
		
		
		
		
		
		
		
		
		

		
		
			
	}
}
