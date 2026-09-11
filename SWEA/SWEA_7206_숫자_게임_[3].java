import java.io.*;
import java.util.*;

public class Solution {

    /*
     * [숫자 만들기 게임]
     *
     * 현재 숫자를 2개 이상의 숫자로 쪼갠다.
     * -> 쪼갠 숫자를 모두 곱한다.
     * -> 만들어진 숫자로 다시 같은 작업을 한다.
     *
     * 한 번 쪼갤 때마다 turn + 1
     * 가능한 방법 중 최대 turn을 구한다.
     *
     *
     * 핵심 아이디어
     *
     * 1234가 있다면 숫자 사이에는 총 3개의 경계가 있다.
     *
     * 1 | 2 | 3 | 4
     *
     * 각 경계를
     *  - 자른다
     *  - 안 자른다
     *
     * 두 가지로 선택할 수 있다.
     *
     * 따라서 모든 자르는 방법을 완전탐색한다.
     *
     *
     * solve(num)
     * = num에서 시작했을 때 앞으로 만들 수 있는 최대 turn
     *
     * 예)
     *
     * solve(123)
     *
     * 1 | 23  -> 23
     * 12 | 3  -> 36
     * 1 | 2 | 3 -> 6
     *
     * 이 각각에 대해서 다시 solve를 수행한다.
     */

    static Map<Integer, Integer> memo;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int start = Integer.parseInt(br.readLine());

            /*
             * 같은 숫자에 대해서 solve를 여러 번 계산할 수 있으므로
             * 결과를 저장한다.
             *
             * key   : 숫자
             * value : 그 숫자에서 만들 수 있는 최대 turn
             */
            memo = new HashMap<>();

            int answer = solve(start);

            System.out.println("#" + tc + " " + answer);
        }
    }


    /*
     * num에서 시작했을 때 만들 수 있는 최대 turn을 반환한다.
     *
     * 예)
     *
     * solve(123)
     *
     * 가능한 한 번의 분할:
     *
     * 1 | 23      -> 23
     * 12 | 3      -> 36
     * 1 | 2 | 3   -> 6
     *
     * 각각
     *
     * 1 + solve(23)
     * 1 + solve(36)
     * 1 + solve(6)
     *
     * 중 최대값을 고른다.
     */
    static int solve(int num) {

        String str = String.valueOf(num);

        /*
         * 한 자리 숫자라면 더 이상 쪼갤 수 없다.
         *
         * 따라서 추가 turn은 0
         */
        if (str.length() == 1) {
            return 0;
        }


        /*
         * 이미 계산했던 숫자라면
         * 다시 계산하지 않고 저장된 값을 사용한다.
         */
        if (memo.containsKey(num)) {
            return memo.get(num);
        }


        int maxTurn = 0;


        /*
         * 예)
         *
         * str = "1234"
         *
         * 길이 = 4
         *
         * 자를 수 있는 위치는
         *
         * 1 | 2 | 3 | 4
         *   0   1   2
         *
         * 총 3개
         */
        int gapCount = str.length() - 1;


        /*
         * gapCount개의 위치마다
         *
         * 0 : 안 자른다
         * 1 : 자른다
         *
         * 모든 경우를 확인한다.
         *
         * 예) 123
         *
         * gapCount = 2
         *
         * 01 -> 1 | 23
         * 10 -> 12 | 3
         * 11 -> 1 | 2 | 3
         *
         * 00은 한 번도 자르지 않는 것이므로 제외한다.
         *
         * 그래서 mask를 1부터 시작한다.
         */
        for (int mask = 1; mask < (1 << gapCount); mask++) {

            int product = 1;

            /*
             * 현재 조각이 시작하는 위치
             */
            int start = 0;


            /*
             * 숫자 사이의 경계를 하나씩 확인한다.
             */
            for (int i = 0; i < gapCount; i++) {

                /*
                 * i번째 위치를 자르는 경우
                 *
                 * 예)
                 *
                 * 1234에서
                 * i = 1 위치를 자른다면
                 *
                 * 12 | 34
                 *
                 * str.substring(start, i + 1)
                 * 를 이용해서 앞쪽 숫자를 가져온다.
                 */
                if ((mask & (1 << i)) != 0) {

                    String piece = str.substring(start, i + 1);

                    int value = Integer.parseInt(piece);

                    product *= value;


                    /*
                     * 방금 i까지 잘랐으므로
                     * 다음 숫자는 i + 1에서 시작
                     */
                    start = i + 1;
                }
            }


            /*
             * 마지막 조각은 반복문에서 처리되지 않는다.
             *
             * 예)
             *
             * 12 | 34
             *
             * 앞에서 12는 처리했지만
             * 마지막 34는 여기서 처리해야 한다.
             */
            String lastPiece = str.substring(start);

            product *= Integer.parseInt(lastPiece);


            /*
             * 이번에 한 번 쪼갰으므로 +1
             *
             * 이후 product에서 다시 게임을 진행한다.
             */
            int turn = 1 + solve(product);


            /*
             * 여러 방법 중 가장 큰 turn을 선택
             */
            maxTurn = Math.max(maxTurn, turn);
        }


        /*
         * num에서 만들 수 있는 최대 turn을 저장
         */
        memo.put(num, maxTurn);

        return maxTurn;
    }
}