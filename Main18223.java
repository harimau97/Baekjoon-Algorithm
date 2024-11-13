import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  static class Edge implements Comparable<Edge> {
    int D, W;

    public Edge(int d, int w) {
      super();
      D = d;
      W = w;
    }

    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.W, o.W);
    }


  }

  static int V, E, P;
  static String answer;
  static List<Edge>[] adjlist;
  static List<Integer>[] parent;
  static List<Integer> path;
  static int[] dist;
  static boolean[] visited;


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    V = sc.nextInt();
    E = sc.nextInt();
    P = sc.nextInt() - 1;
    answer = "";
    adjlist = new ArrayList[V];
    parent = new ArrayList[V];
    path = new ArrayList<>();
    dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    visited = new boolean[V];

    for (int i = 0; i < V; i++) {
      adjlist[i] = new ArrayList<>();
      parent[i] = new ArrayList<>();
    }

    for (int i = 0; i < E; i++) {
      int A = sc.nextInt() - 1;
      int D = sc.nextInt() - 1;
      int W = sc.nextInt();

      adjlist[A].add(new Edge(D, W));
      adjlist[D].add(new Edge(A, W));
    }

    int a = dijk(0, P);
    int b = dijk(P, V - 1);
    int c = dijk(0, V - 1);

    if (a + b <= c) {
      answer = "SAVE HIM";
    } else {
      answer = "GOOD BYE";
    }



    System.out.println(answer);
  }


  private static int dijk(int start, int dest) {
    Arrays.fill(dist, Integer.MAX_VALUE);
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.add(new Edge(start, 0));
    dist[start] = 0;

    while (!pq.isEmpty()) {
      Edge e = pq.poll();

      for (Edge ee : adjlist[e.D]) {
        if (dist[e.D] + ee.W < dist[ee.D]) {
          parent[ee.D].add(e.D);
          dist[ee.D] = dist[e.D] + ee.W;
          pq.add(new Edge(ee.D, dist[ee.D]));
        }
      }
    }
    return dist[dest];
  }

}
