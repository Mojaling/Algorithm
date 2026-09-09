import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] map;

    static List<int[]> cores;

    static int maxCore;
    static int minWire;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            cores = new ArrayList<>();

            for(int r = 0; r < N; r++) {

                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                for(int c = 0; c < N; c++) {

                    map[r][c] = Integer.parseInt(st.nextToken());

                    if(map[r][c] == 1) {

                        // 가장자리 Core는 이미 연결된 상태이므로 제외
                        if(r != 0 && r != N - 1 &&
                           c != 0 && c != N - 1) {

                            cores.add(new int[]{r, c});
                        }
                    }
                }
            }

            maxCore = 0;
            minWire = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWire);
        }
    }


    static void dfs(int index, int connected, int wireLength) {

        // 모든 Core를 확인함
        if(index == cores.size()) {

            if(connected > maxCore) {
                maxCore = connected;
                minWire = wireLength;
            }

            else if(connected == maxCore) {
                minWire = Math.min(minWire, wireLength);
            }

            return;
        }


        int r = cores.get(index)[0];
        int c = cores.get(index)[1];


        // 4방향 시도
        for(int d = 0; d < 4; d++) {

            if(canConnect(r, c, d)) {

                int length = setWire(r, c, d, 2);

                dfs(
                    index + 1,
                    connected + 1,
                    wireLength + length
                );

                setWire(r, c, d, 0);
            }
        }


        // 현재 Core를 연결하지 않는 경우
        dfs(index + 1, connected, wireLength);
    }


    // 이 방향으로 가장자리까지 갈 수 있는가?
    static boolean canConnect(int r, int c, int d) {

        int nr = r + dr[d];
        int nc = c + dc[d];

        while(nr >= 0 && nr < N &&
              nc >= 0 && nc < N) {

            if(map[nr][nc] != 0) {
                return false;
            }

            nr += dr[d];
            nc += dc[d];
        }

        return true;
    }


    // 전선을 설치하거나 제거
    // value = 2 -> 설치
    // value = 0 -> 제거
    static int setWire(int r, int c, int d, int value) {

        int nr = r + dr[d];
        int nc = c + dc[d];

        int length = 0;

        while(nr >= 0 && nr < N &&
              nc >= 0 && nc < N) {

            map[nr][nc] = value;

            length++;

            nr += dr[d];
            nc += dc[d];
        }

        return length;
    }
}