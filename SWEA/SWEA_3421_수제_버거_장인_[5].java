import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 재료 1~N번까지.
	 * 궁합 안 맞는거는 동시에 못 넣음
	 * 궁합 안맞는거 M개 쌍 주어질때, 버거의 종류 몇 가지?
	 */
	
	/*
	 * 1.2의 n승의 모든 경우의 수를 구해둔다.
	 * 2.M에 대하여 조건을 만족하는 케이스를 구한다.
	 */
	static int N;
	static int M;
	static boolean[][] withNo;
	static boolean[] foods;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times =1;times<=T;times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 재료 개수
			foods = new boolean[N+1];//1~N.
			M = Integer.parseInt(st.nextToken());//M은 궁합 안맞는쌍
			withNo = new boolean[N+1][N+1];
			for(int i=0;i<M;i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st1.nextToken());
				int to = Integer.parseInt(st1.nextToken());
				withNo[from][to] = true;
				withNo[to][from] = true;
			}
			count = 0;
			dfs(1);
			System.out.printf("#%d %d%n",times, count);
		}
	}
	
	static void dfs(int depth) {
		if(depth == N + 1) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(withNo[i][j] && foods[i] && foods[j]) {//같이 있으면 안되는애들이 같이 있을때
						return;
					}
				}
			}
			count++;
			return;
		}
		
		foods[depth]=true;
		dfs(depth +1);
		
		foods[depth]=false;
		dfs(depth +1);
	}
}
