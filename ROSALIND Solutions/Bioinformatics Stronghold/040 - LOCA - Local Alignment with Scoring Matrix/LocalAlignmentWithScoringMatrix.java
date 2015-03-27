/**
 *  LOCAL ALIGNMENT PROBLEM
 *
 *  Find the highest-scoring local alignment between two strings.
 *
 *  Given:  Two protein strings.
 *  Return: The maximum score of a local alignment of the strings, followed by a local alignment of these strings achieving the maximum score. Use the PAM250 scoring matrix and indel penalty sigma = 5. (If multiple local alignments achieving the maximum score exist, you may return any one.)
 */

import java.util.*;
public class LocalAlignmentWithScoringMatrix {
    public static String aminosAlpha = "ACDEFGHIKLMNPQRSTVWY";
    static int[][] pam250 = {{2,-2,0,0,-3,1,-1,-1,-1,-2,-1,0,1,0,-2,1,1,0,-6,-3},{-2,12,-5,-5,-4,-3,-3,-2,-5,-6,-5,-4,-3,-5,-4,0,-2,-2,-8,0},{0,-5,4,3,-6,1,1,-2,0,-4,-3,2,-1,2,-1,0,0,-2,-7,-4},{0,-5,3,4,-5,0,1,-2,0,-3,-2,1,-1,2,-1,0,0,-2,-7,-4},{-3,-4,-6,-5,9,-5,-2,1,-5,2,0,-3,-5,-5,-4,-3,-3,-1,0,7},{1,-3,1,0,-5,5,-2,-3,-2,-4,-3,0,0,-1,-3,1,0,-1,-7,-5},{-1,-3,1,1,-2,-2,6,-2,0,-2,-2,2,0,3,2,-1,-1,-2,-3,0},{-1,-2,-2,-2,1,-3,-2,5,-2,2,2,-2,-2,-2,-2,-1,0,4,-5,-1},{-1,-5,0,0,-5,-2,0,-2,5,-3,0,1,-1,1,3,0,0,-2,-3,-4},{-2,-6,-4,-3,2,-4,-2,2,-3,6,4,-3,-3,-2,-3,-3,-2,2,-2,-1},{-1,-5,-3,-2,0,-3,-2,2,0,4,6,-2,-2,-1,0,-2,-1,2,-4,-2},{0,-4,2,1,-3,0,2,-2,1,-3,-2,2,0,1,0,1,0,-2,-4,-2},{1,-3,-1,-1,-5,0,0,-2,-1,-3,-2,0,6,0,0,1,0,-1,-6,-5},{0,-5,2,2,-5,-1,3,-2,1,-2,-1,1,0,4,1,-1,-1,-2,-5,-4},{-2,-4,-1,-1,-4,-3,2,-2,3,-3,0,0,0,1,6,0,-1,-2,2,-4},{1,0,0,0,-3,1,-1,-1,0,-3,-2,1,1,-1,0,2,1,-1,-2,-3},{1,-2,0,0,-3,0,-1,0,0,-2,-1,0,0,-1,-1,1,3,0,-5,-3},{0,-2,-2,-2,-1,-1,-2,4,-2,2,2,-2,-1,-2,-2,-1,0,4,-6,-2},{-6,-8,-7,-7,0,-7,-3,-5,-3,-2,-4,-4,-6,-5,2,-2,-5,-6,17,0},{-3,0,-4,-4,7,-5,0,-1,-4,-1,-2,-2,-5,-4,-4,-3,-3,-2,0,10}};

    public static void main( String[] args ) {
        // DEFINE "s1" AND "s2"!!!
        String s1 = "IIDVYGMGCYIIEYEGYYQESCNPWERNPGNKLHHFMKGQIVAKIVYEWGMYFITNNPDDCSMTWCSMDFPKVIDYDDLGMMFYSAWVDLWWHHWISRQPTDLAKEKMRQPRAPQFHAIYNKRYDWQCVIRKVKWLIKMMIYMAEYYGEWIGSFLHLADSFCLDISEGILVRDYEWACMWKGVGFALLIQFWGMCLMCGLPMESKGMPCWFIASQQISEWGTKHSNHPGDCDSRKCDYWFCMSPYKIQPKMESHNHRISIMWYWHRQLQQNPMNVIERSDSLLCHDGAMKLQANNNHMEIMRTQPNCFNLVKLHGKYQTPDNGMCEVSWPTWENSHSDKEMNTNCKTPNDLYKTSSIMDNMGFLFKSGMNDRWNMGAWQKIGGSSLITWCQKRIDILMTGMIAITVMEKRQTFGIVRNQFISEGSVHHMHGVVFVRDYGNEIYDINDFAHELDVISRHGNCNCPPQLVPPDVMYGPQTAEPPVAHSCILMMCYMVKPPCSWFCTVCHPKLCCLKNGFSFWKSTDHNVTHKHDMKCYDAIDWDIWYQMNIITSYFVNSHPYEQTIQSEKYVKVEGPSKTYFTRKWEAVPNDVPMRWAMFSKCGITTSRPAPTQGCILEAFMEEAATHTFICIQSHSEIFGPMNMHTPMQLRTEHSTLDKCVPYLMSYPMVQPANGHDLCVEGESNMHYDLFYAQRFCEFLEPIDSMIPVFDMKMADNFEESICRGLNDPENGDQSPENHEYNQLLSGEVNELWGEAMEGRITYFVRKNKRYSSGSDDWYHCHEKNDLTWMNPCTNPILNGVITGVKQHKEAELRCPGGNNDAAKKTDWGDDETCVNHTIWLDFKTKALVGMHTVFTHQIVSNGCESPQWQTHMTSDWHKQDYRGFTVPEHESMHHVARRRD";
        String s2 = "CDNYRCWFKQIVVCIILMPENCIPIKNHVAGAWGSSEQSGTPQWYGHWTVRQMKVQVITSSWMGRQAPDSKLALPPPHCLWSRELEFQTHHRDNESFAKDCKGVMTTTIEHTIWECPDGIAEGLVQEKMKPANHCSKLYHYRQMFWHAGQLHSAKISIYYCMKFDSISQLPRIEIYQPFTGLMSAFIRRLSNACHGLQTLMKFGQRWNNMYEGWTCGFAFQRSGNGVLESLEVFLKFLEHYAHKVGHGLDRYNVLEWGTKNDCPCMSPYKIQPKMESHIMDITIMWYVHRQLQQNPRNMIEESDSLLCHDPAMKTQANQHYGNWHTPWPNCPNLGKLHGKYQTPDNKMCAVSWPTWENSHSDKEMPNDLYCVTMGLIVENPLFKSGMNARVNQQDIINMWIGGSTNKRWLRITWCQKRISAMGYDSETCCPAGAGRDKQFIIVRNQFISEGKRKWDHHMHGVVFVWDYGTEIYDINDFAFQVQIELDVGNCNCPPQLVYLCYPLLPHTVMHVKPSWFCTMVLVDCAQCQCRKNGFSFWKSTDCNVTHKCSDIWYQTNIITSYFVNSHPYEQTIQSEKRIEGPSKYAFNTFTWEAMFSSCGITTSRPAPTQGCILEAFMWGYHGKAGKEGCMNHAECRMNQDWSTLCLRPCSACDPYSAHMMVLYHYECFWIWECDSTHLHHMNENIMMEYKLIKPIYTEREMYHTPIQMKSMDTMEWYRMPAASCLAMTWWQLKSLDKNMENWQQISLFPERCSSHVPGIQPKWGWHPFGEVMAIDATLLQLLYFYYVCGWLQRNDEMAGKKGDTPKFMNAWDRQRGQQLTAKWWETYADDCAIDRKKRTQTSIPQKANRKIRITWNELIEQIMERPSQMVPNLSVRWYNWIGKEAFIWSQMAKPCGWPWHCAFPIWAMQPWNAVILQYDW";
        // DEFINE "s1" AND "s2"!!!
        int indel = 5;
        String[] out = proteinLocalAlignment(s1,s2,indel);
        System.out.println(out[0]);
        System.out.println(out[1]);
        System.out.println(out[2]);
    }
    
    public static String[] proteinLocalAlignment( String s, String t, int indel ) {
        // Uses PAM250. PASS indel AS POSITIVE INTEGER, NOT NEGATIVE!
        int[][] S = new int[s.length()+1][t.length()+1];
        int[][] opt = new int[s.length()+1][t.length()+1]; // 1 = right, 2 = down, 3 = diag, 4 = STOP
        S[0][0] = 0;
        opt[0][0] = 4;
        for(int i = 1; i <= s.length(); ++i) {
            S[i][0] = S[i-1][0] - indel;
            opt[i][0] = 1;
        }
        for(int j = 1; j <= t.length(); ++j) {
            S[0][j] = S[0][j-1] - indel;
            opt[0][j] = 2;
        }
        for(int i = 1; i <= s.length(); ++i) {
            for(int j = 1; j <= t.length(); ++j) {
                int opt1 = S[i][j-1] - indel; // right
                int opt2 = S[i-1][j] - indel; // down
                int opt3 = S[i-1][j-1] + pam250[aminosAlpha.indexOf(s.charAt(i-1))][aminosAlpha.indexOf(t.charAt(j-1))]; // diag
                S[i][j] = opt1;
                opt[i][j] = 1;
                if(opt2 > S[i][j]) {
                    S[i][j] = opt2;
                    opt[i][j] = 2;
                }
                if(opt3 > S[i][j]) {
                    S[i][j] = opt3;
                    opt[i][j] = 3;
                }
                if(0 > S[i][j]) {
                    S[i][j] = 0;
                    opt[i][j] = 4;
                }
            }
        }
        String[] out = new String[3];
        out[1] = "";
        out[2] = "";
        int bi = -1;
        int bj = -1;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= s.length(); ++i) {
            for(int j = 0; j <= t.length(); ++j) {
                if(S[i][j] > max) {
                    max = S[i][j];
                    bi = i;
                    bj = j;
                }
            }
        }
        out[0] = "" + S[bi][bj];
        boolean stopped = false;
        while(bi > 0 && bj > 0) {
            if(opt[bi][bj] == 1) { // right
                out[2] = t.charAt(bj-- - 1) + out[2];
            }
            else if(opt[bi][bj] == 2) { // down
                out[1] = s.charAt(bi-- - 1) + out[1];
            }
            else if(opt[bi][bj] == 3) { // diag
                out[1] = s.charAt(bi-- - 1) + out[1];
                out[2] = t.charAt(bj-- - 1) + out[2];
            }
            else if(opt[bi][bj] == 4) { // STOP
                stopped = true;
                break;
            }
        }
        if(!stopped && bi > 0) {
            out[1] = s.substring(0,bi) + out[1];
        }
        if(!stopped && bj > 0) {
            out[2] = t.substring(0,bj) + out[2];
        }
        return out;
    }
}