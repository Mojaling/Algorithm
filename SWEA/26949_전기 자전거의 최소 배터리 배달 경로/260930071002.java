// SWEA #26949 · 전기 자전거의 최소 배터리 배달 경로
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AZ6wpJdKHeHHBIQj
// Language: Java
// Execution Time: 202 ms
// Memory: 39296 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 배터리 가장 적게 쓰면서 목적지.
	 * 한칸 이동마다 기본1, 더 높은데 가면 그 차이만큼 배터리 소모.
	 */
	static int N;
	static int[][] map;
	static int[][] dist;
	static int minEnergy;
	
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			minEnergy=Integer.MAX_VALUE;
			map = new int[N][N];
			
			for(int r=0;r<N;r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			dist = new int[N][N];
			for(int i=0;i<N;i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			dist[0][0] = 0;
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2]-b[2]);
			pq.offer(new int[] {0,0,0});//r,c,energy
			
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int r = now[0];
				int c = now[1];
				int e = now[2];
				
				for(int d=0;d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					
					if(map[r][c]>map[nr][nc]) {
						int ne = e+1;
						if(ne<dist[nr][nc]) {
							dist[nr][nc] = ne;
							pq.offer(new int[] {nr,nc,ne});
						}
					}else {
						int ne = e+1+map[nr][nc]-map[r][c];
						if(ne<dist[nr][nc]) {
							dist[nr][nc] = ne;
							pq.offer(new int[] {nr,nc,ne});
						}
					}
				}
			}
			System.out.printf("#%d %d%n",tc, dist[N-1][N-1]);
		}
	}
}