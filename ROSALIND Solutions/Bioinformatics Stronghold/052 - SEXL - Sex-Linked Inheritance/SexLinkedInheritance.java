public class SexLinkedInheritance {
  public static void main( String[] args ) {
    double[] A = {0.017409081144,0.141232966611,0.2214827724,0.223347418787,0.231707054489,0.263386436862,0.352400654106,0.380676173811,0.391985453076,0.502451350974,0.542460974203,0.722208415701,0.827049370003,0.883303997169,0.958903707024,0.967118409502,0.967615097769};
    double[] out = sexLinkedInheritance(A);
    for(int i = 0; i < A.length; ++i) {
      System.out.print(out[i] + " ");
    }
  }
  
  public static double[] sexLinkedInheritance( double[] A ) {
    double[] B = new double[A.length];
    for(int i = 0; i < A.length; ++i) {
      B[i] = A[i] * 2*(1-A[i]);
    }
    return B;
  }
}