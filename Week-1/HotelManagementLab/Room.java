// Name: Ria Talwar
// Date: July 12, 2021

public class Room
{
  private String type;
  private Customer custom;
  private int number;
  private int rate;
  private boolean booked;

  // Constructor
  public Room(String t, int num, int r, Customer c)
  {
    type = t;
    number = num;
    rate = r;
    custom = c;
    booked = false;
  }

  // Accessor methods
  public String getType()
  {
    return type;
  }
  public Customer getCustomer()
  {
    return custom;
  }
  public int getNumber()
  {
    return number;
  }
  public int getRate()
  {
    return rate;
  }
  public boolean isBooked()
  {
    return booked;
  }

  // Modifier methods
  public void setCustomer(Customer c)
  {
    custom = c;
  }
  public void setBooked(boolean b)
  {
    booked = b;
  }
  public void setRate(int r)
  {
    rate = r;
  }

  public String toString()
  {
    return type + " " + number + " " + rate + " " + custom + " " + booked;
  }
}
