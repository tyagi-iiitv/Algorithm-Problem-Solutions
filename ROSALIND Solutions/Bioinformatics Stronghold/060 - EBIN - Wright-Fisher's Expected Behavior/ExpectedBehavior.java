public class ExpectedBehavior {
    public static void main( String[] args ) {
        int n = 869534;
        double[] P = {0,0.0356100971814,0.149007978283,0.167340226643,0.170773464092,0.185654000492,0.288188062629,0.297058101742,0.346037695699,0.461982913969,0.482430652823,0.513776757864,0.548493396803,0.58245191964,0.627537141202,0.754006403288,0.802981721733,0.916726363594,0.924063967193,1};
        for(double p : expectedBehavior(n,P)) {
            System.out.print(p + " ");
        }
    }
    
    public static double[] expectedBehavior( int n, double[] P ) {
        double[] out = new double[P.length];
        for(int i = 0; i < P.length; ++i) {
            out[i] = n * P[i];
        }
        return out;
    }
}