package algo_workshop;

import java.util.*;
import java.io.*;
/*
 * 상급자 ->1, 하급->최대 2명
 * 임의의 직원 N을 책임자로하는 하위조직에 몇명?(N본인포함)
 * 
 */

public class Solution {

    /*
     * dfs(node, tree)
     *
     * node 직원부터 시작해서
     * 그 직원 아래에 있는 모든 부하 직원의 수를 센다.
     *
     * 자기 자신도 하위 조직에 포함되므로
     * count는 1부터 시작한다.
     */
    static int dfs(int node, List<Integer>[] tree) {

        // 현재 직원 자기 자신을 먼저 1명으로 센다.
        int count = 1;

        /*
         * tree[node]에는
         * 현재 직원이 직접 관리하는 부하 직원들이 들어있다.
         *
         * 예를 들어
         * tree[1] = [6, 7]
         *
         * 이라면
         * 1번 직원의 부하가 6번, 7번이라는 뜻이다.
         */
        for (int child : tree[node]) {

            /*
             * 부하 직원 child를 책임자로 하는
             * 하위 조직의 사람 수도 구한다.
             *
             * 그리고 그 사람 수를
             * 현재 count에 더한다.
             */
            count += dfs(child, tree);
        }

        // 현재 node를 책임자로 하는 하위 조직의 총 인원수
        return count;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 테스트 케이스 개수
        int T = sc.nextInt();

        // 1번 테스트 케이스부터 T번까지 반복
        for (int tc = 1; tc <= T; tc++) {

            // 보고 관계의 개수
            int E = sc.nextInt();

            // 우리가 하위 조직 크기를 구하고 싶은 직원 번호
            int N = sc.nextInt();


            /*
             * 직원 번호가 1 ~ E+1까지 사용된다.
             *
             * 예를 들어 E = 5라면
             * 직원 번호는 1 ~ 6까지 가능하다.
             *
             * 우리는 0번 인덱스를 사용하지 않을 것이므로
             * E + 2 크기로 만든다.
             */
            List<Integer>[] tree = new ArrayList[E + 2];
            //ArrayList를 담을 수 있는 자리 5개


            /*
             * tree 배열의 각 칸에 ArrayList를 만들어준다.
             *
             * 처음 tree를 만들면:
             *
             * tree[1] = null
             * tree[2] = null
             * tree[3] = null
             * ...
             *
             * 상태이기 때문에,
             * 각각 ArrayList를 만들어줘야 한다.
             */
            for (int i = 1; i <= E + 1; i++) {
                tree[i] = new ArrayList<>();
            }


            /*
             * 상사-부하 관계 E개 입력받기
             *
             * 입력이
             *
             * 2 1
             *
             * 이라면
             *
             * 2번 직원의 부하가 1번 직원
             *
             * 이라는 뜻이다.
             */
            for (int i = 0; i < E; i++) {

                int parent = sc.nextInt(); // 상사
                int child = sc.nextInt();  // 부하


                /*
                 * child가 0이면
                 * 그 자리에 부하 직원이 없다는 뜻이므로
                 * Tree에 추가하지 않는다.
                 */
                if (child != 0) {
                    tree[parent].add(child);
                }
            }


            /*
             * N번 직원을 시작점으로 DFS를 돌린다.
             *
             * dfs()가
             * N번 직원 본인 + 모든 부하 직원 수를
             * 반환한다.
             */
            int answer = dfs(N, tree);


            // 문제에서 요구한 출력 형식
            System.out.println("#" + tc + " " + answer);
        }
    }
}