// SWEA #1247 · [S/W 문제해결 응용] 3일차 - 최적 경로
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
// Language: Java
// Execution Time: 326 ms
// Memory: 27488 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 회사, 집, 고객 위치 x,y
	 * 거리는 차의 절댓값 합으로.
	 * 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것.
	 * 회사에서 출발, 모두 방문, 집으로.
	 */
	static int N;
	static int minCost;
	static int[] order;
	static boolean[] visited;
	static int companyX, companyY, homeX, homeY;;
	static int[] customerX, customerY;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			order = new int[N];
			
			customerX = new int[N];
			customerY = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			companyX = Integer.parseInt(st.nextToken());
			companyY = Integer.parseInt(st.nextToken());
			
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			for(int i=0;i<N;i++) {
				customerX[i] = Integer.parseInt(st.nextToken());
				customerY[i] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N];
			minCost = Integer.MAX_VALUE;
			
			dfs(0,0);
			
			System.out.printf("#%d %d%n",tc, minCost);
		}
	}
	static void dfs(int count, int distance) {
		if(count == N) {
			 distance+=(int) (Math.abs(homeX-customerX[order[N-1]]) + Math.abs(homeY-customerY[order[N-1]]));
			 minCost = Math.min(minCost, distance);
			 return;
		}
		
		
		if(distance >= minCost) {
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			order[count] = i;
			visited[i]=true;
			int original = distance;
			if(count==0) {
				 distance+=(int) (Math.abs(companyX-customerX[order[count]]) + Math.abs(companyY-customerY[order[count]]));
			 }else {
				 int prevIndex = order[count - 1];
				 distance+=(int)(Math.abs(customerX[order[count]]-customerX[prevIndex]) + Math.abs(customerY[order[count]]-customerY[prevIndex]));
			 }
			dfs(count+1,distance);
			distance = original;
			visited[i]=false;
			order[count]=0;
		}
		
	}
	
}