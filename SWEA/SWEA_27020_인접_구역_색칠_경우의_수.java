import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, K;
    static boolean[][] adj;
    static int[] color;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            adj = new boolean[N + 1][N + 1];
            color = new int[N + 1];
            answer = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a][b] = true;
                adj[b][a] = true;
            }

            dfs(1);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int node) {
        if (node > N) {
            answer++;
            return;
        }

        for (int c = 1; c <= K; c++) {
            if (!canPaint(node, c)) continue;
            color[node] = c;
            dfs(node + 1);
            color[node] = 0;
        }
    }

    static boolean canPaint(int node, int c) {
        for (int prev = 1; prev < node; prev++) {
            if (adj[node][prev] && color[prev] == c) {
                return false;
            }
        }
        return true;
    }
}