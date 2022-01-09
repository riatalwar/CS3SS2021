import java.util.*;

public class Graph
{
  public static double calculateDistance(Point p1, Point p2)
  {
    return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
  }

  public static double calculateSlope(Point	p1, Point p2)
  {
    if (p1.getX() == p2.getX()) return 0;
    return ((double) p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
  }

  public static boolean isHorizontal(Point p1, Point p2)
  {
    return p1.getY() == p2.getY();
  }

  public static double maxDistOrigin(Point[] points)
  {
    double maxDist = 0;
    Point origin = new Point();
    double dist;
    for (int i = 0; i < points.length; i++)
    {
      dist = calculateDistance(origin, points[i]);
      if (dist > maxDist) maxDist = dist;
    }
    return maxDist;
  }

  public static void main(String [] args)
  {
    Point p1 = new Point();
    Point p2 = new Point(2, 3);
    Point p3 = new Point(4, 3);
    Point p4 = new Point(2, 4);
    Point[] points = new Point[4];
    points[0] = p1;
    points[1] = p2;
    points[2] = p3;
    points[3] = p4;
    System.out.println(maxDistOrigin(points));
    System.out.println(calculateDistance(p1, p2));
    System.out.println(calculateSlope(p2, p4));
    System.out.println(calculateSlope(p3, p4));
    System.out.println(isHorizontal(p2, p3));
  }
}
