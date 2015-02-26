/**
 *  LONGEST SHARED SUBSTRING PROBLEM
 *
 *  Find the longest substring shared by two strings.
 *
 *  Given:  Strings Text1 and Text2.
 *  Return: The longest substring that occurs in both Text1 and Text2.
 */

import java.util.*;
public class LongestSharedSubstringProblem {
    public static void main( String[] args ) {
        // DEFINE "text1" AND "text2"!!!
        String text1 = "";
        String text2 = "";
        // DEFINE "text1" AND "text2"!!!
        System.out.println(longestSharedSubstring(text1,text2));
    }
    
    public static String longestSharedSubstring( String s, String t ) {
        String cat = s+'#'+t;
        SharedSubstringSuffix[] suffixes = new SharedSubstringSuffix[cat.length()];
        for(int i = 0; i < cat.length(); ++i) {
            if(i < s.length()) {
                suffixes[i] = new SharedSubstringSuffix(cat.substring(i,cat.length()),0);
            }
            else {
                suffixes[i] = new SharedSubstringSuffix(cat.substring(i,cat.length()),1);
            }
        }
        Arrays.sort(suffixes);
        ArrayList<String> options = new ArrayList<>();
        for(int i = 0; i < suffixes.length-1; ++i) {
            if(suffixes[i].source != suffixes[i+1].source) {
                options.add(longestSharedPrefix(suffixes[i].string,suffixes[i+1].string));
            }
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

class SharedSubstringSuffix implements Comparable {
    public String string;
    public int source;
    
    public SharedSubstringSuffix( String str, int s ) {
        string = str;
        source = s;
    }
    
    public int compareTo( Object other ) {
        return string.compareTo(((SharedSubstringSuffix)other).string);
    }
}