import java.util.*;
import java.io.*;
/*
 * 한줄로 꿴 구슬. 1~1000이하의 숫자
 * 구슬 N개가 한줄. 맨앞 -> 기준값, 첫번째 작업위치
 * 현재 작업위치 M칸 앞-> 새 구슬.(바로앞칸 구슬 + 뒤로 밀려나는 구슬) 거기가 작업위치.
 * 마지막 구슬을 지나면, 남은 칸 수는 맨 앞 구슬부터.
 * 만약 정확히 줄의 끝을 지나 다시 처음에 닿아 뒤로 밀려날 구슬이 없는 경우에는, 마지막 구슬의 숫자와 기준값을 더한 새 구슬을 줄의 맨 뒤에 끼워 넣는다.??
 * 끼워넣기 작업 K번. 마지막숫자서부터 10개를 읽음.
 * 
 */

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		
		for(int times=1; times<=T;times++) {
			int N = sc.nextInt(); //N은 구슬 초기 개
			int M = sc.nextInt(); //M은 세는 칸수(앞으로 가는수)
			int K = sc.nextInt(); // 반복횟수
			List<Integer> nums = new ArrayList<Integer>();
			//사이에 숫자를 넣기에 arraylist가 적합.
			for(int i=0;i<N;i++) {
				nums.add(sc.nextInt());
			}//초기 숫자 배열.
			int now = 0;
			for(int i=0; i<K;i++) {
				now = now + M;
				now = now % nums.size();
				
				if(now == 0) {
					nums.add(nums.get(0)+nums.get(nums.size()-1));
					now = nums.size() - 1;
				}else {
					nums.add(now, nums.get(now-1)+nums.get(now));
				}
			}
			
			System.out.printf("#%d ",times);
			for(int i=nums.size()-1; i>nums.size()-11;i--) {
				if(i==-1) {
					break;
				}
				System.out.printf("%d ",nums.get(i));
			}
			System.out.println();
			
			
			
		}
	}
}