// Name: Ria Talwar
// Date: July 12, 2021

import java.util.*;
import java.io.*;
public class HotelManagement
{
  private Room [] rooms;
  private ArrayList<String> waitingList;

  public HotelManagement()
  {
    try
    {
      Scanner scan = new Scanner(new File("hotel.txt"));
      int count = Integer.parseInt(scan.nextLine());
      rooms = new Room[count];
      for (int i = 0; i < rooms.length; i++)
      {
        String line = scan.nextLine();
        String [] arr = line.split(" ");
        String type = arr[0];
        int number = Integer.parseInt(arr[1]);
        int rate = Integer.parseInt(arr[2]);
        String c = arr[3];
        Customer custom = null;
        Room r;
        if (!c.equals("available"))
        {
          custom = new Customer(arr[3]);
          r = new Room(type, number, rate, custom);
          rooms[i] = r;
          rooms[i].setBooked(true);
        }
        else
        {
          r = new Room(type, number, rate, null);
          rooms[i] = r;
        }
      }
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    waitingList = new ArrayList<String>();
  }

  public int bookedRooms()
  {
    // Return the number of booked rooms
    int bookedCount = 0;
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i].isBooked()) bookedCount++;
    return bookedCount;
  }
  public ArrayList<Integer> cheapRooms(int limit)
  {
    // Return list of rooms that cost less than a certain amount
    ArrayList<Integer> cheap = new ArrayList<Integer>();
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i].getRate() < limit) cheap.add(rooms[i].getNumber());
    return cheap;
  }
  public void requestRoom(String guest)
  {
    // Check if there are empty rooms
    if (rooms.length <= bookedRooms())
    {
      waitingList.add(guest);
      System.out.println("Hotel is full.");
      return;
    }
    // Assign customer to first empty room
    for (int i = 0; i < rooms.length; i++)
    {
      if (!rooms[i].isBooked())
      {
        rooms[i].setCustomer(new Customer(guest));
        rooms[i].setBooked(true);
        return;
      }
    }
  }
  public void cancelRoom(String name)
  {
    // Cancels room occupied by name
    for (int i = 0; i < rooms.length; i++)
    {
      if (rooms[i].getCustomer() != null && rooms[i].getCustomer().getName().equals(name))
      {
        rooms[i].setCustomer(null);
        rooms[i].setBooked(false);
        return;
      }
    }
  }
  public String occupantName(int roomNum)
  {
    for (int i = 0; i < rooms.length; i++)
    {
      if (rooms[i].getNumber() == roomNum && rooms[i].getCustomer() != null)
        return rooms[i].getCustomer().getName();
    }
    return null;
  }

  //Create a method that changes the rate of the romms in the second floor to new rate
  public void raiseRate(int roomNumber, int newRate)
  {
    for (int i = 0; i < rooms.length; i++)
    {
      if (rooms[i].getNumber() == roomNumber)
        rooms[i].setRate(newRate);
    }
  }
  //create a method that returns the list of customers in the hotel
  public ArrayList<String> allCustomers()
  {
    ArrayList<String> customers = new ArrayList<String>();
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i].getCustomer() != null) customers.add(rooms[i].getCustomer().getName());
    return customers;
  }
  //create a method that returns the list of room numbers available
  public ArrayList<Integer> availableRooms()
  {
    ArrayList<Integer> available = new ArrayList<Integer>();
    for (int i = 0; i < rooms.length; i++)
      if (!rooms[i].isBooked()) available.add(rooms[i].getNumber());
    return available;
  }
  //create a method that prints the rooms with their types, numbers, rates, and the tenants' names
  public void printAll()
  {
    for (int i = 0; i < rooms.length; i++)
      System.out.println(rooms[i].toString());
  }
}
