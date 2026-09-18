import java.io.*;
import java.util.*;

public class Solution {

    /*
     * 1~n 까지의 집합.
     * 합집합 연산, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산
     * 
     * n: 1~1백만, m:1~10만.
     * 
     * 합집합 : 0 a b. a가 포함된 집합, b가 포함된 집합을 합침.
     * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산 : 1 a b -> a와 b가 같은 집합에 포함되어 있는지.
     * 1이 나올때마다 결과로 같이있으면 1, 아니면 0으로 표현.
     */
	
	/*
	 * 
	 */
	static int[] parent;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int times = 1; times <= T; times++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());//N은 1~N까지의 집합.
        	int M = Integer.parseInt(st.nextToken());//M은 연산의 개수.
        	parent = new int[N+1];
        	for(int i=1;i<=N;i++) {//자기 자신을 부모로 하기.
        		parent[i] = i;
        	}
        	String result="";
        	
        	for(int i=0;i<M;i++) {
        		st = new StringTokenizer(br.readLine());
        		int how = Integer.parseInt(st.nextToken());
        		int first = Integer.parseInt(st.nextToken());
        		int second = Integer.parseInt(st.nextToken());
        		
        		if(how ==0) {
        			union(first, second);
        		}else {
        			if(find(first)==find(second)) {
        				result+=1;
        			}else {
        				result+=0;
        			}
        		}
        	}
        	System.out.printf("#%d %s%n",times, result);
        	
        	
        }
    }
    
    static int find(int x) {
    	if (parent[x]==x) {
    		return x;
    	}
    	
    	return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b) {
    	
    	int rootA = find(a);
    	int rootB = find(b);
    	
    	if(rootA !=rootB) {
    		parent[rootB] = rootA;
    	}
    }
}