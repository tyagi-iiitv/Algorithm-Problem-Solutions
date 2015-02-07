import java.util.*;
public class OrderingStringsOfVaryingLengthLexicographically {
    public static String alphabet = "";
    public static void main( String[] args ) {
        alphabet = "LQHCIFATPYM";
        int n = 3;
        ArrayList<String> out = orderStrings(alphabet,n);
        for(int i = 0; i < out.size(); ++i) {
            System.out.println(out.get(i));
        }
    }
    
    public static ArrayList<String> orderStrings( String alphabet, int n ) {
        ArrayList<String> out = new ArrayList<>();
        for(int i = 1; i <= n; ++i) {
            permute(alphabet,i,"",out);
        }
        Collections.sort(out,new Comparator<String>() {
            public int compare( String s1, String s2 ) {
                for(int i = 0; i < s1.length() && i < s2.length(); ++i) {
                    int ind1 = alphabet.indexOf(s1.charAt(i));
                    int ind2 = alphabet.indexOf(s2.charAt(i));
                    if(ind1 > ind2) {
                        return 1;
                    }
                    else if(ind1 < ind2) {
                        return -1;
                    }
                }
                if(s1.length() == s2.length()) {
                    return 0;
                }
                else if(s1.length() > s2.length()) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        return out;
    }
    
    public static void permute( String alphabet, int length, String prefix, ArrayList<String> list ) {
        if(length == 0) {
            list.add(prefix);
            return;
        }
        for(int i = 0; i < alphabet.length(); ++i) {
            permute(alphabet,length - 1,prefix + alphabet.charAt(i),list);
        }
    }
}