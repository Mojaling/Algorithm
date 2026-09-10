import java.io.*;
import java.util.*;

public class Solution {
	/*
	 * 높이가 B인 선반
	 * 점원 N명
	 * 점원 키 Hi, 점원들이 쌓는 탑은 점원 1명이상.
	 * 높이가 B 이상인 탑 중에서 높이가 가장 낮은 탑을 알아내려고함.
	 */
	static int N;
	static int B;
	static int[] height;
	static int minResult;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	for(int times=1;times<=T;times++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());//N은 점원 수
    		B = Integer.parseInt(st.nextToken());//B는 탑의 높이
    		height = new int[N];
    		StringTokenizer st1 = new StringTokenizer(br.readLine());
    		for(int i=0;i<N;i++) {
    			height[i] = Integer.parseInt(st1.nextToken());
    		}
    		minResult = Integer.MAX_VALUE;
    		dfs(0, 0);
    		System.out.printf("#%d %d%n",times, minResult-B);
    	}
    }
    
    static void dfs(int Index, int sum) {
    	if(Index==N) {
    		if(sum>=B) {
    			minResult = Math.min(sum, minResult);
    		}
    		return;
    	}
    	
    	dfs(Index+1, sum+height[Index]);
    	dfs(Index+1, sum);
    }
}