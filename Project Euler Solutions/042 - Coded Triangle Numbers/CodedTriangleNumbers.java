import java.io.*;
public class CodedTriangleNumbers {
  public static void main( String[] args ) {
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String in = r.readLine();
      String[] words = in.substring(1,in.length()-1).split("\",\"");
      System.out.println(codedTriangleNumbers(words));
    } catch(Exception e) {}
  }
  
  public static int codedTriangleNumbers( String[] words ) {
    int[] nums = new int[words.length];
    int max = 0;
    for(int i = 0; i < nums.length; ++i) {
      nums[i] = wordToNumber(words[i]);
      if(nums[i] > max) {
        max = nums[i];
      }
    }
    int maxT = 1;
    int add = 2;
    boolean[] t = new boolean[max+1];
    while(maxT < max) {
      t[maxT] = true;
      maxT += add++;
    }
    int count = 0;
    for(int num : nums) {
      if(t[num]) {
        ++count;
      }
    }
    return count;
  }
  
  public static int wordToNumber( String word ) {
    int sum = 0;
    for(int i = 0; i < word.length(); ++i) {
      sum += (word.charAt(i) - 'A' + 1);
    }
    return sum;
  }
}