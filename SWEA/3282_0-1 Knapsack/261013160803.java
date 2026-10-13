// SWEA #3282 · 0/1 Knapsack
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJAVpqrzQDFAWr
// Language: Java
// Execution Time: 111 ms
// Memory: 31740 KB

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] v = new int[N + 1];   // v[i] : i번 물건 부피
            int[] c = new int[N + 1];   // c[i] : i번 물건 가치
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = Integer.parseInt(st.nextToken());
            }

            // dp[i][w] : 1~i번 물건만 보고, 부피 w 이하로 담을 수 있는 최대 가치
            int[][] dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                for (int w = 0; w <= K; w++) {
                    dp[i][w] = dp[i - 1][w];                                       // i번 안 넣기
                    if (v[i] <= w) {
                        dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - v[i]] + c[i]); // i번 넣기
                    }
                }
            }
            sb.append('#').append(tc).append(' ').append(dp[N][K]).append('\n');
        }
        System.out.print(sb);
    }
}