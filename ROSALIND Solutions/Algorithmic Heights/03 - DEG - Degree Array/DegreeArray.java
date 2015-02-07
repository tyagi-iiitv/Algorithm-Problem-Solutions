import java.io.*;
public class DegreeArray {
  public static void main( String[] args ) {
    int lines = 0;
    String[] in = null;
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String line;
      while((line = r.readLine()) != null) {
        ++lines;
      }
      in = new String[lines];
      r = new BufferedReader(new FileReader(args[0]));
      int i = 0;
      while((line = r.readLine()) != null) {
        in[i++] = line;
      }
    } catch(Exception e) {}
    
    int[][] graph = new int[in.length][];
    int max = 1;
    for(int i = 0; i < graph.length; ++i) {
      graph[i] = new int[2];
      String[] ori = in[i].split(" ");
      graph[i][0] = Integer.parseInt(ori[0]);
      graph[i][1] = Integer.parseInt(ori[1]);
      if(graph[i][0] > max) {
        max = graph[i][0];
      }
      if(graph[i][1] > max) {
        max = graph[i][1];
      }
    }
    
    int[] d = degrees(graph,max);
    for(int i = 1; i < d.length; ++i) {
      System.out.print(d[i] + " ");
    }
  }
  
  public static int[] degrees( int[][] graph, int max ) {
    int[] d = new int[max+1];
    for(int i = 0; i < graph.length; ++i) {
      ++d[graph[i][0]];
      ++d[graph[i][1]];
    }
    return d;
  }
}