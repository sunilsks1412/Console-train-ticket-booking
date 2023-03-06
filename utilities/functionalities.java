package utilities;
import java.util.*;

import utilities.user;

public class functionalities
{
    static Scanner sc = new Scanner(System.in);
    public String number;
    public String from;
    public String to;
    private int seats;
    // private int passengers=0;
    // private int seatsLeft=seats;
    // private int booked=0;
    private int wl=0;
    static public int[] stations = new int[5];
    static public int[] waiting = new int[5];
    static public HashMap<Integer,String> stoppings = new HashMap<Integer,String>();
    static HashMap<String,Ticket> trains = new HashMap<String,Ticket>();
    static LinkedHashMap<String,Ticket> waitlist = new LinkedHashMap<String,Ticket>();
    static HashMap<String,Ticket> tickets = new HashMap<>();

    static int waitSeats = 5;
    
    public functionalities(String number,String from ,String to,int seats, int wl)
    {
        this.number = number;
        this.from = from;
        this.to = to;
        this.seats = seats;
        Arrays.fill(stations,0);
        this.wl = wl;
        stoppings.put(0,"Coimbatore");
        stoppings.put(1,"Tirupur");
        stoppings.put(2,"Erode");
        stoppings.put(3,"Salem");
        stoppings.put(4,"Chennai");
    }

    public int availableSeats(int from,int to)
    {
        for(int i=from;i<to;i++)
        {
            if(stations[i-1]==seats)
            {
                return 0;
            }
            
        }

        int min = Integer.MAX_VALUE;

        for(int i=from;i<to;i++)
        {
            min = Math.min(min,seats-stations[i-1]);
            
        }
        return min;
    }
    public int WaitlistAvailable(int from,int to)
    {
        for(int i=from;i<to;i++)
        {
            if(waiting[i-1]==wl)
            {
                return 0;
            }
        }
        int min = Integer.MAX_VALUE;

        for(int i=from;i<to;i++)
        {
            min = Math.min(min,waitSeats -waiting[i-1]);
            
        }
        return min;
    }
    public void bookTickets(user user)
    {   
        System.out.println("Enter the Boarding Point: ");
        System.out.println("1. Coimbatore");
        System.out.println("2. Tirupur");
        System.out.println("3. Erode");
        System.out.println("4. Salem");
        System.out.println("5. Chennai");
        int from = sc.nextInt();
        System.out.println("Enter the Destination: ");
        int to = sc.nextInt();
        if(from>to || from==to)
        {
            System.out.println("Invalid Input");
            System.out.println("Please enter the boarding point and destination again");
            return;
        }
        int availableSeats = availableSeats(from,to);
        int waitingSeats = WaitlistAvailable(from,to);
        System.out.println("Available seats: "+availableSeats);
        System.out.println("Waiting seats: "+waitingSeats);
        System.out.println("Enter the number of seats to be booked: ");
        int seats = sc.nextInt();
        if(availableSeats(from,to) > 0 && seats <= availableSeats)
        {
            for (int i = 0; i < seats; i++)
            {
                Random rand = new Random(); 
                int ticketNumber = rand.nextInt(1000000);
                // int seatsAvailable = 0;
                for(int j=from;j<to;j++)
                {
                    stations[j-1]++;
                }
                user u = new user(user.name,user.password);
                tickets.put(ticketNumber+"",new Ticket(Integer.toString(ticketNumber),from,to,number,"confirmed",u.name));
                System.out.println("Your ticket has been booked successfully");
                System.out.println("Your ticket number: " + ticketNumber);
            }
        }
        else if (seats <= (availableSeats+waitingSeats ))
        {
            System.out.println(availableSeats+"will be confirmed!    "+(seats - availableSeats)+" will be waitlisted");
            System.out.println("Do you want to waitlist? (Y/N)");
            Scanner sc = new Scanner(System.in);
            String confirm = sc.next();
            if(!confirm.equals("Y") && !confirm.equals("y"))
            { 
                return;
            }
 
            for (int i = 0; i < availableSeats; i++)
            {
                Random rand = new Random(); 
                int ticketNumber = rand.nextInt(1000000);
                // int seatsAvailable = 0;
                for(int j=from;j<to;j++)
                {
                    stations[j-1]++;
                }
                user u = new user(user.name,user.password);
                tickets.put(ticketNumber+"",new Ticket(Integer.toString(ticketNumber),from,to,number,"confirmed",u.name));
                System.out.println("Your ticket has been booked successfully");
                System.out.println("Your ticket number: " + ticketNumber);
            }

            for (int i = 0; i < (seats-availableSeats); i++) 
            {
                user u = new user(user.name,user.password);
                wl++;
                for (int j=0;j<to;i++)
                {
                    waiting[j-1]++;
                }
                waitlist.put(Integer.toString(wl),new Ticket(number,from,to,wl+"","wl",u.name));
                System.out.println("Your are waitlisted");
                System.out.println("Your ticket number: " + wl);
            }
            
        }
       else 
       {
           System.out.println("No seats available");
       }
    }
    public void cancelTicket(user user)
    {
        boolean flag = true;
        while(flag)
        {
            System.out.println("Enter your ticket number: ");
            String ticketNumber = sc.next();
            if(tickets.containsKey(ticketNumber))
            {
                Ticket ticket = tickets.get(ticketNumber);
                for(int i=ticket.from;i<ticket.to;i++)
                {
                    stations[i-1]--;
                }
                tickets.remove(ticketNumber);
                System.out.println("Ticket of " + ticketNumber + " has been cancelled successfully");
                if(waitlist.size()>0)
                {
                    String key = waitlist.keySet().iterator().next();
                    Ticket waitlistTicket = waitlist.get(key);
                    if((ticket.from <= waitlistTicket.from) && (ticket.to >= waitlistTicket.to))
                    {
                        user u = new user(user.name,user.password);
                        for(int i=waitlistTicket.from;i<waitlistTicket.to;i++)
                        {
                            stations[i-1]++;
                        }
                        System.out.println(key);
                        tickets.put(waitlistTicket.id,new Ticket(waitlistTicket.id,waitlistTicket.from,waitlistTicket.to,waitlistTicket.train_id,"confirmed",u.name));
                        System.out.println("Ticket of number " + key + " has been confirmed");
                        waitlist.remove(key);
                    }
                    else
                    {
                        System.out.println("No tickets are confirmed");
                    }
                    System.out.println("Do you want to cancel more tickets? (Y/N)");
                    String confirm = sc.next();
                    if(!confirm.equals("Y") && !confirm.equals("y"))
                    {
                        flag = false;
                    }        
                }
        }
        else
        {
            System.out.println("Invalid Ticket Number");
        }
        }
    }
    public void printTicket(user user)
    {
        System.out.println(" You are under My Details Section");
        System.out.println("Enter your ticket number: ");
        String ticketNumber = sc.next();
        if(tickets.containsKey(ticketNumber))
        {
            Ticket ticket = tickets.get(ticketNumber);
            user u = new user(user.name,user.password);
            System.out.println("Your name: " + u.name);
            System.out.println("Your ticket number: " + ticket.id);
            System.out.println("Your train number: " + number);
            System.out.println("Your boarding point: " + stoppings.get(ticket.from-1));
            System.out.println("Your destination: " + stoppings.get(ticket.to-1));
            System.out.println("Thank you for using our service");
        }
        else
        {
            System.out.println("Invalid Ticket Number");
        }
    }
}