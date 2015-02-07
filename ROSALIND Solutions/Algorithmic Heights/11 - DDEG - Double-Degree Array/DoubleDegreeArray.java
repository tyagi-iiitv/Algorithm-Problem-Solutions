import java.io.*;
public class DoubleDegreeArray {
  public static void main( String[] args ) {
    int lines = 0;
    String[] in = null;
    int nodes = -1;
    try {
      BufferedReader r = new BufferedReader(new FileReader(args[0]));
      String line;
      String[] parts = r.readLine().split(" ");
      in = new String[Integer.parseInt(parts[1])];
      nodes = Integer.parseInt(parts[0]);
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
        
    int[] dd = doubleDegrees(graph,nodes);
    
    for(int i = 1; i < dd.length; ++i) {
      System.out.print(dd[i] + " ");
    }
  }
  
  public static int[] doubleDegrees( int[][] graph, int max ) {
    boolean[][] g2 = new boolean[max+1][max+1];
    for(int i = 0; i < graph.length; ++i) {
      g2[graph[i][0]][graph[i][1]] = true;
      g2[graph[i][1]][graph[i][0]] = true;
    }
    int[] d = degrees(graph,max);
    int[] dd = new int[d.length];
    for(int x = 0; x < d.length; ++x) {
      int sum = 0;
      for(int i = 0; i < g2.length; ++i) {
        if(g2[x][i]) {
          sum += d[i];
        }
      }
      dd[x] = sum;
    }
    return dd;
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