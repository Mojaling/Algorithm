// SWEA #1267 · [S/W 문제해결 응용] 10일차 - 작업순서
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18TrIqIwUCFAZN
// Language: Java
// Execution Time: 138 ms
// Memory: 49496 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 앞에 일을 끝내야 뒤의 일을 할 수 있음.
	 * 일을 끝낼 수 있는 작업 순서.
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=10;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());//정점의 개수
			int E = Integer.parseInt(st.nextToken());//간선의 개수
			boolean[][] connected = new boolean[V+1][V+1];
			int[] indegree = new int[V+1];//1~V.
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<E;i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				connected[from][to] = true;
				indegree[to]++;
			}
			
			Deque<Integer> dq = new ArrayDeque<>();
			for(int i=1;i<=V;i++) {
				if(indegree[i]==0) {
					dq.offer(i);
				}
			}
			String result="";
			while(!dq.isEmpty()) {
				int now = dq.poll();
				result+=now+" ";
				
				for(int next=1;next<=V;next++) {
					if(connected[now][next]) {//둘이 연결되어 있을경우
						indegree[next]--;
						
						if(indegree[next]==0) {
							dq.offer(next);
						}
					}
				}
			}
			
			System.out.printf("#%d %s%n",tc, result);
		}
	}
}