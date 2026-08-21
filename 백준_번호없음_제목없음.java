import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            // N : 상자 개수
            // M : 작업자 수
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Integer[] boxes = new Integer[N];
            Integer[] workers = new Integer[M];

            // 상자의 무게 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                boxes[i] = Integer.parseInt(st.nextToken());
            }

            // 작업자가 들 수 있는 최대 무게 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                workers[i] = Integer.parseInt(st.nextToken());
            }

            // 상자 : 무거운 순서대로 정렬
            Arrays.sort(boxes, Collections.reverseOrder());

            // 작업자 : 힘이 센 순서대로 정렬
            Arrays.sort(workers, Collections.reverseOrder());

            int boxIndex = 0;       // 현재 확인하고 있는 상자
            int workerIndex = 0;    // 현재 확인하고 있는 작업자

            int answer = 0;

            // 상자나 작업자 중 하나를 모두 확인할 때까지 반복
            while (boxIndex < N && workerIndex < M) {

                /*
                 * 현재 작업자가 현재 상자를 들 수 있는 경우
                 *
                 * 가장 무거운 상자부터 보고 있기 때문에
                 * 이 상자를 현재 작업자에게 배정하는 것이 유리하다.
                 */
                if (workers[workerIndex] >= boxes[boxIndex]) {

                    answer += boxes[boxIndex];

                    // 상자를 사용했으므로 다음 상자로
                    boxIndex++;

                    // 작업자도 한 번만 옮길 수 있으므로 다음 작업자로
                    workerIndex++;

                } else {

                    /*
                     * 현재 가장 힘센 작업자조차
                     * 이 상자를 들 수 없는 경우
                     *
                     * 뒤의 작업자들은 현재 작업자보다 힘이 약하므로
                     * 이 상자는 아무도 들 수 없다.
                     *
                     * 따라서 상자만 건너뛴다.
                     */
                    boxIndex++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}