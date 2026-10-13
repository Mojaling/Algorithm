// SWEA #2383 · [모의 SW 역량테스트] 점심 식사시간
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
// Language: Java
// Execution Time: 101 ms
// Memory: 28416 KB

import java.io.*;
import java.util.*;

public class Solution {
    static int P, M, ans;
    static List<int[]> people;   // {r, c}
    static List<int[]> stairs;   // {r, c, K}
    static int[][] dist;         // dist[p][s] : p번 사람 -> s번 계단 거리
    static int[] choice;         // choice[p] : p번 사람이 가는 계단 번호
    
    /*
     * 1."내가 정해야 하는 것"과 "정해지면 자동으로 나오는 것"을 나누기
     * ->누가 어느 계단으로 가느냐.
     * 
     * 2. 답에 영향을 주는 정보만 남기기.
     * -> 이 정보가 답을 바꾸나?
     * 
     * 3.제약 조건을 보고 풀이 방법 정하기
     * 
     * 4. 고르는 방식을 템플릿에 맞추기(여기서는 중복순열)
     * 
     * 5.뼈대부터쓰고 하나씩 검증하기.
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            people = new ArrayList<>();
            stairs = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (v == 1) people.add(new int[]{r, c});
                    else if (v > 1) stairs.add(new int[]{r, c, v});
                }
            }

            P = people.size();
            M = stairs.size();

            // 거리는 배정과 상관없으니 미리 계산
            dist = new int[P][M];
            for (int p = 0; p < P; p++) {
                for (int s = 0; s < M; s++) {
                    dist[p][s] = Math.abs(people.get(p)[0] - stairs.get(s)[0])
                               + Math.abs(people.get(p)[1] - stairs.get(s)[1]);
                }
            }

            choice = new int[P];
            ans = Integer.MAX_VALUE;
            dfs(0);
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int depth) {
        if (depth == P) {                       // 모든 사람 배정 완료
            int time = 0;
            for (int s = 0; s < M; s++) {
                time = Math.max(time, calc(s)); // 가장 늦게 끝나는 계단이 완료 시간
            }
            ans = Math.min(ans, time);
            return;
        }
        for (int s = 0; s < M; s++) {
            choice[depth] = s;                  // 덮어쓰기라 되돌리기 필요 없음
            dfs(depth + 1);
        }
    }

    // s번 계단에 배정된 사람들이 모두 내려가는 시각
    static int calc(int s) {
        int[] arrive = new int[P];
        int cnt = 0;
        for (int p = 0; p < P; p++) {
            if (choice[p] == s) arrive[cnt++] = dist[p][s];
        }
        Arrays.sort(arrive, 0, cnt);            // 먼저 온 사람이 먼저 들어감

        int K = stairs.get(s)[2];
        int[] slot = new int[3];                // slot[j] : j번 자리가 비는 시각
        int last = 0;
        for (int i = 0; i < cnt; i++) {
            int j = 0;                          // 가장 빨리 비는 자리
            for (int k = 1; k < 3; k++) if (slot[k] < slot[j]) j = k;

            int start = Math.max(arrive[i] + 1, slot[j]); // 도착 1분 후 & 자리 날 때까지
            slot[j] = start + K;
            last = Math.max(last, slot[j]);
        }
        return last;
    }
}