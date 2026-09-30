// SWEA #1767 · [SW Test 샘플문제] 프로세서 연결하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
// Language: Java
// Execution Time: 191 ms
// Memory: 31092 KB

import java.io.*;
import java.util.*;

public class Solution {
	static class Node{
		int r,c;
		public Node(int r, int c) {
			// TODO Auto-generated constructor stub
			this.r=r;
			this.c=c;
		}
	}
	/*
	 * n*n cell
	 * 전선은 직선. 교차 x
	 * 끝자리에 있는 core는 연결처리.
	 * 최대한 많은 Core를 연결하고, 전선길이의 합은 최소가 되게하라.
	 */
	static int N;
	static int nodesSize;
	static int[][] map;
	static List<Node> nodes;
	static int maxCore;
	static int minLength;
	
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			nodes = new ArrayList<Solution.Node>();
			maxCore = 0;
			minLength = Integer.MAX_VALUE;
			for(int r=0;r<N;r++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for(int c=0;c<N;c++) {
					int now = Integer.parseInt(st.nextToken());
					map[r][c] = now;
					if(now ==1) {
						if(r!=0 && r!=N-1 && c!=0 && c!=N-1) {
							nodes.add(new Node(r,c));
						}
					}
				}
			}//map배열 및, 전선 연결해야하는 node 정보 입력.
			nodesSize = nodes.size();
			dfs(0,0,0);
			System.out.printf("#%d %d%n",tc, minLength);
		}
	}
	static void dfs(int depth, int cores, int length) {
		if(depth==nodesSize) {//Core를 모두 골랐을때
			if(cores>maxCore) {
				maxCore = cores;
				minLength = length;
			}else if(cores==maxCore) {
				minLength = Math.min(length, minLength);
			}
			return;
		}
		
		
		Node now = nodes.get(depth);
		int r = now.r;
		int c = now.c;
		//코어를 골랐을 경우.
		for(int d=0;d<4;d++) {
			if(isOK(r, c, d)) {//길이 괜찮을경우
				int nowLength = setWire(r, c, d, 2);//2로 전부다 바꿔버리기.
				dfs(depth+1, cores+1, length+nowLength);
				setWire(r,c,d,0);;//원복하기.
			}
		}
		//코어를 안고른경우
		dfs(depth+1,cores,length);
	}

	
	static boolean isOK(int r, int c, int d) {

		int nr = r + dr[d];
		int nc = c + dc[d];

		while (nr >= 0 && nr < N  && nc >= 0 && nc < N) {
			if (map[nr][nc] != 0) {
				return false;
			}
			nr += dr[d];
			nc += dc[d];
		}
		return true;
	}
	
	static int setWire(int r, int c, int d, int value) {//해당 방향으로 wire를 세팅함.
		int nr = r + dr[d];
		int nc = c + dc[d];
		int length=0;
		while (nr >= 0 && nr < N  && nc >= 0 && nc < N) {
			map[nr][nc] = value;
			length++;
			nr+=dr[d];
			nc+=dc[d];
		}
		return length;
	}
}
