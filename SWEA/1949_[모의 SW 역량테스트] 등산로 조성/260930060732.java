// SWEA #1949 · [모의 SW 역량테스트] 등산로 조성
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq
// Language: Java
// Execution Time: 99 ms
// Memory: 27136 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 가장 높은 봉우리에서 시작, 높->낮으로 가로세로 이동.
	 * 딱한번 최대 K만큼 깎을 수 있음.
	 * 가장 긴 등산로.
	 */
	static int N,K;
	static int[][] map;
	static boolean[][] visited;
	static List<Node> starts;
	static int maxLength;
	
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 map의 크기
			K = Integer.parseInt(st.nextToken());//K는 줄일수 있는 크기.
			map = new int[N][N];
			visited = new boolean[N][N];
			starts = new ArrayList<Solution.Node>();
			int high=0;
			for(int r=0;r<N;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					high = Math.max(map[r][c], high);
				}
			}
			maxLength = 1;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(map[r][c]==high) {
						starts.add(new Node(r,c));
					}
				}
			}
			for(int i=0;i<starts.size();i++) {
				Node now = starts.get(i);
				int r = now.r;
				int c = now.c;
				visited[r][c] = true;
				dfs(r, c, false, 1);
				visited[r][c] = false;
			}
			System.out.printf("#%d %d%n",tc,maxLength);
		}
	}
	static void dfs(int r, int c, boolean isUsed, int length) {
		maxLength = Math.max(maxLength, length);
		for(int d=0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
			if(map[r][c]>map[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, isUsed, length+1);
				visited[nr][nc] = false;
			}else if(map[r][c]>map[nr][nc]-K && !isUsed) {
				visited[nr][nc] = true;
				int original = map[nr][nc];
				map[nr][nc] = map[r][c]-1;
				dfs(nr, nc, true, length+1);
				visited[nr][nc] = false;
				map[nr][nc] = original;
			}
			
		}
	}
	static class Node{
		int r,c;
		public Node(int r, int c) {
			// TODO Auto-generated constructor stub
			this.r=r;
			this.c=c;
		}
	}
}