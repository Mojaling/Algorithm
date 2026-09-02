import java.util.*;
import java.io.*;
/*
 * 1->벽 ,0->길, 2->스타트지점,3->엔드지점
 * 길이 있는지 없는지.
 */

public class Solution {
	static int endR;
	static int endC;
	static int[][] maze;
	static int N;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int times=1;times<=10;times++) {
			int T = Integer.parseInt(br.readLine());
			N =16;
			maze = new int[N][N];
			visited = new boolean[N][N];
			int startR = 0;
			int startC = 0;
			endR = 0;
			endC = 0;
			for(int i=0;i<N;i++) {//maze에 숫자들 넣기. 시작,끝 x,y 좌표알기
				String line = br.readLine();
				for(int j=0; j<N;j++) {
					maze[i][j] = line.charAt(j) - '0';
					if(maze[i][j]==2) {
						startR = i;
						startC = j;
					}else if (maze[i][j]==3) {
						endR = i;
						endC = j;
					}
				}
			}
			int result =-1;
			if(dfs(startR, startC)) {
				result = 1;
			}else {
				result =0;
			}
			System.out.printf("#%d %d%n",times, result);
		}
    }
	
	static boolean dfs(int r, int c) {
		if(r==endR && c==endC) {
			return true;
		}
		
		visited[r][c] = true;
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		for(int i=0;i<4;i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || maze[nr][nc]==1 || visited[nr][nc]) {
				continue;
			}
			if(dfs(nr,nc)) {
				return true;
			}
		}
		return false;
	}
}