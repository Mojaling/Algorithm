// SWEA #8275 · 햄스터
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWxQ310aOlQDFAWL
// Language: Java
// Execution Time: 1653 ms
// Memory: 28476 KB

/*
 * N개의 햄스터 우리, 1~N.
 * 각 우리에 0<= <=X이하 햄스터.
 * M개의 기록. l번~r번 우리의 햄스터 수를 세었더니 s마리.
 */

/*
 * 햄스터 수가 가장 많게, 사전순으로 가장 앞서게, 만족하는 햄스터 배치 없을시 -1 출력.
 */
import java.io.*;
import java.util.*;
public class Solution {
	static int N,X,M;
	static List<Record> record;
	static int[] hamsters;
	static int maxSum;
	static int[] answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 우리의 개수.
			X = Integer.parseInt(st.nextToken());//X는 우리에 있는 최대마리개수.
			M = Integer.parseInt(st.nextToken());//M은 기록의 개수
			record = new ArrayList<>();
			hamsters = new int[N+1];//1~N번 우리.
			answer = new int[N+1];
			maxSum = -1;
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				record.add(new Record(l,r,s));
			}
			
			dfs(1);//N은 1번부터.
			
			if(maxSum==-1) {//dfs를 거치고도 maxSum의 변화가 없다면
				System.out.printf("#%d %d%n",tc, maxSum);
			}else {//dfs가 정상 작동했다면.
				System.out.print("#"+tc+" ");
				for(int i=1;i<=N;i++) {
					System.out.print(answer[i]+" ");
				}
				System.out.println();
			}
		}
	}
	static void dfs(int depth) {
		if(depth==N+1) {//N번까지 다 고름.
			boolean check = true;//조건을 한번이라도 틀리면 틀린 배열.
			for(int i=0;i<M;i++) {
				Record now = record.get(i);
				int l = now.l;
				int r = now.r;
				int s = now.s;
				int sum = 0;
				for(int j=l; j<=r;j++) {
					sum+=hamsters[j];
				}
				if(sum!=s) {
					check = false;
				}
			}
			if(check) {//조건 모두 만족할때
				int sum=0;
				for(int i=1;i<=N;i++) {
					sum+=hamsters[i];
				}
				if(maxSum < sum) {//사전순을 맞추기 위해 같을때는 재설정을 안함. 작은 숫자부터 들어오기때문.
					maxSum = sum;
					answer = hamsters.clone();
				}
			}
			return;
		}
		
		for(int i=0;i<=X;i++) {
			hamsters[depth]=i;
			dfs(depth+1);
		}
	}
	
	static class Record{
		int l, r, s;
		public Record(int l, int r, int s) {
			// TODO Auto-generated constructor stub
			this.l=l;
			this.r=r;
			this.s=s;
		}
	}
}
