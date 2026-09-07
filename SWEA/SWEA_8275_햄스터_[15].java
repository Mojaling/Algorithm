import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 햄스터 우리 N개, 1~N번까지.
	 * 각 우리에 0~X마리
	 * 경근이는 M개의 기록 남김.
	 * l번 우리에서 r번 우리까지 햄스터의 수가 s마리.
	 * 이 기록을 모두 만족하는 햄스터 수 배치
	 * 
	 * 전체 햄스터 수가 가장 많게끔.
	 * 사전순으로 가장 앞선 것 출력.
	 */
	static int N;
	static int X;
	static int M;
	static int[] l;
	static int[] r;
	static int[] s;
	static int[] hamsters;
	static int maxSum;
	static int[] answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times =1;times<=T;times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 우리의 개수
			X = Integer.parseInt(st.nextToken());//X는 각 우리에 있는 최대마리
			M = Integer.parseInt(st.nextToken());//M은 남긴 기록의 개수
			l = new int[M];
			r = new int[M];
			s = new int[M];
			for(int i=0;i<M;i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				l[i] = Integer.parseInt(st1.nextToken());
				r[i] = Integer.parseInt(st1.nextToken());
				s[i] = Integer.parseInt(st1.nextToken());
			}
			maxSum = -1;
			answer = new int[N];
			hamsters = new int[N];
			dfs(0);
			
			System.out.print("#"+times+" ");
			if(maxSum == -1) {
				System.out.println(-1);
			}else {
				for(int x : answer) {
					System.out.print(x+" ");
				}
				System.out.println();
			}
		}
	}
	static void dfs(int depth) {
		if(depth == N) {
			for(int j=0;j<M;j++) {
				int nowSum = 0;
				for(int i = l[j]-1; i<=r[j]-1;i++) {
					nowSum+= hamsters[i];
				}
				if(nowSum != s[j]) {
					return;
				}
			}
			int total = 0;
			for(int i=0;i<N;i++) {
				total += hamsters[i];
			}
			if(total > maxSum) {
				maxSum = total;
				answer = hamsters.clone(); //작은것서부터 했기에 이미 사전순.
			}
			return;
		}
		
		for(int i=0;i<=X;i++) {
			hamsters[depth] = i;
			dfs(depth + 1);
		}
	}

}
