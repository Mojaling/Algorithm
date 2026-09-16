import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 100*100 행렬
	 * 1은 벽, 0은 길, 2는 출발점, 3은 도착점
	 * 도달가능여부 1:가능, 0:불가능
	 */
	static int[][] map;
	static boolean[][] visited;
	static int startR, startC;
	static int endR;
	static int endC;
	static int result;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int times=1;times<=10;times++) {
			int T = Integer.parseInt(br.readLine());
			map = new int[100][100];
			visited = new boolean[100][100];
			for(int i=0;i<100;i++) {//map 배열.
				String line = br.readLine();
				for(int j=0;j<100;j++) {
					map[i][j] = line.charAt(j) - '0';
					if(map[i][j]==2) {
						startR = i;
						startC = j;
					}else if(map[i][j]==3) {
						endR = i;
						endC = j;
					}
				}
			}
			result =0;
			visited[startR][startC] = true;
			dfs(startR, startC);
			
			System.out.printf("#%d %d%n",times, result);
		}
	}
	private static void dfs(int startR, int startC) {
		if(startR == endR && startC == endC) {
			result =1;
		}
		int r = startR;
		int c = startC;
		
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0|| nr>=100 || nc<0 || nc>=100 || visited[nr][nc] || map[nr][nc]==1) {
				continue;
			}
			visited[nr][nc] = true;
			dfs(nr, nc);
			visited[nr][nc] = false;
		}
		
	}
}