// SWEA #2115 · [모의 SW 역량테스트] 벌꿀채취
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
// Language: Java
// Execution Time: 108 ms
// Memory: 27392 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * N*N 벌통. 두명의 일꾼. 벌통의 수 M.가로로 연속되게. 서로 안겹치게.
	 * 벌통에는 하나씩. 최대 양 C.
	 */
	
	/*
	 * 하나씩 고른다고 했을때, 각 칸에서 나오는 최대 이득치를 profit으로 구하고
	 * 여기서 2개를 뽑자.
	 */
	static int N,M,C;
	static int[][] map;
	static int[][] profit;
	
	static int maxSum;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 map 크기.
			M = Integer.parseInt(st.nextToken());//M은 벌통 개수
			C = Integer.parseInt(st.nextToken());//C는 최대양.
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			profit = new int[N][N-M+1];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N-M+1;j++) {
					maxSum = 0;
					
					dfs(i, j,0,0,0);
					profit[i][j] = maxSum;
				}
			}
			answer=0;
			for(int r1=0;r1<N;r1++) {
				for(int c1=0;c1<=N-M;c1++) {
					
					for(int r2=0;r2<N;r2++) {
						for(int c2=0;c2<=N-M;c2++) {
							
							if(r1==r2) {
								if(Math.abs(c1-c2)<M) continue;
							}
							
							int sum = profit[r1][c1] + profit[r2][c2];
							
							answer = Math.max(answer, sum);
						}
					}
				}
			}
			System.out.printf("#%d %d%n",times, answer);
		}
	}
	static void dfs(int startR, int startC, int depth, int honeySum, int moneySum) {
		if(depth == M) {
			
			maxSum = Math.max(maxSum, moneySum);
			return;
		}
		
		int honey = map[startR][startC+depth];
		
		dfs(startR, startC, depth+1, honeySum, moneySum);
		
		if(honeySum + honey <= C) {
			dfs(startR, startC, depth+1, honeySum+honey, moneySum + honey*honey);
		}
	}
	
}