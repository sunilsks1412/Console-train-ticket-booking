package utilities;
import java.util.*;

public class services 
{
    public static void options(user user)
    {
        Scanner sc = new Scanner(System.in);
        functionalities t1 = new functionalities("12345","Coimbatore","Chennai",10,5);
        while(true)
        {
            System.out.println("Welcome to the Railway Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. My Ticket");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            if(choice==1)
            {
                t1.bookTickets(user);
            }
            else if(choice==2)
            {
                t1.cancelTicket(user);
            }
            else if(choice==3)
            {
                t1.printTicket(user);
            }
            else if(choice==4)
            {
                System.out.println("********************* Thank you for using our service *********************");
                break;
            }
            else
            {
                System.out.println("Invalid Input");  
            }
        }
    }
}
