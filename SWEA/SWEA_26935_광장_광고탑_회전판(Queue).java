package solution;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	/*
	 * 광고판 N개. 누적 조회수.
	 * M번 회전했을때 정면 광고판?
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int times=1; times<=T;times++) {
			Queue<Integer> queue = new ArrayDeque<>();
			int N = sc.nextInt(); //N은 광고판개수
			int M = sc.nextInt(); //M은 총 회전횟수
			
			for(int i=0;i<N;i++) {
				queue.offer(sc.nextInt());
				//offer->맨뒤에넣기.
			}
			
			for(int i=0;i<M;i++) {
				queue.offer(queue.poll()); //맨앞에꺼 뒤에놓기.
				//poll->맨앞에꺼 꺼내기.
			}
			
			System.out.printf("#%d %d%n",times, queue.peek());
			
		}
	}
		

}