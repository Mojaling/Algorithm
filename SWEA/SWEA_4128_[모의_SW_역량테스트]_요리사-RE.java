import java.util.*;
import java.io.*;
/*
 * 식재료 N/2로 나누어 요리.
 * 맛 차이 최소 되게끔.
 * 
 */

public class Solution {
	static int N;
	static boolean[] visited;
	static int[][] map;
	static int minResult;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int times = 1; times <= T; times++) {
			N = Integer.parseInt(br.readLine());//N은 재료의 개수
			map = new int[N][N];
			for(int i=0;i<N;i++) {//map 채우기
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N];
			minResult = Integer.MAX_VALUE;
			dfs(0,0);
			System.out.printf("#%d %d%n",times, minResult);
		}
	}
	static void dfs(int start, int count) {
		if(count == N/2) {
			int resultA = 0;
			int resultB = 0;
			for(int i =0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					if(visited[i] && visited[j]) {
						resultA += map[i][j] + map[j][i];
					}
					if(!visited[i] && !visited[j]) {
						resultB += map[i][j] + map[j][i];
					}
				}
			}
			minResult = Math.min(minResult, Math.abs(resultA-resultB));
			return;
		}
		for(int i=start;i<N;i++) {
			visited[i] = true;
			dfs(i+1, count+1);
			visited[i] = false;
		}
	}
}