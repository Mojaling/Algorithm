package solution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Solution {
	/*
	 * 상자들이 한줄.
	 * 맞붙어 있는 두 상자에 적힌 글자가 같으면, 같이 내려감.
	 * 나머지들은 붙게됨.
	 * 묶음처리 끝난뒤 남은 상자갯수.
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int times=1; times<=T;times++) {
			Deque<Character> stack = new ArrayDeque<Character>();
			String boxes = sc.next();
			
			for(char x : boxes.toCharArray()) {
				if (!stack.isEmpty() && stack.peek() == x) {
					stack.pop();
				}else {
					stack.push(x);
				}
			}
			System.out.printf("#%d %d%n",times, stack.size());
			
		}
	}
		

}