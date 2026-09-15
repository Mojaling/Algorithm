import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static char[][] map;
    static int[][] mineCount;
    static boolean[][] visited;

    // 8방향
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            map = new char[N][N];
            mineCount = new int[N][N];
            visited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                String line = br.readLine();

                for (int c = 0; c < N; c++) {
                    map[r][c] = line.charAt(c);
                }
            }

            // 1. 각 칸 주변의 지뢰 개수 계산
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '*') {
                        continue;
                    }

                    int count = 0;

                    for (int d = 0; d < 8; d++) {

                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= N ||
                            nc < 0 || nc >= N) {
                            continue;
                        }

                        if (map[nr][nc] == '*') {
                            count++;
                        }
                    }

                    mineCount[r][c] = count;
                }
            }

            int result = 0;

            // 2. 0인 칸부터 BFS
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '.' &&
                        mineCount[r][c] == 0 &&
                        !visited[r][c]) {

                        result++;

                        bfs(r, c);
                    }
                }
            }

            // 3. 아직 안 열린 칸 직접 클릭
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '.' &&
                        !visited[r][c]) {

                        result++;
                    }
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    static void bfs(int startR, int startC) {

        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] {startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for (int d = 0; d < 8; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N ||
                    nc < 0 || nc >= N) {
                    continue;
                }

                if (map[nr][nc] == '*') {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                // 주변 칸은 숫자가 표시되므로 방문 처리
                visited[nr][nc] = true;

                // 단, 0인 칸에서만 계속 퍼진다.
                if (mineCount[nr][nc] == 0) {
                    queue.add(new int[] {nr, nc});
                }
            }
        }
    }
}