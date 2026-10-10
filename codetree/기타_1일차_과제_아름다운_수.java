import java.io.*;
import java.util.*;

public class Main{
	/*
	 * 1~4.
	 * 숫자가 연속으로 그만큼 나오게끔.
	 * +그 연속이 나누기가 된다면 ok.
	 * N자리에 아름다운 숫자 몇개?
	 * N은 1~10
	 */
	static int N;
	static int[] arr;
	static Deque<Integer> dq;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];//1자리~ N자리까지.
		answer = 0;
		dfs(1);
		System.out.println(answer);
		
	}
	
	static void dfs(int depth) {//N자리 자연수 만들기.
		if(depth==N+1) {//1~N까지 다 골랐을때.
			//아름다운 수가 되기 위한 조건이 들어감.
			int[] now = arr.clone();
			dq = new ArrayDeque<Integer>();
			for(int i=1;i<now.length;i++) {
				dq.offer(now[i]);
			}
			boolean check = true;
			while(!dq.isEmpty()) {
				int first = dq.pollFirst();
				if(isOK(first)) {
					continue;
				}else {
					check = false;
				}
			}
			if(check) {
				answer++;
			}
			return;
		}
		
		for(int i=1;i<=4;i++) {
			arr[depth] = i;
			dfs(depth+1);
		}
	}
	static boolean isOK(int num) {
		if(num ==1) {
			return true;
		}else {
			for(int i=0;i<num-1;i++) {
				if(dq.isEmpty() || dq.pollFirst()!=num) {
					return false;
				}
			}
			return true;
		}
	}
}