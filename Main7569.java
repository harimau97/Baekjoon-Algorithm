import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main7569 {

  static int M, N, H, cnt;
  static int[][][] box;
  static boolean[][][] visited;
  static List<int[]> yum;
  static int answer;
  static int[] dx = {1, -1, 0, 0, 0, 0};
  static int[] dy = {0, 0, -1, 1, 0, 0};
  static int[] dz = {1, -1};


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    M = sc.nextInt();
    N = sc.nextInt();
    H = sc.nextInt();
    answer = 0;
    yum = new ArrayList<>();

    box = new int[N][M][H];
    visited = new boolean[N][M][H];

    // 토마토 정보 받아오기

    for (int j2 = 0; j2 < H; j2++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          box[i][j][j2] = sc.nextInt();
        }

      }

    }
    // 검수시작
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        for (int j2 = 0; j2 < H; j2++) {
          if (box[i][j][j2] == 1) {
            yum.add(new int[] {i, j, j2});
          }
        }
      }
    }
    bfs();
    checkBox();
    System.out.println(answer);
  }

  private static void checkBox() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        for (int j2 = 0; j2 < H; j2++) {
          if (box[i][j][j2] == 0) {
            answer = -1;
          }
        }
        if (answer == -1)
          break;
      }
    }
  }

  private static void bfs() {
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < yum.size(); i++) {
      visited[yum.get(i)[0]][yum.get(i)[1]][yum.get(i)[2]] = true;
      q.add(new int[] {yum.get(i)[0], yum.get(i)[1], yum.get(i)[2], 0});
    }

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0];
      int y = cur[1];
      int z = cur[2];
      int s = cur[3];

      for (int i = 0; i < 6; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx == x && ny == y) {
          for (int j = 0; j < 2; j++) {
            int nz = z + dz[j];
            if (nx >= 0 && ny >= 0 && ny < M && nx < N && nz >= 0 && nz < H) {
              if (box[nx][ny][nz] == 0 && !visited[nx][ny][nz]) {
                visited[nx][ny][nz] = true;
                box[nx][ny][nz] = s + 1;
                q.add(new int[] {nx, ny, nz, s + 1});
                answer = Math.max(answer, s + 1);
              }
            }
          }
        } else {
          if (nx >= 0 && ny >= 0 && ny < M && nx < N) {
            if (box[nx][ny][z] == 0 && !visited[nx][ny][z]) {
              visited[nx][ny][z] = true;
              box[nx][ny][z] = s + 1;
              q.add(new int[] {nx, ny, z, s + 1});
              answer = Math.max(answer, s + 1);
            }
          }
        }
      }
    }
  }
}
