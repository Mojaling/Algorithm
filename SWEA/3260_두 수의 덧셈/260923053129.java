// SWEA #3260 · 두 수의 덧셈
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBC1lOad9IDFAWr
// Language: Java
// Execution Time: 98 ms
// Memory: 27776 KB

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Solution{
	/*
	 * 
	 */
	
	static int N, M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String first = st.nextToken();
			String second = st.nextToken();
			
			BigDecimal bigFirst = new BigDecimal(first);
			BigDecimal bigSecond = new BigDecimal(second);
			
			BigDecimal result = bigFirst.add(bigSecond);
			System.out.println("#"+times+" "+result);
		}
	}
}