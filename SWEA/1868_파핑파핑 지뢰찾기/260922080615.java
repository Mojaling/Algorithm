// SWEA #1868 · 파핑파핑 지뢰찾기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc
// Language: Java
// Execution Time: 183 ms
// Memory: 39812 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 최소 몇번 클릭해야하는가?
	 */
	static int N;
	static char[][] charMap;
	static int[][] intMap;
	static boolean[][] boolMap;
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			N = Integer.parseInt(br.readLine());//N은 map의 크기.
			charMap = new char[N][N];
			for(int i=0;i<N;i++) {
				String line = br.readLine();
				for(int j=0;j<N;j++) {
					charMap[i][j] = line.charAt(j);
				}
			}
			intMap = new int[N][N];
			boolMap = new boolean[N][N];
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(charMap[r][c]=='*') {
						
						for(int d=0;d<8;d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
							
							intMap[nr][nc]++;
						}
					}
				}
			}
			
			int count=0;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(charMap[r][c] == '*' || intMap[r][c]!=0 || boolMap[r][c]) continue;
					boolMap[r][c] = true;
					count++;
					dfs(r, c);
				}
			}
			
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(charMap[r][c] == '*' || boolMap[r][c]) continue;
					boolMap[r][c] = true;
					count++;
				}
			}
			System.out.printf("#%d %d%n",times, count);
		}
	}
	static void dfs(int r, int c) {
		for(int d=0;d<8;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || boolMap[nr][nc] || charMap[nr][nc]=='*') continue;
			boolMap[nr][nc] = true;
			if(intMap[nr][nc]==0) {
				dfs(nr, nc);
			}
		}
	}
}