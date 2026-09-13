import java.io.*;
import java.util.*;

public class Solution {

    static class BC {
        int x;
        int y;
        int c;
        int p;

        BC(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    static int M; // 이동 시간
    static int A; // BC 개수

    static int[] moveA;
    static int[] moveB;

    static BC[] bc;

    // 0: 정지, 1: 상, 2: 우, 3: 하, 4: 좌
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moveA = new int[M];
            moveB = new int[M];

            // 사용자 A 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }

            // 사용자 B 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            // BC 정보
            bc = new BC[A];

            for (int i = 0; i < A; i++) {

                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                bc[i] = new BC(x, y, c, p);
            }

            // 사용자 A 시작 위치
            int ax = 1;
            int ay = 1;

            // 사용자 B 시작 위치
            int bx = 10;
            int by = 10;

            int result = 0;

            /*
             * 0초 ~ M초
             *
             * M번 이동하지만
             * 초기 위치인 0초에도 충전 가능하므로
             * 총 M+1번 충전한다.
             */
            for (int time = 0; time <= M; time++) {

                // 0초가 아니라면 이동
                if (time > 0) {

                    int commandA = moveA[time - 1];
                    int commandB = moveB[time - 1];

                    ax += dx[commandA];
                    ay += dy[commandA];

                    bx += dx[commandB];
                    by += dy[commandB];
                }

                // 현재 위치에서 얻을 수 있는 최대 충전량
                result += getMaxCharge(ax, ay, bx, by);
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    /*
     * 현재 사용자 A, B 위치에서
     * 얻을 수 있는 최대 충전량
     */
    static int getMaxCharge(int ax, int ay, int bx, int by) {

        int max = 0;

        /*
         * -1 : 아무 BC도 사용하지 않음
         *
         * A가 선택할 BC와
         * B가 선택할 BC를 전부 조합해본다.
         */
        for (int aBC = -1; aBC < A; aBC++) {

            // A가 해당 BC에 접속할 수 없다면
            if (aBC != -1 && !canConnect(ax, ay, aBC)) {
                continue;
            }

            for (int bBC = -1; bBC < A; bBC++) {

                // B가 해당 BC에 접속할 수 없다면
                if (bBC != -1 && !canConnect(bx, by, bBC)) {
                    continue;
                }

                int sum = 0;

                // 둘 다 아무것도 사용 안 함
                if (aBC == -1 && bBC == -1) {
                    sum = 0;
                }

                // A만 사용
                else if (aBC != -1 && bBC == -1) {
                    sum = bc[aBC].p;
                }

                // B만 사용
                else if (aBC == -1 && bBC != -1) {
                    sum = bc[bBC].p;
                }

                // 둘 다 같은 BC 사용
                else if (aBC == bBC) {

                    /*
                     * 예를 들어 P=100이면
                     *
                     * A = 50
                     * B = 50
                     *
                     * 합은 100
                     */
                    sum = bc[aBC].p;
                }

                // 서로 다른 BC 사용
                else {
                    sum = bc[aBC].p + bc[bBC].p;
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    /*
     * 사용자 위치 (x, y)가
     * 해당 BC 범위 안에 들어가는지 확인
     */
    static boolean canConnect(int x, int y, int bcIndex) {

        BC charger = bc[bcIndex];

        int distance =
                Math.abs(x - charger.x)
              + Math.abs(y - charger.y);

        return distance <= charger.c;
    }
}