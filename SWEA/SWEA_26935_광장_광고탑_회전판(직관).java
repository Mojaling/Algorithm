package solution;

import java.util.ArrayDeque;
import java.util.Deque;
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
			int N = sc.nextInt(); //N은 광고판개수
			int M = sc.nextInt(); //M은 총 회전횟수
			
			int[] nums = new int[N];
			for(int i=0; i<N;i++) {
				nums[i] = sc.nextInt();
			}
			
			int result = M % N;
			System.out.printf("#%d %d%n",times, nums[result]);
			
		}
	}
		

}