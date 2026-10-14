// SWEA #3307 · 최장 증가 부분 수열
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBOKg-a6l0DFAWr
// Language: Java
// Execution Time: 120 ms
// Memory: 32716 KB


import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] dp = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;

            for (int i = 0; i < N; i++) {
                dp[i] = 1;

                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                answer = Math.max(answer, dp[i]);
            }

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
