// SWEA #7733 · 치즈 도둑
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWrDOdQqRCUDFARG
// Language: Java
// Execution Time: 284 ms
// Memory: 36232 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 날마다 치즈칸들이 지워짐. 덩어리가 가장 많을때?
	 */
	
	/*
	 * 덩어리를 어떻게 count 할것인가.
	 * day보다 큰 날에 대해 하나를 잡고 주변 dfs로 다 눌러버리기.
	 * 하나 누를때마다 count++하고..
	 * 
	 * 다 누르고 나서는 day 보다 큰것들은 다시 false로 원복하기.
	 */
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			N = Integer.parseInt(br.readLine());//N은 한변의 길이
			map = new int[N][N];
			visited = new boolean[N][N];
			int high = 0;
			for(int r=0;r<N;r++) {//map 배열.
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					high = Math.max(map[r][c], high);
				}
			}
			int result=1;
			for(int day=1;day<=high-1;day++) {
				int count=0;
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(map[r][c] > day) visited[r][c] = false;//해당 날짜 보다 높은 애들은 살아 있음.
						else if(map[r][c] == day) visited[r][c] = true;//해당 날짜에 비활성화됨.
					}
				}//살릴거 살리고, 죽일거 죽이고...
				
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(map[r][c]>day && !visited[r][c]) {//day보다 큰 블록, 방문하지 않은 블록
							visited[r][c] = true;
							dfs(r, c, day);
							count++;
						}
					}
				}
				result = Math.max(result, count);
			}
			System.out.printf("#%d %d%n",times, result);
		}
	}
	static void dfs(int r, int c, int day) {
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc] || map[nr][nc]<=day) continue;
			
			visited[nr][nc] = true;
			dfs(nr, nc, day);
		}
	}
}