import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt(); // 전체 노드 개수
            int M = sc.nextInt(); // 리프 노드 개수
            int L = sc.nextInt(); // 값을 알고 싶은 노드 번호

            // 노드 번호를 그대로 인덱스로 사용하기 위해 N+1
            int[] tree = new int[N + 1];

            // 문제에서 주어진 리프 노드의 값을 저장
            for (int i = 0; i < M; i++) {

                int node = sc.nextInt();
                int value = sc.nextInt();

                tree[node] = value;
            }

            // 아래쪽 부모부터 위쪽 부모까지 계산
            for (int i = N / 2; i >= 1; i--) {

                // 왼쪽 자식
                tree[i] += tree[i * 2];

                // 오른쪽 자식이 실제로 존재할 경우
                if (i * 2 + 1 <= N) {
                    tree[i] += tree[i * 2 + 1];
                }
            }

            System.out.println("#" + tc + " " + tree[L]);
        }
    }
}