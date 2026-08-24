import java.util.Scanner;

public class Solution {
    static int N;
    static int[][] S;
    static boolean[] pick;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();

            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    S[i][j] = sc.nextInt();
                }
            }

            pick = new boolean[N];
            answer = Integer.MAX_VALUE;   // 테스트케이스마다 초기화 필수!

            pick[0] = true;
            dfs(1, 1);

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void dfs(int idx, int cnt) {
        if (cnt == N / 2) {
            answer = Math.min(answer, diff());
            return;
        }
        if (idx == N) return;

        pick[idx] = true;
        dfs(idx + 1, cnt + 1);

        pick[idx] = false;
        dfs(idx + 1, cnt);
    }

    static int diff() {
        int a = 0, b = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (pick[i] && pick[j])        a += S[i][j] + S[j][i];
                else if (!pick[i] && !pick[j]) b += S[i][j] + S[j][i];
            }
        }
        return Math.abs(a - b);
    }
}