// SWEA #3289 · 서로소 집합
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
// Language: Java
// Execution Time: 2386 ms
// Memory: 138088 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 합집합은 0 a b, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b
	 * 1로 시작하는 입력에 대해 같은 집합에 속해있으면 1, 아니면 0
	 */
	static int[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//N은 1~n까지의 집합.
			int M = Integer.parseInt(st.nextToken());//M은 연간의 개수
			list = new int[N+1];
			for(int i=1;i<=N;i++) {
				list[i]  = i;
			}
			String result="";
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int info = Integer.parseInt(st.nextToken());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				if(info ==0) {
					union(first, second);
				}else {
					if(find(first)==find(second)) {
						result+=1;
					}else {
						result+=0;
					}
				}
			}
			System.out.printf("#%d %s%n",times,result);
		}
	}
	
	static int find(int x) {
		if(list[x] ==x) {
			return x;
		}
		
		return list[x] = find(list[x]);
	}
	
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA != rootB) {
			list[rootB] = rootA;
		}
	}
}