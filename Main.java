import java.util.*;
import sample.functionalities;
import sample.services;
import sample.user;

public class Main
{
    public static void main(String[] args) 
    {   user k = new user("sunil","1234");
        HashMap<String,user> users = new HashMap<>();
        users.put("sunil",k);
        Scanner sc = new Scanner(System.in);
        Boolean flag = true;
        while(flag)
        {
            System.out.println("\n");
            System.out.println("**********************      Welcome to the Railway Reservation System       **********************");
            System.out.println("1. New User");
            System.out.println("2. Existing User");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            if(choice==1)
            {
                System.out.println("Enter your name: ");
                String name = sc.next();
                System.out.println("Enter new password: ");
                String password = sc.next();
                user u = new user(name,password);
                users.put(name,u);
                System.out.println("User"+name+"created successfully");
            }
            else if(choice==2)
            {
                System.out.println("Enter your name: ");
                String name = sc.next();
                System.out.println("Enter your password: ");
                String password = sc.next();
                if(users.containsKey(name))
                {
                    user u = users.get(name);
                    if(u.password.equals(password))
                    {
                        services.options(users.get(name));
                    }
                }
                else
                {
                    System.out.println("Invalid User");
                }
            }
            else if(choice==3)
            {
                flag = false;
                System.out.println("Thank you for using our Railway Reservation System");
            }
            else
            {
                System.out.println("Invalid Choice");
            }
        }
    }
}