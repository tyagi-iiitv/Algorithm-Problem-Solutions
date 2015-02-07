/**
 *  CONVOLUTION CYCLOPEPTIDE SEQUENCING PROBLEM
 *
 *  Implement ConvolutionCyclopeptideSequencing
 *
 *  Given:  An integer M, an integer N, and a collection of (possibly repeated) integers Spectrum.
 *  Return: A cyclic peptide LeaderPeptide resulting from running ConvolutionCyclopeptideSequencing on Spectrum, with an alphabet size of M (and ties) and a leaderboard size of N (and ties).
 */

import java.util.*;
public class ConvolutionCyclopeptideSequencingProblem {
    public static void main( String[] args ) {
        // DEFINE "m", "n", AND "spectrum"!!!
        int m = 0;
        int n = 0;
        int[] spectrum = {};
        // DEFINE "m", "n", AND "spectrum"!!!
        System.out.println(convolutionCyclopeptideSequencing(m,n,spectrum));
    }
    
    public static String convolutionCyclopeptideSequencing( int m, int n, int[] spectrum ) {
        HashMap<Character,Integer> aaToMass = new HashMap<>();
        aaToMass.put('G',57);
        aaToMass.put('A',71);
        aaToMass.put('S',87);
        aaToMass.put('P',97);
        aaToMass.put('V',99);
        aaToMass.put('T',101);
        aaToMass.put('C',103);
        aaToMass.put('I',113);
        aaToMass.put('N',114);
        aaToMass.put('D',115);
        aaToMass.put('K',128);
        aaToMass.put('E',129);
        aaToMass.put('M',131);
        aaToMass.put('H',137);
        aaToMass.put('F',147);
        aaToMass.put('R',156);
        aaToMass.put('Y',163);
        aaToMass.put('W',186);
        HashMap<Integer,Character> massToAA = new HashMap<>();
        massToAA.put(57, 'G');
        massToAA.put(71, 'A');
        massToAA.put(87, 'S');
        massToAA.put(97, 'P');
        massToAA.put(99, 'V');
        massToAA.put(101,'T');
        massToAA.put(103,'C');
        massToAA.put(114,'N');
        massToAA.put(115,'D');
        massToAA.put(129,'E');
        massToAA.put(131,'M');
        massToAA.put(137,'H');
        massToAA.put(147,'F');
        massToAA.put(156,'R');
        massToAA.put(163,'Y');
        massToAA.put(186,'W');
        
        int[] convTemp = spectralConvolution(spectrum);
        int num = 0;
        for(int i = 0; i < convTemp.length; ++i) {
            if(convTemp[i] >= 57 && convTemp[i] <= 200) {
                ++num;
            }
        }
        int[] conv = new int[num];
        int add = 0;
        for(int i = 0; i < convTemp.length; ++i) {
            if(convTemp[i] >= 57 && convTemp[i] <= 200) {
                conv[add++] = convTemp[i];
            }
        }
        HashMap<Integer,Integer> counts = new HashMap<>();
        for(int i = 0; i < conv.length; ++i) {
            if(!counts.containsKey(conv[i])) {
                counts.put(conv[i],1);
            }
            else {
                int curr = counts.get(conv[i]);
                counts.put(conv[i],curr+1);
            }
        }
        Integer[] vals = counts.values().toArray(new Integer[0]);
        int mthBestCount = vals[vals.length-m];
        ArrayList<Integer> mthBestMasses = new ArrayList<>();
        for(int i = 0; i < conv.length; ++i) {
            if(counts.get(conv[i]) >= mthBestCount) {
                mthBestMasses.add(conv[i]);
            }
        }
        ArrayList<Character> tempAlpha = new ArrayList<>();
        for(int i = 0; i < mthBestMasses.size(); ++i) {
            int curr = mthBestMasses.get(i);
            if(aaToMass.containsValue(curr)) {
                if(curr == 113) {
                    tempAlpha.add('I');
                    tempAlpha.add('L');
                }
                else if(curr == 128) {
                    tempAlpha.add('K');
                    tempAlpha.add('Q');
                }
                else {
                    tempAlpha.add(massToAA.get(curr));
                }
            }
        }
        char[] aminos = new char[tempAlpha.size()];
        for(int i = 0; i < aminos.length; ++i) {
            aminos[i] = tempAlpha.get(i);
        }
        return leaderboardCyclopeptideSequencing(aminos,n,spectrum);
    }
    
    public static int[] spectralConvolution( int[] spectrum ) {
        int n = spectrum.length;
        int[] out = new int[(n*(n-1))/2];
        int add = 0;
        for(int i = 0; i < spectrum.length; ++i) {
            for(int j = 0; j < spectrum.length; ++j) {
                if(spectrum[j] > spectrum[i]) {
                    out[add++] = spectrum[j] - spectrum[i];
                }
            }
        }
        int[] outTrim = Arrays.copyOf(out,add);
        Arrays.sort(outTrim);
        return outTrim;
    }
    
    public static String leaderboardCyclopeptideSequencing( char[] aminos, int n, int[] spectrum ) {
        String leaderPeptide = "";
        int leaderScore = 0;
        ArrayList<String> leaderboard = new ArrayList<>();
        leaderboard.add(leaderPeptide);
        while(!leaderboard.isEmpty()) {
            leaderboard = expandPeps(aminos,leaderboard);
            for(int i = 0; i < leaderboard.size(); ++i) {
                String peptide = leaderboard.get(i);
                int currMass = peptideToMass(peptide);
                if(currMass == parentMass(spectrum)) {
                    int currScore = score(peptide,spectrum);
                    if(currScore > leaderScore) {
                        leaderPeptide = peptide;
                        leaderScore = currScore;
                    }
                }
                else if(currMass > parentMass(spectrum)) {
                    leaderboard.remove(i--);
                }
            }
            leaderboard = cut(leaderboard,spectrum,n);
        }
        ArrayList<Integer> pep = peptideToIntegers(leaderPeptide);
        String out = "" + pep.get(0);
        for(int i = 1; i < pep.size(); ++i) {
            out += ("-" + pep.get(i));
        }
        return out;
    }
    
    public static ArrayList<String> cut( ArrayList<String> leaderboard, int[] spectrum, int n ) {
        int[] scores = new int[leaderboard.size()];
        for(int i = 0; i < scores.length; ++i) {
            scores[i] = score(leaderboard.get(i),spectrum);
        }
        Arrays.sort(scores);
        int nthHighestScore = -1;
        if(scores.length == 0) {
            nthHighestScore = 0;
        }
        else if(scores.length >= n) {
            nthHighestScore = scores[scores.length-n];
        }
        else {
            nthHighestScore = scores[0];
        }
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < leaderboard.size(); ++i) {
            String curr = leaderboard.get(i);
            if(score(curr,spectrum) >= nthHighestScore) {
                out.add(curr);
            }
        }
        return out;
    }
    
    public static int score( String peptide, int[] spectrum ) {
        int[] cyclo = cyclospectrum(peptide);
        int sum = 0;
        for(int i = 0; i < cyclo.length; ++i) {
            int val = cyclo[i];
            boolean contains = false;
            for(int j = 0; j < spectrum.length; ++j) {
                if(spectrum[j] == val) {
                    contains = true;
                    break;
                }
            }
            if(contains) {
                ++sum;
            }
        }
        return sum;
    }
    
    public static int[] cyclospectrum( String peptide ) {
        if(peptide.length() == 1) {
            int[] out = new int[1];
            out[0] = aaToMass(peptide.charAt(0));
            return out;
        }
        int[] answers = new int[peptide.length() * (peptide.length() - 1) + 2];
        answers[1] = peptideToMass(peptide);
        int index = 2;
        
        for(int i = 0; i < peptide.length(); ++i) {
            answers[index++] = peptideToMass(""+peptide.charAt(i));
        }
        for(int length = 2; length < peptide.length(); ++length) {
            for(int i = 0; i < peptide.length(); ++i) {
                String test = "";
                if(i > peptide.length() - length) {
                    test += peptide.substring(i,peptide.length());
                    test += peptide.substring(0,(i+length)%peptide.length());
                }
                else {
                    test = peptide.substring(i,i+length);
                }
                answers[index++] = peptideToMass(test);
            }
        }
        Arrays.sort(answers);
        return answers;
    }
    
    public static ArrayList<String> expandPeps( char[] aminos, ArrayList<String> peptides ) {
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i < peptides.size(); ++i) {
            String curr = peptides.get(i);
            for(int j = 0; j < aminos.length; ++j) {
                out.add(curr + aminos[j]);
            }
        }
        return out;
    }
    
    public static ArrayList<Integer> peptideToIntegers( String pep ) {
        ArrayList<Integer> out = new ArrayList<>();
        for(int i = 0; i < pep.length(); ++i) {
            out.add(aaToMass(pep.charAt(i)));
        }
        return out;
    }
    
    public static int parentMass( int[] spectrum ) {
        return spectrum[spectrum.length-1];
    }
    
    public static int peptideToMass( String p ) {
        int mass = 0;
        for(int i = 0; i < p.length(); ++i) {
            mass += aaToMass(p.charAt(i));
        }
        return mass;
    }
    
    public static int aaToMass( char c ) {
        switch(c) {
            case 'G': return 57;
            case 'A': return 71;
            case 'S': return 87;
            case 'P': return 97;
            case 'V': return 99;
            case 'T': return 101;
            case 'C': return 103;
            case 'I': return 113;
            case 'L': return 113;
            case 'N': return 114;
            case 'D': return 115;
            case 'K': return 128;
            case 'Q': return 128;
            case 'E': return 129;
            case 'M': return 131;
            case 'H': return 137;
            case 'F': return 147;
            case 'R': return 156;
            case 'Y': return 163;
            case 'W': return 186;
            default:  return -1;
        }
    }
}