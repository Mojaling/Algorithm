import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 배터리 가장 적게 쓰면서 목적지.
	 * 이웃칸 갈때마다 1소모.
	 * 높이 차만큼 배터리가 추가로 소모.
	 * 
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			int N = Integer.parseInt(br.readLine());//N은 map크기
			int[][] map = new int[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]= Integer.parseInt(st.nextToken()); 
				}
			}
			
			int[][] dist = new int[N][N];
			for(int i=0;i<N;i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			dist[0][0] = 0;
			
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> a[2]-b[2]);
			pq.offer(new int[] {0,0,0});
			
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int r = now[0];
				int c = now[1];
				int cost = now[2];
				
				if(cost > dist[r][c]) {//cost가 기존것보다 클 경우 패스.
					continue;
				}
				
				int[] dr = {-1,1,0,0};
				int[] dc = {0,0,-1,1};
				
				for(int d=0;d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) {
						continue;
					}
					int nowCost = cost + 1;
					
					if(map[nr][nc] > map[r][c]) {
						nowCost += map[nr][nc] - map[r][c];
					}
					if(dist[nr][nc] > nowCost) {//nowCost가 기존보다 더 작을경우
						dist[nr][nc] = nowCost;
						pq.offer(new int[] {nr, nc, nowCost});
					}
				}
			}
			System.out.printf("#%d %d%n", times, dist[N-1][N-1]);
		}
	}
	
	
}