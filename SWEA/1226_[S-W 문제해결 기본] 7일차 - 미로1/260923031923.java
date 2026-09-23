// SWEA #1226 · [S/W 문제해결 기본] 7일차 - 미로1
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14vXUqAGMCFAYD
// Language: Java
// Execution Time: 79 ms
// Memory: 25088 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 
	 */
	static int N=16;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int startR, startC, endR, endC;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int times=1;times<=10;times++) {
			int T = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int r=0;r<N;r++) {
				String line = br.readLine();
				for(int c=0;c<N;c++) {
					map[r][c] = line.charAt(c)-'0';
					if(map[r][c] ==2) {
						startR = r;
						startC = c;
					}else if(map[r][c]==3) {
						endR = r;
						endC = c;
					}
				}
			}
			result = 0;
			visited[startR][startC] = true;
			dfs(startR, startC);
			
			System.out.printf("#%d %d%n",times, result);
			
		}
	}
	static void dfs(int r, int c) {
		if(r==endR && c==endC) {
			result = 1;
			return;
		}
		
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc] || map[nr][nc]==1) continue;
			visited[nr][nc]=true;
			dfs(nr,nc);
		}
	}
}