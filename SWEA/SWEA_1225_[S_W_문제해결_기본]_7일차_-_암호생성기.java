import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int i=1; i<=T;i++) {
			int times = sc.nextInt();
			Queue<Integer> queue = new ArrayDeque<Integer>();
			
			for(int j=0; j<8;j++) {
				queue.offer(sc.nextInt());
			}//queue의 초기세팅.
			
			while(true) {
				int num = 1;
				for(int j=0;j<5;j++) {
					int now = queue.poll();
					now = now - num;
					if(now <=0) {
						now = 0;
						queue.offer(now);
						break;
					}else {
						queue.offer(now);
						num++;
					}
				}
				if(queue.contains(0)) {
					break;
				}
			}
			System.out.printf("#%d %d %d %d %d %d %d %d %d%n",times, queue.poll(), queue.poll(), queue.poll(), queue.poll(), queue.poll(), queue.poll(), queue.poll(), queue.poll());
			
		}
	}

}