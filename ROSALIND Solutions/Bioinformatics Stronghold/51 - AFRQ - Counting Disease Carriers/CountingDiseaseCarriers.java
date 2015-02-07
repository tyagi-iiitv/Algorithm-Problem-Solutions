public class CountingDiseaseCarriers {
  public static void main( String[] args ) {
    double[] A = {0.206540628355,0.667617931696,0.593222684424,0.549689933521,0.516872844916,0.651792814935,0.346092725437,0.344205949265,0.681791196717,0.915360926354,0.521043327228,0.985005203312,0.911932904052,0.281639551277,0.840302647339,0.287147354305,0.216995091878,0.49154693746,0.372238981772};
    double[] out = countingDiseaseCarriers(A);
    for(int i = 0; i < out.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static double[] countingDiseaseCarriers( double[] A ) {
    double[] out = new double[A.length];
    for(int i = 0; i < A.length; ++i) {
      double pSqr = A[i];
      double p = Math.sqrt(pSqr);
      out[i] = pSqr + 2*p*(1-p);
    }
    return out;
  }
}