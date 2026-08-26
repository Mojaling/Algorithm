import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        int r;
        int c;
        int cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            int[][] map = new int[N][N];

            for (int r = 0; r < N; r++) {
                String line = br.readLine();

                for (int c = 0; c < N; c++) {
                    map[r][c] = line.charAt(c) - '0';
                }
            }

            int[][] dist = new int[N][N];

            for (int r = 0; r < N; r++) {
                Arrays.fill(dist[r], Integer.MAX_VALUE);
            }

            dist[0][0] = 0;

            PriorityQueue<Node> pq =
                    new PriorityQueue<>(
                            (a, b) -> Integer.compare(a.cost, b.cost)
                    );

            pq.offer(new Node(0, 0, 0));

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!pq.isEmpty()) {

                Node now = pq.poll();

                int r = now.r;
                int c = now.c;
                int cost = now.cost;

                if (cost > dist[r][c]) {
                    continue;
                }

                if (r == N - 1 && c == N - 1) {
                    break;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }

                    int newCost = cost + map[nr][nc];

                    if (newCost < dist[nr][nc]) {
                        dist[nr][nc] = newCost;
                        pq.offer(new Node(nr, nc, newCost));
                    }
                }
            }

            System.out.printf("#%d %d%n", tc, dist[N - 1][N - 1]);
        }
    }
}