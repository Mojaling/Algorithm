import java.util.*;
import java.io.*;
/*
 * 등산로는 가장 높은 봉우리에서 시작.
 * 높 -> 낮은 지형 이동.
 * 긴 등산로를 위해 딱 한곳만 K 깊이만큼 깎을 수 있음.
 */

public class Solution {
	static int result;
	static int N;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int times = 1; times <= T; times++) {//10개의 테스트 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // N은 지도 한변의길이
			K = Integer.parseInt(st.nextToken()); // K는 깎을 수 있는 높이
			map = new int[N][N];
			result = 0;
			visited = new boolean[N][N];
			int maxHeight = Integer.MIN_VALUE;
			for(int i=0;i<N;i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st1.nextToken());
					maxHeight = Math.max(map[i][j], maxHeight);
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if (map[i][j] == maxHeight) {
						dfs(i, j , 1, false);
					}
				}
			}
			System.out.printf("#%d %d%n", times, result);
		}
	}
	
	static void dfs(int r, int c, int length, boolean use) {
		result = Math.max(result, length);
		visited[r][c] = true;
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) {
				continue;
			}
			
			if(map[nr][nc] < map[r][c]) {
				dfs(nr, nc, length +1, use);
			} else if (map[nr][nc] -K < map[r][c] && use == false) {
				int original = map[nr][nc];
				map[nr][nc] = map[r][c]-1;
				dfs(nr, nc, length+1, true);
				map[nr][nc] = original;
			}
		}
		visited[r][c] = false;
	}
}