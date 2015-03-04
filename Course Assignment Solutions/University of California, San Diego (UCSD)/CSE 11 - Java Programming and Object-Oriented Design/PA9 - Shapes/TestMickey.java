/*
 * Test program for classes Point and Circle for Shapes inheritance
 * exercise - CSE 11 Fall 2012.
 *
 * Program draws a Mickey Mouse figure.
 */

import java.awt.*;
import objectdraw.*;

public class TestMickey extends WindowController
{
  public void begin()
  {
    makeMickey();
  }

  public void makeMickey()
  {
    Shape c1, c2, c3;  // these are generic Shapes
    Circle c4;
    Point p1, p2, p3;

    p1 = new Point( canvas.getWidth()/2, canvas.getHeight()/2 );
    c1 = new Circle( p1, 100 );

    p2 = new Point( p1 );	// test Point copy ctor
    p2.move( -100, -100 );	// test Point move()
    c2 = new Circle( p2, 50 );

    p3 = new Point ( p2 );	// test Point copy ctor

    if ( p2.equals( p3 ) )	// test Point equals()
    {
      c3 = new Circle( p3, 50 );
      c3.move( 200, 0 );	// test Circle move()
    }
    else
    {
      c3 = c2;
    }

    if ( c1 instanceof Circle )
    {
      c4 = new Circle( (Circle) c1 );	// test Circle copy ctor

      if ( c1.equals( c4 ) )	// test Circle equals()
      {
        c1.draw( canvas, Color.BLACK, true );	// black and filled
        c2.draw( canvas, Color.BLACK, true );
        c3.draw( canvas, Color.BLACK, true );
      }

      // test Circle toString(), getCenter(), getRadius(), and
      // Point getX()/getY()

      Text text1 = new Text( c4.toString(), c4.getCenter().getX(),
                             c4.getCenter().getY() + c4.getRadius() + 20,
                             canvas );

      text1.move( -text1.getWidth()/2, 0 );	// center text under circle
    }

    if ( c2 instanceof Circle )
    {
      c4 = (Circle) c2;

      Text text2 = new Text( c4.toString(), c4.getCenter().getX(),
                             c4.getCenter().getY() - c4.getRadius() - 40,
                             canvas );

      text2.move( -text2.getWidth()/2, 0 );	// center text over circle
    }

    if ( c3 instanceof Circle )
    {
      c4 = (Circle) c3;

      Text text3 = new Text( c4.toString(), c4.getCenter().getX(),
                             c4.getCenter().getY() - c4.getRadius() - 20,
                             canvas );

      text3.move( -text3.getWidth()/2, 0 );	// center text over circle
    }

  }  // End of makeMickey()

} // End of class 
