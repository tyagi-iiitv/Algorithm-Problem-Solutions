/**
 *  LONGEST REPEAT PROBLEM
 *
 *  Find the longest repeat in a string.
 *
 *  Given:  A string Text.
 *  Return: A longest repeat in Text, i.e., a longest substring of Text that appears in Text more than once.
 */

import java.util.*;
public class LongestRepeatProblem {
    public static void main( String[] args ) {
        // DEFINE "text"!!!
        String text = "";
        // DEFINE "text"!!!
        System.out.println(longestRepeat(text));
    }
    
    public static String longestRepeat( String text ) {
        String[] suffixes = new String[text.length()];
        for(int i = 0; i < text.length(); ++i) {
            suffixes[i] = text.substring(i,text.length());
        }
        Arrays.sort(suffixes);
        ArrayList<String> options = new ArrayList<>();
        for(int i = 0; i < suffixes.length-1; ++i) {
            options.add(longestSharedPrefix(suffixes[i],suffixes[i+1]));
        }
        String best = "";
        for(int i = 0; i < options.size(); ++i) {
            String curr = options.get(i);
            if(curr.length() > best.length()) {
                best = curr;
            }
        }
        return best;
    }
    
    public static String longestSharedPrefix( String s, String t ) {
        String out = "";
        for(int i = 0; i < s.length() && i < t.length(); ++i) {
            if(s.charAt(i) == t.charAt(i)) {
                out += s.charAt(i);
            }
            else {
                break;
            }
        }
        return out;
    }
}