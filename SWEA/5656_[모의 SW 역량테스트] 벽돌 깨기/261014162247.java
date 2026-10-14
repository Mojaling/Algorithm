// SWEA #5656 · [모의 SW 역량테스트] 벽돌 깨기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
// Language: Java
// Execution Time: 204 ms
// Memory: 75152 KB


import java.io.*;
import java.util.*;

public class Solution {

    static int N, W, H;
    static int answer;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];

            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;

            dfs(0, map);

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }

    // 1. 구슬을 쏠 열 선택
    static void dfs(int depth, int[][] map) {

        int remain = count(map);
        answer = Math.min(answer, remain);

        // N번 발사했거나 모든 벽돌이 없어진 경우
        if (depth == N || remain == 0) {
            return;
        }

        for (int c = 0; c < W; c++) {

            // 해당 열의 가장 위 벽돌 찾기
            int r = 0;
            while (r < H && map[r][c] == 0) {
                r++;
            }

            // 해당 열에 벽돌이 없다면 건너뛰기
            if (r == H) continue;

            // 지도 복사
            int[][] copy = new int[H][W];
            for (int i = 0; i < H; i++) {
                copy[i] = map[i].clone();
            }

            // 벽돌 폭발
            explode(copy, r, c);

            // 중력 적용
            gravity(copy);

            // 다음 구슬 발사
            dfs(depth + 1, copy);
        }
    }

    // 2. BFS로 연쇄 폭발
    static void explode(int[][] map, int r, int c) {

        Queue<int[]> q = new ArrayDeque<>();

        int power = map[r][c];
        q.offer(new int[]{r, c, power});
        map[r][c] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int cr = now[0];
            int cc = now[1];
            int range = now[2];

            for (int d = 0; d < 4; d++) {
                for (int k = 1; k < range; k++) {

                    int nr = cr + dr[d] * k;
                    int nc = cc + dc[d] * k;

                    if (nr < 0 || nr >= H ||
                        nc < 0 || nc >= W) {
                        break;
                    }

                    if (map[nr][nc] == 0) continue;

                    int nextPower = map[nr][nc];

                    // 중복 폭발 방지
                    map[nr][nc] = 0;

                    q.offer(new int[]{
                        nr, nc, nextPower
                    });
                }
            }
        }
    }

    // 3. 중력 처리
    static void gravity(int[][] map) {

        for (int c = 0; c < W; c++) {
            int bottom = H - 1;

            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    int value = map[r][c];
                    map[r][c] = 0;
                    map[bottom][c] = value;
                    bottom--;
                }
            }
        }
    }

    // 4. 남은 벽돌 세기
    static int count(int[][] map) {
        int cnt = 0;

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (map[r][c] != 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
