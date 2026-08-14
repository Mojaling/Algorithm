package solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	/*
	 * N개의 바구니 원형, N개의 생두 바구니 볶을 수 있음
	 * 볶여 나오는 시간 제각각
	 * 1~M번까지 번호 매겨진 M개의 생두 바구니
	 * 한바퀴 돌때마다 /2(버림)
	 * 0되면빠지고 이어서 바로 들어옴.
	 * 마지막까지 남은 바구니의 번호.
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int times=1; times<=T;times++) {
			int N = sc.nextInt();//자리수 N
			int M = sc.nextInt();// 총 바구니 개수 M
			
			int[] nums = new int[M];
			for(int i=0;i<M;i++) {
				nums[i] = sc.nextInt();
			}
			
			Queue<int []> queue = new ArrayDeque<int[]>();
			//각각의 index를 저장하기 위해 배열 구조 저장.
			
			
			for(int i=0; i<N;i++) {
				queue.offer(new int[] {i+1, nums[i]});
			}//queue 초반 세팅.
			//문제 내에서 1번 부터 쓰기에 1이라 정의.
			int next = N;
			while(queue.size()!=1) {//하나가 남을때까지.
				int[] now = queue.poll();
				//now[0]은 index, now[1]은 남은숫자.
				now[1] = now[1] / 2;
				
				if(now[1]==0) {
					if(next<M) {//next=M-1까지 가능.
						queue.offer(new int[] {next+1, nums[next]});
						next++;
						//남은 숫자가 0이라면
						//N번째 nums를 넣고 +1 처리를해둠.
					}else {
						continue;
					}//N>M이면 더 넣을것이 없다는것. 패스.
				}else {
					queue.offer(now);
				}
			}
			
			int[]result = queue.poll();
			System.out.printf("#%d %d%n",times, result[0]);
		}
	}

}