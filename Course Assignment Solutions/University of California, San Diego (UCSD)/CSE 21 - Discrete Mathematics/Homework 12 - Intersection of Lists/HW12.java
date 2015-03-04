/*
 * Name: Alexander Niema Moshiri
 * Student ID: A09850737
 * Login: cs21ffs
 * Filename: hw12.java
 *
 * Compile on ieng6.ucsd.edu: javac HW12.java
 * Run on ieng6.ucsd.edu: java HW12
 */
public class HW12
{
  public static void main( String args[] )
  {
    int arrayX[] = {1, 3, 5, 8, 9, 2, 4, 6, 7, 10};
    int sizeX = arrayX.length;
    int arrayY[] = {11, -2, 1, 5, 10, 0, 8, 2};
    int sizeY = arrayY.length;

/*  TEST ARRAY X
    for(int i = 0; i < 10; ++i)
    {
      System.out.println(arrayX[i] + " ");
    }
*/
/*  TEST ARRAY Y
    for(int j = 0; j < 9; ++j)
    {
      System.out.println(arrayY[j] + " ");
    }
*/

    int numIntersects = 0;

    for(int i = 0; i < sizeX; ++i)
    {
      for(int j = 0; j < sizeY; ++j)
      {
        if(arrayX[i] == arrayY[j])
        {
          System.out.println(arrayX[i]);
          numIntersects++;
        }
      }
    }

    System.out.println("Size of intersection of X and Y = " + numIntersects);
  }
}
