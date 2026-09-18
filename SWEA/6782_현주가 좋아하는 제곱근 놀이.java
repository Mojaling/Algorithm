import java.io.*;
import java.util.*;

public class Solution {

    /*
     * 제곱근 놀이. 2이상의 정수 N.
     * N을 N+1로 바꿀 수 있고, 루트N이 정수이면 루트 N으로 바꿀 수 있음.
     * 
     * 목표는 N을 2로 만드는 것.
     * 조작해야하는 횟수의 최솟값.
     */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int times = 1; times <= T; times++) {
        	long N = Long.parseLong(br.readLine());//N은 자연수
        	
        	long count = 0;
        	
        	while(N!=2) {
        		long root = (long) Math.sqrt(N);
        		if(root*root == N) {
        			N = root;
        			count++;
        		}else {
        			long next = (root+1)*(root+1);
        			count += next - N;
        			N = next;
        		}
        	}
        	
        	System.out.printf("#%d %d%n",times, count);
        	
        }
    }
}