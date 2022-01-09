// Name: Ria Talwar
// Date: July 12, 2021

import java.util.*;
public class Tester
{
  public static void main(String[] args)
  {
    //Create a HotelManagement object
    Scanner scan = new Scanner(System.in);
    HotelManagement hotel = new HotelManagement();
    int choice = 0;
    while (choice != 9)
    {
      System.out.println ();
      System.out.println ("Menu: ");
      System.out.println ("1-print all rooms in the hotel");
      System.out.println ("2-search for a tenant");
      System.out.println ("3-raise a room rate");
      System.out.println ("4-list of all customers");
      System.out.println ("5-list of available rooms");
      System.out.println ("6-Request a room");
      System.out.println ("7-Cancel a room");
      System.out.println ("8-list of rooms with rate less than limit");
      System.out.println ("9-Exit");
      System.out.print("\nEnter action: ");
      choice = Integer.parseInt(scan.nextLine());

      if (choice > 9 || choice < 1) System.out.println("Invalid choice.");
      else if (choice == 1) hotel.printAll();
      else if (choice == 2)
      {
        System.out.print("Enter room number: ");
        int roomNum = Integer.parseInt(scan.nextLine());
        System.out.println("Guest's name: " + hotel.occupantName(roomNum));
      }
      else if (choice == 3)
      {
        System.out.print("Enter room number: ");
        int roomNum = Integer.parseInt(scan.nextLine());
        System.out.print("Enter new rate: ");
        int rate = Integer.parseInt(scan.nextLine());
        hotel.raiseRate(roomNum, rate);
      }
      else if (choice == 4)
      {
        ArrayList<String> customers = hotel.allCustomers();
        System.out.println(customers);
      }
      else if (choice == 5)
      {
        ArrayList<Integer> available = hotel.availableRooms();
        System.out.println(available);
      }
      else if (choice == 6)
      {
        System.out.print("Enter guest's name: ");
        String name = scan.nextLine();
        hotel.requestRoom(name);
      }
      else if (choice == 7)
      {
        System.out.print("\nEnter guest's name: ");
        String name = scan.nextLine();
        hotel.cancelRoom(name);
      }
      else if (choice == 8)
      {
        System.out.print("Enter limit: ");
        int limit = Integer.parseInt(scan.nextLine());
        ArrayList<Integer> cheap = hotel.cheapRooms(limit);
        System.out.println(cheap);
      }
    }
    System.out.println("Goodbye.");
  }
}
