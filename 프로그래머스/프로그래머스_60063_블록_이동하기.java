import java.util.*;

class Solution {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board) {

        int N = board.length;

        Queue<Robot> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new Robot(0, 0, 0, 1, 0));
        visited.add(makeState(0, 0, 0, 1));

        while (!queue.isEmpty()) {

            Robot now = queue.poll();

            int r1 = now.r1;
            int c1 = now.c1;
            int r2 = now.r2;
            int c2 = now.c2;
            int time = now.time;

            // 목적지 도착
            if ((r1 == N - 1 && c1 == N - 1) ||
                (r2 == N - 1 && c2 == N - 1)) {
                return time;
            }


            // ========================
            // 1. 상하좌우 평행 이동
            // ========================

            for (int d = 0; d < 4; d++) {

                int nr1 = r1 + dr[d];
                int nc1 = c1 + dc[d];

                int nr2 = r2 + dr[d];
                int nc2 = c2 + dc[d];

                // 범위 밖
                if ((nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= N) ||
                    (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= N)) {
                    continue;
                }

                // 벽
                if (board[nr1][nc1] == 1 ||
                    board[nr2][nc2] == 1) {
                    continue;
                }

                String state = makeState(nr1, nc1, nr2, nc2);

                if (!visited.contains(state)) {

                    visited.add(state);

                    queue.offer(
                        new Robot(nr1, nc1, nr2, nc2, time + 1)
                    );
                }
            }


            // ========================
            // 2. 회전
            // ========================

            // 가로 상태
            if (r1 == r2) {

                // ----------------
                // 아래쪽으로 회전
                // ----------------
                if (r1 + 1 < N &&
                    board[r1 + 1][c1] == 0 &&
                    board[r2 + 1][c2] == 0) {

                    // 첫 번째 칸을 축으로
                    addRobot(
                        queue,
                        visited,
                        r1, c1,
                        r1 + 1, c1,
                        time + 1
                    );

                    // 두 번째 칸을 축으로
                    addRobot(
                        queue,
                        visited,
                        r2, c2,
                        r2 + 1, c2,
                        time + 1
                    );
                }


                // ----------------
                // 위쪽으로 회전
                // ----------------
                if (r1 - 1 >= 0 &&
                    board[r1 - 1][c1] == 0 &&
                    board[r2 - 1][c2] == 0) {

                    // 첫 번째 칸을 축으로
                    addRobot(
                        queue,
                        visited,
                        r1, c1,
                        r1 - 1, c1,
                        time + 1
                    );

                    // 두 번째 칸을 축으로
                    addRobot(
                        queue,
                        visited,
                        r2, c2,
                        r2 - 1, c2,
                        time + 1
                    );
                }
            }


            // 세로 상태
            else {

                // ----------------
                // 오른쪽으로 회전
                // ----------------
                if (c1 + 1 < N &&
                    board[r1][c1 + 1] == 0 &&
                    board[r2][c2 + 1] == 0) {

                    // 첫 번째 칸 축
                    addRobot(
                        queue,
                        visited,
                        r1, c1,
                        r1, c1 + 1,
                        time + 1
                    );

                    // 두 번째 칸 축
                    addRobot(
                        queue,
                        visited,
                        r2, c2,
                        r2, c2 + 1,
                        time + 1
                    );
                }


                // ----------------
                // 왼쪽으로 회전
                // ----------------
                if (c1 - 1 >= 0 &&
                    board[r1][c1 - 1] == 0 &&
                    board[r2][c2 - 1] == 0) {

                    // 첫 번째 칸 축
                    addRobot(
                        queue,
                        visited,
                        r1, c1,
                        r1, c1 - 1,
                        time + 1
                    );

                    // 두 번째 칸 축
                    addRobot(
                        queue,
                        visited,
                        r2, c2,
                        r2, c2 - 1,
                        time + 1
                    );
                }
            }
        }

        return -1;
    }


    // 큐에 넣는 부분 반복되니까 함수로 뺌
    static void addRobot(
        Queue<Robot> queue,
        Set<String> visited,
        int r1, int c1,
        int r2, int c2,
        int time
    ) {

        String state = makeState(r1, c1, r2, c2);

        if (!visited.contains(state)) {

            visited.add(state);

            queue.offer(
                new Robot(r1, c1, r2, c2, time)
            );
        }
    }


    // 같은 로봇 상태인데 두 좌표 순서만 다른 경우 방지
    static String makeState(
        int r1, int c1,
        int r2, int c2
    ) {

        if (r1 > r2 || (r1 == r2 && c1 > c2)) {

            int tempR = r1;
            int tempC = c1;

            r1 = r2;
            c1 = c2;

            r2 = tempR;
            c2 = tempC;
        }

        return r1 + "," + c1 + "," + r2 + "," + c2;
    }
}


class Robot {

    int r1, c1;
    int r2, c2;
    int time;

    public Robot(
        int r1, int c1,
        int r2, int c2,
        int time
    ) {

        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
        this.time = time;
    }
}