// SWEA #1249 · [S/W 문제해결 응용] 4일차 - 보급로
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD
// Language: Java
// Execution Time: 182 ms
// Memory: 34900 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 
	 */
	static int N;
	static int[][] map;
	static int[][] dist;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int r=0;r<N;r++) {
				String line = br.readLine();
				for(int c=0;c<N;c++) {
					map[r][c] = line.charAt(c)-'0';
				}
			}
			dist = new int[N][N];
			for(int i=0;i<N;i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			dist[0][0] = 0;
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
			pq.offer(new int[] {0,0,0});
			
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int r = now[0];
				int c = now[1];
				int cost = now[2];
				
				for(int d=0;d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					
					if(dist[nr][nc]>map[nr][nc]+cost) {
						dist[nr][nc] = map[nr][nc]+cost;
						pq.offer(new int[] {nr, nc, dist[nr][nc]});
					}
				}
			}
			System.out.printf("#%d %d%n",tc, dist[N-1][N-1]);
		}
	}
}