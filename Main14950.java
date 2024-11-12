import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main14950 {
  static class Edge implements Comparable<Edge> {
    int A, B, W;

    public Edge(int a, int b, int w) {
      super();
      A = a;
      B = b;
      W = w;
    }

    @Override
    public int compareTo(Main14950.Edge o) {
      return Integer.compare(this.W, o.W);
    }


  }

  static int N, M, cost;
  static long answer;
  static List<Edge>[] adjlist;
  static boolean[] visited;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    cost = sc.nextInt();
    answer = 0L;
    adjlist = new ArrayList[N];
    visited = new boolean[N];

    for (int i = 0; i < N; i++) {
      adjlist[i] = new ArrayList<>();
    }


    for (int i = 0; i < M; i++) {
      int A = sc.nextInt() - 1;
      int B = sc.nextInt() - 1;
      int W = sc.nextInt();

      adjlist[A].add(new Edge(A, B, W));
      adjlist[B].add(new Edge(B, A, W));

    }

    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.addAll(adjlist[0]);
    visited[0] = true;

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      if (visited[e.B])
        continue;
      visited[e.B] = true;
      answer += e.W;

      pq.addAll(adjlist[e.B]);
    }

    for (int i = 0; i < N - 1; i++) {
      answer += (i * cost);
    }

    System.out.println(answer);

  }



}
