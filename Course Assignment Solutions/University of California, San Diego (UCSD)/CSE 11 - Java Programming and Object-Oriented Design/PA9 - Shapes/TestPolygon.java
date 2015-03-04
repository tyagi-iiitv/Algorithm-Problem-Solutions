/*
 * Test program for classe CSE11_Polygon.
 */

import java.awt.*;
import objectdraw.*;

public class TestPolygon extends WindowController
{
  public void begin()
  {
    makeShape();
  }

  public void makeShape()
  {
    RandomIntGenerator randomX = new RandomIntGenerator(0,canvas.getWidth()/2);
    RandomIntGenerator randomY = new RandomIntGenerator(0,canvas.getHeight());
    Shape poly1, poly2, poly3;  // these are generic Shapes
    CSE11_Polygon poly4;
    Point[] allPts = new Point[12];
    // Randomly generate all 10 points
    for(int i = 0; i < allPts.length; i++)
    {
      allPts[i] = new Point( randomX.nextValue(), randomY.nextValue() );
    }
 
    Point[] pts1 = {allPts[0],allPts[1],allPts[2]};
    Point[] pts2 = {allPts[3],allPts[4],allPts[5],allPts[6]};
    Point[] pts3 = {allPts[7],allPts[8],allPts[9],allPts[10],allPts[11]};

    poly1 = new CSE11_Polygon( pts1 ); // 3 Point  (should be a triangle)
    poly2 = new CSE11_Polygon( pts2 ); // 4 Points (should be 4-point shape)
    poly3 = new CSE11_Polygon( pts3 ); // 5 Points (should be 5-point shape)
    poly1.move( 200, 0 );	// test CSE11_Polygon move()

    if ( poly1 instanceof CSE11_Polygon )
    {
      poly4=new CSE11_Polygon((CSE11_Polygon)poly1);// CSE11_Polygon copy ctor

      if ( poly1.equals( poly4 ) )	// test CSE11_Polygon equals()
      {
        poly1.draw( canvas, Color.BLACK, true );
        poly2.draw( canvas, Color.BLUE, true );
        poly3.draw( canvas, Color.RED, true );
      }

      // test CSE11_Polygon toString(), getCenter(), getRadius(), and
      // Point getX()/getY()

      Text text1 = new Text( poly4.toString(), poly4.getPoint(0).getX(),
                             poly4.getPoint(0).getY() + 20,
                             canvas );
    }

    if ( poly2 instanceof CSE11_Polygon )
    {
      poly4 = (CSE11_Polygon) poly2;

      Text text2 = new Text( poly4.toString(), poly4.getPoint(0).getX(),
                             poly4.getPoint(0).getY() - 40,
                             canvas );
    }

    if ( poly3 instanceof CSE11_Polygon )
    {
      poly4 = (CSE11_Polygon) poly3;

      Text text3 = new Text( poly4.toString(), poly4.getPoint(0).getX(),
                             poly4.getPoint(0).getY() - 20,
                             canvas );
    }

  }  // End of makeShape()

} // End of class 
