// SWEA #1227 · [S/W 문제해결 기본] 7일차 - 미로2
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14wL9KAGkCFAYD
// Language: Java
// Execution Time: 109 ms
// Memory: 31508 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * bfs로 풀어보자.
	 */
	static int N=100;
	static int[][] map = new int[N][N];
	static boolean[][] visited = new boolean[N][N];
	static int startR, startC, endR, endC;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int times=1;times<=10;times++) {
			int T = Integer.parseInt(br.readLine());
			for(int r=0;r<N;r++) {//map 배열.
				String line = br.readLine();
				for(int c=0;c<N;c++) {
					map[r][c] = line.charAt(c) - '0';
					if(map[r][c]==2) {
						startR = r;
						startC = c;
					}else if(map[r][c]==3) {
						endR = r;
						endC = c;
					}
				}
			}
			for(int i=0;i<N;i++) {
				Arrays.fill(visited[i], false);
			}
			visited[startR][startC] = true;
			
			Deque<int[]> dq = new ArrayDeque<int[]>();
			dq.offer(new int[] {startR, startC});
			
			int result = 0;
			
			while(!dq.isEmpty()) {
				int[] now = dq.poll();
				int r = now[0];
				int c = now[1];
				
				if(r==endR && c==endC) {
					result=1;
				}
				
				for(int d=0;d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc]==1 || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					dq.offer(new int[] {nr,nc});
				}
			}
			
			System.out.printf("#%d %d%n",times, result);
		}
	}
}