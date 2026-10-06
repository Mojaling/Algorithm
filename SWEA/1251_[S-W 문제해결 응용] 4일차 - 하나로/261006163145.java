// SWEA #1251 · [S/W 문제해결 응용] 4일차 - 하나로
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
// Language: Java
// Execution Time: 896 ms
// Memory: 84068 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * N개의 섬들 연결. 
	 * 비용이 있음. (E * L^2) 최소로 지불.
	 */
	static class Edge implements Comparable<Edge>{
		int from, to;
		long weight;//거리는 long

		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight); //가중치 기준 오름차순
		}
	}
	static int N;
	static Edge[] edgeList;
	static int[] parents;
	
	static void makeSets() {
		for(int i=0;i<N;i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			int[] x= new int[N];
			int[] y= new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			
			//1. 모든 섬 쌍에 대해 간선 생성(완전그래프, N(N-1)/2)
			edgeList = new Edge[N*(N-1)/2];
			int idx=0;
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					long dx = x[i] - x[j];
					long dy = y[i] - y[j];
					edgeList[idx++] = new Edge(i,j,dx*dx+dy*dy);
				}
			}
			
			Arrays.sort(edgeList);//간선 비용 최소 정렬
			parents = new int[N];
			makeSets();
 
			long result = 0; // L^2의 합
			int cnt = 0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++cnt == N - 1) break;
				}
			}
			
			// 3. E는 마지막에 한 번만 곱하고 반올림
			sb.append("#").append(tc).append(" ").append(Math.round(result * E)).append("\n");	
		}
		System.out.print(sb);
	}
}