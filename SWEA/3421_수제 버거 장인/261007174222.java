// SWEA #3421 · 수제 버거 장인
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWErcQmKy6kDFAXi
// Language: Java
// Execution Time: 269 ms
// Memory: 27900 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 재료 N가지. (1~N)
	 * 궁합이 맞지 않으면 동시 사용 x.
	 * M개 쌍에 대한 정보.
	 * 몇 가지 종류의 버거?
	 */
	static int N,M;
	static boolean[] visited;
	static List<int[]> list;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //N은 재료개수
			M = Integer.parseInt(st.nextToken());//M은 쌍의 개수.
			list = new ArrayList<int[]>();
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				list.add(new int[] {first, second});
			}
			visited = new boolean[N+1];//1~N개.
			result=0;
			dfs(1);
			System.out.printf("#%d %d%n",tc, result);
		}
	}
	
	static void dfs(int count) {
		if(count==N+1) {
			for(int i=0;i<list.size();i++) {
				int[] now = list.get(i);
				int first = now[0];
				int second = now[1];
				
				if(visited[first] && visited[second]) {
					return;
				}
			}
			result++;
			return;
		}
		
		dfs(count+1);
		visited[count]=true;
		dfs(count+1);
		visited[count]=false;
	}
	
}