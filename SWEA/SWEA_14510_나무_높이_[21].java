import java.util.*;
import java.io.*;
/*
 * 각 나무가 얼마나부족한지
 * 2최대한 넣어보기
 * 홀수인건 반드시 1로처리
 * 1이 넘 적으면 2를 1로 바꿈
 */
public class Solution {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	for(int times=1; times<=T;times++) {
    		int N = Integer.parseInt(br.readLine());
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int []trees = new int[N];
    		for(int i=0;i<N;i++) {
    			trees[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		int max = Integer.MIN_VALUE;
    		for(int i=0; i<N;i++) {
    			max = Math.max(trees[i], max);
    		}
    		
    		for(int i=0;i<N;i++) {
    			trees[i] = max - trees[i];
    		}
    		
    		int twoCount = 0;
    		int oneCount = 0;
    		
    		for(int i=0;i<N;i++) {
    			while(trees[i]>=2) {
    				trees[i] = trees[i]-2;
    				twoCount++;
    			}
    			if(trees[i]==1) {
    				oneCount++;
    			}
    		}//2를 최대한하고, 1을 함.
    		int days = 0;
    		if(twoCount>=oneCount) {
    			days = twoCount*2;
    		}else {
    			days = oneCount*2-1;
    		}
    		while (twoCount>0) {
    			twoCount = twoCount -1;
    			oneCount = oneCount +2;
    			int now_days = 0;
    			if(twoCount>=oneCount) {
        			now_days = twoCount*2;
        		}else {
        			now_days = oneCount*2-1;
        		}
    			days = Math.min(days, now_days);
    		}
    		System.out.printf("#%d %d%n",times, days);
    		
    	}
    	
    }
}