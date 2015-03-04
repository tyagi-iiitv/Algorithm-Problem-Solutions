public class HW3
{
  public static void main( String args[] )
  {
    int n  = 0;
    int Gn = 0;

    do
    {
      if(n == 0)
      {
        Gn = 1;
        System.out.println( "G(" + n + ") = " + Gn );
        n++;
      }

      else
      {
        Gn = Gn + 2*n - 1;
        System.out.println( "G(" + n + ") = " + Gn );
        n++;
      }
    }
    
    while(n < 51);
  }
}