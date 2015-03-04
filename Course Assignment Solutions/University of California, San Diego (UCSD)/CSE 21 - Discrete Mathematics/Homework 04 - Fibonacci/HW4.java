public class HW4
{
  public static void main( String args[] )
  {
    int n   = 0;
    int Gn1 = 0;
    int Gn2 = 0;
    int Gn3 = 0;
    
    do
    {
      if(n == 0)
      {
        Gn1 = 0;
        Gn2 = 1;
        System.out.println( "G(0) = " + Gn1 );
        System.out.println( "G(1) = " + Gn2 );
        n = 2;
      }
      
      else
      {
        Gn3 = Gn2 + Gn1;
        System.out.println( "G(" + n + ") = " + Gn3 );
        Gn1 = Gn2;
        Gn2 = Gn3;
        n++;
      }
    }
    
    while(n < 43);
  }
}