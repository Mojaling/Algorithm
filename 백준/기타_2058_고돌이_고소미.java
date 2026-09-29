import java.io.*;
import java.util.*;

/*
 * 두 사람 각자의 탈출지점.
 * 기지는 N*N
 * 도주 과정에서 인접해서는 안됨.
 *
 * 1초단위로 8방향 + 제자리 이동
 * 8방향에 대해 인접해 있으면 안됨.
 * 1인칸은 벽임.
 * 둘다 탈출하기 위한 최소 시간.
 */
public class Solution {
	static int N;
	static int[][] map;
	static Node nowX;
	static Node nowY;
	static Set<String> visited;

	// 8방향 + 제자리
	static int[] dr = {-1,-1,-1,0,1,1,1,0,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1,0};

	public static void main(String[] args) throws Exception {

		BufferedReader br =
				new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 문제 좌표가 1부터 시작하므로 -1
		int startXR = Integer.parseInt(st.nextToken()) - 1;
		int startXC = Integer.parseInt(st.nextToken()) - 1;
		nowX = new Node(startXR, startXC);

		int endXR = Integer.parseInt(st.nextToken()) - 1;
		int endXC = Integer.parseInt(st.nextToken()) - 1;


		st = new StringTokenizer(br.readLine());

		int startYR = Integer.parseInt(st.nextToken()) - 1;
		int startYC = Integer.parseInt(st.nextToken()) - 1;
		nowY = new Node(startYR, startYC);

		int endYR = Integer.parseInt(st.nextToken()) - 1;
		int endYC = Integer.parseInt(st.nextToken()) - 1;


		map = new int[N][N];

		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}


		visited = new HashSet<>();

		Deque<Node[]> dq = new ArrayDeque<>();

		dq.offer(new Node[] {nowX, nowY});

		// 시작 상태 방문처리
		visited.add(startXR + "," + startXC + "," + startYR + "," + startYC);


		int count = 0;


		while(!dq.isEmpty()) {

			// 현재 시간대에 있는 상태들 개수
			int size = dq.size();

			for(int s = 0; s < size; s++) {

				Node[] now = dq.poll();

				int r1 = now[0].r;
				int c1 = now[0].c;

				int r2 = now[1].r;
				int c2 = now[1].c;


				// 둘 다 각자 목적지 도착
				if(r1 == endXR && c1 == endXC &&
				   r2 == endYR && c2 == endYC) {

					System.out.println(count);
					return;
				}


				// X 9가지 이동
				for(int d1 = 0; d1 < 9; d1++) {

					int nr1 = r1 + dr[d1];
					int nc1 = c1 + dc[d1];


					// Y 9가지 이동
					for(int d2 = 0; d2 < 9; d2++) {

						int nr2 = r2 + dr[d2];
						int nc2 = c2 + dc[d2];


						// 범위 밖 또는 벽
						if(nr1 < 0 || nr1 >= N ||
						   nc1 < 0 || nc1 >= N ||
						   nr2 < 0 || nr2 >= N ||
						   nc2 < 0 || nc2 >= N ||
						   map[nr1][nc1] == 1 ||
						   map[nr2][nc2] == 1) {

							continue;
						}


						// 둘이 인접하면 안됨
						if(Math.abs(nr1 - nr2) <= 1 &&
						   Math.abs(nc1 - nc2) <= 1) {

							continue;
						}


						String next =
								nr1 + "," + nc1 + "," +
								nr2 + "," + nc2;


						// 이미 같은 위치 조합을 방문함
						if(visited.contains(next)) {
							continue;
						}


						// 방문처리
						visited.add(next);


						Node X = new Node(nr1, nc1);
						Node Y = new Node(nr2, nc2);

						dq.offer(new Node[] {X, Y});
					}
				}
			}

			// 한 레벨 끝났으므로 1초 증가
			count++;
		}
	}


	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}