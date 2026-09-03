import java.util.*;
import java.io.*;
/*
 * 과자 2봉지 삼.
 * N개의 과자 봉지, 각 Ai그램 무게
 * 두 봉지의 무게가 M 그램 초과하면 안됨.
 * 무조건 2봉지사야함.
 */

public class Solution {
	static int N;
	static int M;
	static int[] weights;
	static int nowIndex;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int times = 1; times <= T; times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 과자 개수
			M = Integer.parseInt(st.nextToken());//M은 무게 제한
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			weights = new int[N];
			for(int i=0;i<N;i++) {
				weights[i] = Integer.parseInt(st1.nextToken());
			}//무게들 배치.
			boolean second = false;
			int maxResult = Integer.MIN_VALUE;
			for(int i=0;i<N;i++) {
				int result = weights[i];
				for(int j=i+1;j<N;j++) {
					if(result + weights[j]<=M) {
						second = true;
						maxResult = Math.max(maxResult, result + weights[j]);
					}
				}
			}
			if(!(second)) {
				maxResult = -1;
			}
			System.out.printf("#%d %d%n",times, maxResult);
		}
	}
}