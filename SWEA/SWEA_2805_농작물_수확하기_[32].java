import java.io.*;
import java.util.*;

public class Solution {
	/*
	 * N*N 크기의 농장
	 * 크기 홀수, 정사각형 마름모 형태로 수확.
	 * 
	 */
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	for(int times=1;times<=T;times++) {
    		int N = Integer.parseInt(br.readLine());
    		int[][] map = new int[N][N];
    		for(int i=0;i<N;i++) {
    			String line = br.readLine();
    			for(int j=0;j<N;j++) {
    				map[i][j]= line.charAt(j) - '0';
    			}
    		}
    		int mid = N/2;
    		int sum=0;
    		
    		for(int r=0;r<N;r++) {
    			int range = mid - Math.abs(mid - r);
    			
    			for(int c=mid - range; c<=mid+range;c++) {
    				sum+=map[r][c];
    			}
    		}
    		
    		System.out.printf("#%d %d%n",times, sum);
    	}
    }
}