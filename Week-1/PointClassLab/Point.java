// Name: Ria Talwar
// Date: July 12, 2021

public class Point
{
  private int x;
  private int y;
  // Constructors
  public Point()
  {
    x = 0;
    y = 0;
  }
  public Point(int xVal, int yVal)
  {
    x = xVal;
    y = yVal;
  }
  // Accessor methods
  public int getX()
  {
    return x;
  }
  public int getY()
  {
    return y;
  }
  // Modifier methods
  public void setX(int newX)
  {
    x = newX;
  }
  public void setY(int newY)
  {
    y = newY;
  }
  public String toString()
  {
    return "(" + x + ", " + y + ")";
  }
}
