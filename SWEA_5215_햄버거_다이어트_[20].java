import java.util.Scanner;

public class Solution {

    static int N;           // 재료의 개수
    static int L;           // 제한 칼로리

    static int[] taste;     // 각 재료의 맛 점수
    static int[] calorie;   // 각 재료의 칼로리

    static int maxScore;    // 가능한 조합 중 최대 맛 점수

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();   // 테스트 케이스 개수

        for (int tc = 1; tc <= T; tc++) {

            N = sc.nextInt();   // 재료 개수
            L = sc.nextInt();   // 제한 칼로리

            taste = new int[N];
            calorie = new int[N];

            for (int i = 0; i < N; i++) {
                taste[i] = sc.nextInt();
                calorie[i] = sc.nextInt();
            }

            maxScore = 0;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + maxScore);
        }

        sc.close();
    }

    static void dfs(int index, int score, int cal) {

        // 제한 칼로리를 넘었다면 더 탐색할 필요가 없다.
        if (cal > L) {
            return;
        }

        // 모든 재료를 선택할지 말지 결정했다면
        if (index == N) {
            maxScore = Math.max(maxScore, score);
            return;
        }

        // 현재 재료를 선택하는 경우
        dfs(
            index + 1,
            score + taste[index],
            cal + calorie[index]
        );

        // 현재 재료를 선택하지 않는 경우
        dfs(
            index + 1,
            score,
            cal
        );
    }
}