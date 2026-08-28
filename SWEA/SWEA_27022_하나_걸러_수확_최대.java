import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int times = 1; times <= T; times++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] nums = new int[N];

            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            // dp[i] = 0번 ~ i번 나무까지 봤을 때 얻을 수 있는 최대 수확량
            int[] dp = new int[N];

            dp[0] = nums[0];

            if (N >= 2) {
                dp[1] = Math.max(nums[0], nums[1]);
            }

            for (int i = 2; i < N; i++) {
                int nowOn = dp[i - 2] + nums[i]; // 현재 나무 선택
                int nowOff = dp[i - 1];          // 현재 나무 선택 안 함

                dp[i] = Math.max(nowOn, nowOff);
            }

            System.out.printf("#%d %d%n", times, dp[N - 1]);
        }
    }
}