// SWEA #2805 · 농작물 수확하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB
// Language: Java
// Execution Time: 105 ms
// Memory: 29824 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 
	 */
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int length,r,c,sum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int r=0;r<N;r++) {
				String line = br.readLine();
				for(int c=0;c<N;c++) {
					map[r][c]=line.charAt(c) - '0';
				}
			}
			r = N/2;
			c = N/2;
			length = N/2;
			sum = 0;
			Deque<int[]> dq = new ArrayDeque<>();
			dq.offer(new int[] {r,c,0});
			visited[r][c]=true;
			sum+=map[r][c];
			
			while(!dq.isEmpty()) {
				int[] now = dq.poll();
				int r = now[0];
				int c = now[1];
				int l = now[2];
				
				if(l<length) {
					for(int d=0;d<4;d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(visited[nr][nc]) continue;
						sum+=map[nr][nc];
						visited[nr][nc] = true;
						dq.offer(new int[] {nr,nc,l+1});
					}
				}
			}
			System.out.printf("#%d %d%n",tc, sum);
		}
	}
}