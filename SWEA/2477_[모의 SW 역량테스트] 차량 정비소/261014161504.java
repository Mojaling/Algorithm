// SWEA #2477 · [모의 SW 역량테스트] 차량 정비소
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV6c6bgaIuoDFAXy
// Language: Java
// Execution Time: 141 ms
// Memory: 27648 KB


import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] a = new int[N];
            int[] b = new int[M];
            int[] arrival = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                arrival[i] = Integer.parseInt(st.nextToken());
            }

            // 각 창구의 현재 고객번호 (0이면 빈 창구)
            int[] receptionCustomer = new int[N];
            int[] repairCustomer = new int[M];

            // 각 창구의 업무 종료 시간
            int[] receptionEnd = new int[N];
            int[] repairEnd = new int[M];

            // 각 고객이 이용한 접수 창구번호
            int[] usedReception = new int[K + 1];

            Queue<Integer> receptionQ = new ArrayDeque<>();
            Queue<Integer> repairQ = new ArrayDeque<>();

            int time = 0;
            int nextCustomer = 1;
            int completed = 0;
            int answer = 0;

            while (completed < K) {

                // 1. 정비 완료 처리
                for (int j = 0; j < M; j++) {
                    if (repairCustomer[j] != 0
                            && repairEnd[j] == time) {

                        int customer = repairCustomer[j];

                        if (usedReception[customer] == A
                                && j + 1 == B) {
                            answer += customer;
                        }

                        repairCustomer[j] = 0;
                        completed++;
                    }
                }

                // 2. 접수 완료 처리
                // 창구번호가 작은 순서로 확인
                for (int i = 0; i < N; i++) {
                    if (receptionCustomer[i] != 0
                            && receptionEnd[i] == time) {

                        repairQ.offer(receptionCustomer[i]);
                        receptionCustomer[i] = 0;
                    }
                }

                // 3. 새로 도착한 고객
                while (nextCustomer <= K
                        && arrival[nextCustomer] <= time) {
                    receptionQ.offer(nextCustomer);
                    nextCustomer++;
                }

                // 4. 접수 창구 배정
                for (int i = 0; i < N; i++) {
                    if (receptionCustomer[i] == 0
                            && !receptionQ.isEmpty()) {

                        int customer = receptionQ.poll();

                        receptionCustomer[i] = customer;
                        receptionEnd[i] = time + a[i];

                        usedReception[customer] = i + 1;
                    }
                }

                // 5. 정비 창구 배정
                for (int j = 0; j < M; j++) {
                    if (repairCustomer[j] == 0
                            && !repairQ.isEmpty()) {

                        int customer = repairQ.poll();

                        repairCustomer[j] = customer;
                        repairEnd[j] = time + b[j];
                    }
                }

                time++;
            }

            if (answer == 0) answer = -1;

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
