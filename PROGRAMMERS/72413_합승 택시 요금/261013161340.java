// PROGRAMMERS #72413 · 합승 택시 요금
// https://school.programmers.co.kr/learn/courses/30/lessons/72413
// Language: Java

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = 100_000_000;            // 실제 최대 경로(199 * 100,000)보다 충분히 크고, 3번 더해도 int 범위 안

        // d[i][j] : i -> j 최소 요금
        int[][] d = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                d[i][j] = (i == j) ? 0 : INF;
            }
        }
        for (int[] f : fares) {                 // 양방향
            d[f[0]][f[1]] = f[2];
            d[f[1]][f[0]] = f[2];
        }

        // 플로이드-워셜 : k를 거쳐 가는 게 더 싸면 갱신
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (d[i][k] + d[k][j] < d[i][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }

        // k번 지점까지 같이 타고, 거기서 헤어진다 (k == s 이면 합승 안 함)
        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer, d[s][k] + d[k][a] + d[k][b]);
        }
        return answer;
    }
}