// SWEA #1233 · [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD
// Language: Java
// Execution Time: 98 ms
// Memory: 26752 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int times=1;times<=10;times++) {
			int N = Integer.parseInt(br.readLine());//N은 정점의 총 수
			int result = 1;
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				String second = st.nextToken();
				
				if(second.equals("*")||
						second.equals("-")||
						second.equals("+")||
						second.equals("/")) {
					if(st.countTokens()==2) {
						continue;
					}else {
						result=0;
					}
				}else {
					if(st.hasMoreTokens()) {
						result=0;
					}
				}
			}
			System.out.printf("#%d %d%n",times, result);
			
		}
	}
}