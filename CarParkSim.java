import java.util.Scanner;
/**
 * Write a description of class CarParkSimSolution here.
 *
 * @author 
 * @version 1
  */
public class CarParkSim {
    
    private CarParkSim() {}
    
    public static void main(final String[] args) {
        final Scanner keyboard = new Scanner(System.in);
        final Clock clock = new Clock(new Time("00:00:00"));
        final Register register = new Register();
        System.out.println("Car Park Simulator");
        System.out.printf("The current time is %s.\n", clock.examine());
        System.out.println("Commands: advance {minutes}, arrive, depart, quit.");
        System.out.print(">");
        String input = keyboard.next().toLowerCase();
        while (!input.equals("quit")) {
            if (input.equals("advance")) {
                clock.advance(new Duration("minute", keyboard.nextInt()));  
                System.out.printf("The current time is %s.\n", clock.examine());
            }
            else if (input.equals("arrive")) {
                final Ticket ticket = new Ticket(clock.examine());
                register.add(ticket);
                System.out.printf("Ticket issued: %s.\n", ticket);
            }
            else if (input.equals("depart")) {
                final String ID = keyboard.next();
                if (!register.contains(ID)) {
                    System.out.println("Invalid ticket ID.");
                }
                else {
                    final Ticket ticket = register.retrieve(ID);
                    System.out.printf("Ticket details: %s.\n", ticket);
                    System.out.printf("Current time: %s.\n", clock.examine());
                    System.out.printf("Duration of stay: %s.\n", ticket.age(clock.examine()).toString("hour", "minute"));
                }
            }
            else {
                System.out.println("That command is not recognised.");
                System.out.println("Commands: advance <minutes>, arrive, depart, quit.");
            }            
            System.out.print(">");
            input = keyboard.next().toLowerCase();
        }            
        System.out.println("Goodbye.");
    }

}
