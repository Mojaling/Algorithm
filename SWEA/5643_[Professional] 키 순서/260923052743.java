// SWEA #5643 · [Professional] 키 순서
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo
// Language: Java
// Execution Time: 1865 ms
// Memory: 100156 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 
	 */
	
	static int N, M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			N = Integer.parseInt(br.readLine());//N은 학생 수. (1~N)
			M = Integer.parseInt(br.readLine());//M은 비교 횟수.
			boolean[][] connected = new boolean[N+1][N+1];
			int[] counts = new int[N+1];
			
			for(int i=0;i<M;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				connected[from][to] = true;
			}
			
			for(int k=1;k<=N;k++) {
				for(int i=1;i<=N;i++) {
					for(int j=1;j<=N;j++) {
						if(connected[i][k]&&connected[k][j]) {
							connected[i][j] = true;
						}
					}
				}
			}
			int result=0;
			for(int nowIndex=1;nowIndex<=N;nowIndex++) {
				
				for(int nextIndex=1;nextIndex<=N;nextIndex++) {
					
					if(connected[nowIndex][nextIndex] || connected[nextIndex][nowIndex]) {
						counts[nowIndex]++;
					}
				}
			}
			
			for(int i=1;i<=N;i++) {
				if(counts[i]==N-1) result++;
			}
			
			System.out.printf("#%d %d%n",times, result);
		}
	}
}