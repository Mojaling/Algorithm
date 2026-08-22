import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 개수
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            // 환자의 수
            int N = Integer.parseInt(br.readLine());

            int[] times = new int[N];

            // 한 줄에 주어진 N명의 진료시간을 읽는다.
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            /*
             * 핵심 1.
             *
             * 총 대기시간을 최소화하려면
             * 진료시간이 짧은 환자부터 진료해야 한다.
             *
             * 예:
             * 3 1 2
             *
             * 정렬 후
             * 1 2 3
             */
            Arrays.sort(times);

            /*
             * currentWait
             * = 현재 환자가 기다려야 하는 시간
             *
             * totalWait
             * = 모든 환자의 대기시간의 합
             *
             * long을 사용하는 이유:
             * N과 진료시간이 커지면 합계가 int보다
             * 커질 가능성을 안전하게 대비하기 위해서이다.
             */
            long currentWait = 0;
            long totalWait = 0;

            for (int i = 0; i < N; i++) {

                /*
                 * 현재 환자가 기다린 시간을
                 * 전체 대기시간에 더한다.
                 *
                 * 첫 번째 환자는 앞사람이 없으므로
                 * currentWait = 0이다.
                 */
                totalWait += currentWait;

                /*
                 * 현재 환자의 진료가 끝나면
                 * 다음 환자는 이 환자의 진료시간만큼
                 * 추가로 기다려야 한다.
                 */
                currentWait += times[i];
            }

            System.out.println("#" + tc + " " + totalWait);
        }
    }
}