
import java.util.*;
import java.io.*;
/*
 * 
 */
public class Solution {
	public int solution(int N, int number) {
        Set<Integer>[] dp = new Set[9];
        for(int i=1; i<=8;i++) {
        	dp[i] = new HashSet<>();
        }
        
        dp[1].add(N);
        if(dp[1].contains(number)) {
        	return 1;
        }
        
        for(int i =2; i<=8;i++) {
        	String value = "";
        	for(int a =0;a<i;a++) {
        		value += String.valueOf(N);
        	}
        	dp[i].add(Integer.parseInt(value));
        	
        	for(int j=i-1;j>=1;j--) {
        		for(int x : dp[j]) {
        			for(int y : dp[i-j]) {
        				dp[i].add(x+y);
                		dp[i].add(x-y);
                		if(y!=0) {
                			dp[i].add(x/y);
                		}
                		dp[i].add(x*y);
        			}
        		}
        	}
        	if(dp[i].contains(number)) {
            	return i;
            }
        }
        
        return -1;
        
    }
    
}