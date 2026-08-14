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
			int N = sc.nextInt();//N은 볶을 수 있는 개수
			int M = sc.nextInt();//M은 총 바구니 개수
			
			List<Integer> nums = new ArrayList<Integer>();
			for(int i=0; i<M; i++) {
				nums.add(sc.nextInt());
			}
			
			// {바구니 번호, 현재 수분량}
            Queue<int[]> queue = new ArrayDeque<>();
			
            for (int i = 0; i < N; i++) {
                queue.offer(new int[] {i + 1, nums.get(i)});
            }
            
            // 다음에 넣을 바구니의 인덱스
            int next = N;
			
			while (queue.size()!=1) {//queue에 하나가 남으면 종료
				int[] basket = queue.poll();
				
				basket[1] /= 2;
				if (basket[1] == 0) {
                    // 아직 대기 중인 바구니가 있다면
                    if (next < M) {
                        queue.offer(new int[] {next + 1, nums.get(next)});
                        next++;
                    }
				}else {
                    // 아직 수분이 남았다면 다시 뒤로
                    queue.offer(basket);    
                }
			}
			// 마지막 남은 바구니의 번호
            int result = queue.peek()[0];
            
            System.out.printf("#%d %d%n", times, result);
		}
	}

}