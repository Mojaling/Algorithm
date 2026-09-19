import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 1~N 구역. 국경 맞닿은 곳있음.
	 * 색상 모두 K가지. 인접한 두 구역은 다른 색으로.
	 * 
	 * 
	 */
	static int N;
	static int M;
	static int K;
	static int[] colors;
	static boolean[][] connected;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1; times<=T;times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 구역의 수
			M = Integer.parseInt(st.nextToken());//M은 인접 관계의수
			K = Integer.parseInt(st.nextToken());//K는 색의 수
			
			colors = new int[N+1];//색 안칠한건 0이라 생각하기.
			count = 0;
			connected = new boolean[N+1][N+1];//1~N
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				connected[from][to]=true;
				connected[to][from]=true;
			}
			
			dfs(1);
			System.out.printf("#%d %d%n",times, count);
		}
	}
	
	static void dfs(int index) {

	    if (index == N + 1) {
	        count++;
	        return;
	    }

	    for (int color = 1; color <= K; color++) {

	        boolean possible = true;

	        for (int i = 1; i <= N; i++) {

	            if (connected[index][i] || connected[i][index]) {

	                if (colors[i] == color) {
	                    possible = false;
	                    break;
	                }
	            }
	        }

	        if (possible) {

	            colors[index] = color;

	            dfs(index + 1);

	            colors[index] = 0;
	        }
	    }
	}
	
}