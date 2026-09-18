// SWEA #27022 · 하나 걸러 수확 최대
// https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AZ87c2Z6yFnHBITH
// Language: Java
// Execution Time: 86 ms
// Memory: 25856 KB

import java.io.*;
import java.util.*;

public class Solution {

    /*
     * N그루 한줄. 인접한 두 나무는 함께 수확할 수 없다. 수확량이 최대가되게.
     */
	static int[] dp;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int times = 1; times <= T; times++) {
        	int N = Integer.parseInt(br.readLine());//N은 나무의 개수.
        	int[] trees = new int[N+4];
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i=0;i<N;i++) {
        		
        		trees[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	dp = new int[N+4];
        	
        	//dp[N] = dp[N-1] 이거나 dp[N-2]+trees[N]
        	dp[0] = trees[0];
        	dp[1] = Math.max(dp[0], trees[1]);
        	
        	for(int i=2;i<N;i++) {
        		dp[i] = Math.max(dp[i-1], dp[i-2]+trees[i]);
        	}
        	
        	System.out.printf("#%d %d%n",times, dp[N-1]);
        }
        
    }
    
    
}