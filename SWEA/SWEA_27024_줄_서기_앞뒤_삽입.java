import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 맨 앞 맨 뒤에 줄섬
	 * 그 사람이 설위치 c, 번호 id
	 * c=1 맨앞, c=2 맨뒤
	 * 순서대로 id 출력하는 프로그램
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int times=1; times<=T;times++) {
			int N = sc.nextInt(); // N은 사람 수
			Deque<Integer> deque = new ArrayDeque<>();
			for(int i=0;i<N;i++) {
				int c = sc.nextInt();
				int id = sc.nextInt();
				if(c==1) {
					deque.addFirst(id);
				}else {
					deque.addLast(id);
				}
			}
			System.out.printf("#%d ", times);
			for(int i=0;i<N;i++) {
				System.out.print(deque.pollFirst()+" ");
			}
			System.out.println();
		}
			
		
	}

}