import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution10966 {
  static int T, N, M;
  static List<int[]> water;
  static long answer;
  static char[][] map;
  static int[][] dist;
  static char[] arr;
  static boolean[][] visited;
  static int[] dr = {1, -1, 0, 0};
  static int[] dc = {0, 0, 1, -1};
  static StringTokenizer st;
  static StringBuilder sb;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {

      st = new StringTokenizer(br.readLine());
      sb = new StringBuilder();

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      water = new ArrayList<>();
      answer = 0L;
      map = new char[N][M];
      visited = new boolean[N][M];
      dist = new int[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          dist[i][j] = Integer.MAX_VALUE;
        }
      }
      // 물과 땅 위치 읽기
      for (int i = 0; i < N; i++) {
        arr = br.readLine().toCharArray();
        for (int j = 0; j < M; j++) {
          map[i][j] = arr[j];
        }
      }
      // 다중 bfs를 위해 W인 지점 기록
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (map[i][j] == 'W') {
            water.add(new int[] {i, j, 0});
          }
        }
      }
      bfs(water);

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (dist[i][j] != Integer.MAX_VALUE) {
            answer += dist[i][j];
          }
        }
      }

      sb.append("#" + t + " " + answer);
      System.out.println(sb);

    }
  }

  private static void bfs(List<int[]> pool) {
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < water.size(); i++) {
      q.add(water.get(i));
      visited[water.get(i)[0]][water.get(i)[1]] = true;
    }

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int r = cur[0];
      int c = cur[1];
      int cnt = cur[2];

      for (int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];

        if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
          if (!visited[nr][nc] && map[nr][nc] == 'L') {
            visited[nr][nc] = true;
            dist[nr][nc] = Math.min(dist[nr][nc], cnt + 1);
            q.add(new int[] {nr, nc, cnt + 1});
          }
        }


      }
    }

  }

}
