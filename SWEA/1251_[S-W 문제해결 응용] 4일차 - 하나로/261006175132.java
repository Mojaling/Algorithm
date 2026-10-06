// SWEA #1251 · [S/W 문제해결 응용] 4일차 - 하나로
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
// Language: Java
// Execution Time: 129 ms
// Memory: 30248 KB

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            int[] x = new int[N];
            int[] y = new int[N];

            // X좌표를 먼저 N개 입력받는다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            // Y좌표를 N개 입력받는다. 섬 i의 좌표는 (x[i], y[i])이다.
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Integer.parseInt(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine().trim());

            boolean[] visited = new boolean[N];
            long[] minEdge = new long[N];
            Arrays.fill(minEdge, Long.MAX_VALUE);

            // 섬 0에서 시작한다. 시작 정점에는 연결 비용이 들지 않는다.
            minEdge[0] = 0;
            long result = 0;

            // 한 번 반복할 때마다 섬 하나를 트리에 추가한다.
            for (int c = 0; c < N; c++) {
                // 1단계: 트리 밖에서 가장 저렴하게 연결할 수 있는 섬 선택
                long min = Long.MAX_VALUE;
                int minVertex = -1;

                for (int i = 0; i < N; i++) {
                    if (!visited[i] && minEdge[i] < min) {
                        min = minEdge[i];
                        minVertex = i;
                    }
                }

                // 모든 섬 사이에 터널을 만들 수 있으므로 정점 선택은 항상 가능하다.
                visited[minVertex] = true;
                result += min;

                // 2단계: 새로 들어온 섬을 이용해 외부 섬들의 연결 비용 갱신
                for (int i = 0; i < N; i++) {
                    if (visited[i]) continue;

                    long dx = (long) x[minVertex] - x[i];
                    long dy = (long) y[minVertex] - y[i];
                    long weight = dx * dx + dy * dy;

                    if (weight < minEdge[i]) {
                        minEdge[i] = weight;
                    }
                }
            }

            // 선택한 간선들의 길이 제곱 합에 세율을 곱하고 마지막에 반올림한다.
            long answer = Math.round(result * E);
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}