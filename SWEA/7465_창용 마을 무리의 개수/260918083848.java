// SWEA #7465 · 창용 마을 무리의 개수
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
// Language: Java
// Execution Time: 134 ms
// Memory: 28800 KB

import java.io.*;
import java.util.*;

public class Solution {

    /*
     * 마을에 N명.1~N번.
     * 아는 관계라면 무리. 창용 마을에 몇개의 무리가 존재하는가?
     * 
     */
	static int[] parent;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int times = 1; times <= T; times++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());//N은 사람수. 1~N.
        	int M = Integer.parseInt(st.nextToken());//M은 서로를 알고 있는 사람 관계수.
        	parent = new int[N+1];
        	for(int i=1;i<=N;i++) {
        		parent[i] = i;
        	}
        	
        	for(int i=0;i<M;i++) {
        		st = new StringTokenizer(br.readLine());
        		int first = Integer.parseInt(st.nextToken());
        		int second = Integer.parseInt(st.nextToken());
        		
        		union(first, second);
        	}
        	Set<Integer> count = new HashSet<>();
        	for(int i=1;i<=N;i++) {
        		count.add(find(i));
        	}
        	System.out.printf("#%d %d%n",times, count.size());
        }
    }
    
    static int find(int x) {
    	if(parent[x]==x) {
    		return x;
    	}
    	
    	return parent[x] = find(parent[x]);
    }
    
	static void union(int a, int b) {

		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			parent[rootB] = rootA;
		}

	}
}