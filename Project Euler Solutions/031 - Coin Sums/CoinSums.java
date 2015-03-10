public class CoinSums {
  public static void main( String[] args ) {
    int n = 200;
    System.out.println(coinSums(n));
  }
  
  public static int coinSums( int n ) {
    int[] coins = {1,2,5,10,20,50,100,200};
    int[] max = new int[coins.length];
    for(int i = 0; i < max.length; ++i) {
      max[i] = n/coins[i];
    }
    int count = 0;
    for(int a = 0; a <= max[0]; ++a) {
      for(int b = 0; b <= max[1]; ++b) {
        for(int c = 0; c <= max[2]; ++c) {
          for(int d = 0; d <= max[3]; ++d) {
            for(int e = 0; e <= max[4]; ++e) {
              for(int f = 0; f <= max[5]; ++f) {
                for(int g = 0; g <= max[6]; ++g) {
                  for(int h = 0; h <= max[7]; ++h) {
                    int sum = a*coins[0] + b*coins[1] + c*coins[2] + d*coins[3] + e*coins[4] + f*coins[5] + g*coins[6] + h*coins[7];
                    if(sum == n) {
                      ++count;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return count;
  }
}