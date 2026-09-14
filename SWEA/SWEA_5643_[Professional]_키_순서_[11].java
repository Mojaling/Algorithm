import java.io.*;
import java.util.*;

public class Solution {

    /*
     * 1~N번. 키 비교 결과 일부.(모두 키 다름)
     * 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇명인가.
     * 
     */
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int times = 1; times <= T; times++) {
           int N = Integer.parseInt(br.readLine());//N은 1~N명.
           int M = Integer.parseInt(br.readLine());//M은 간선의 수
           boolean[][] connected = new boolean[N+1][N+1];
           
           for(int i=0;i<M;i++) {
        	   StringTokenizer st = new StringTokenizer(br.readLine());
        	   int from = Integer.parseInt(st.nextToken());
        	   int to = Integer.parseInt(st.nextToken());
        	   connected[from][to] = true;
           }
           
           for(int k=1;k<=N;k++) {
        	   for(int i=1;i<=N;i++) {
        		   for(int j=1;j<=N;j++) {
        			   if(connected[i][k] && connected[k][j]) {
        				   connected[i][j] = true;
        			   }
        		   }
        	   }
           }
           int result =0;
           for(int i=1;i<=N;i++) {
        	   int count=0;
        	   for(int j=1;j<=N;j++) {
        		   if(connected[j][i]) {
        			   count++;
        		   }else if(connected[i][j]) {
        			   count++;
        		   }
        	   }
        	   if(count==N-1) {
        		   result++;
        	   }
           }
           System.out.printf("#%d %d%n",times, result);
        }
    }
}