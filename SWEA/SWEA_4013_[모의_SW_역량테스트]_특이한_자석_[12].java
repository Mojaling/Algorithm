import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 4개의 자석. 각 자석은 8개의 날. 각 날 마다 N,S극.
	 * 임의의 자석 1칸씩 K번 회전
	 * 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만 반대 방향으로 1칸 회전.
	 * 
	 * 빨간 화살표가 S극일때 1,2,4,8점 획득.
	 * S:1, N:0
	 * 
	 * K번 회전시킨 후 획득하는 점수의 총 합
	 */
	
	/*
	 * 회전이니까... 각각을 dq 형식으로 받아서 회전할때마다 뽑고 뒤에 두고 해야하나?
	 * 빨간화살표를 맨앞이라 하고, 시계방향으로 index를 하나씩 늘려나가는...
	 */
	static int K;
	static int[] rotateDir;
	static List<Deque<Integer>> magnets;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			//K는 회전시키는 횟수
			K = Integer.parseInt(br.readLine());
			//4개의 자석 정보
			magnets = new ArrayList<>();
			StringTokenizer st;

			for (int num = 0; num < 4; num++) {
			    Deque<Integer> dq = new ArrayDeque<>();
			    st = new StringTokenizer(br.readLine());

			    for (int i = 0; i < 8; i++) {
			        dq.offerLast(Integer.parseInt(st.nextToken()));
			    }

			    magnets.add(dq);
			}
			//K개의 자석 회전 정보(1:시계방향, -1:반시계)
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int number = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				rotateDir = new int[5];//자석 번호 1~4 사용.
				check(number, dir);
				
				for(int num=1;num<=4;num++) {
					Deque<Integer> dq = magnets.get(num-1);
					
					if(rotateDir[num]==1) {
						int a = dq.pollLast();
						dq.offerFirst(a);
					}
					if(rotateDir[num]==-1) {
						int a = dq.pollFirst();
						dq.offerLast(a);
					}
				}
			}
			int sum=0;
			for(int num=1;num<=4;num++) {
				sum+=(int)Math.pow(2,num-1) * magnets.get(num-1).peekFirst();
			}
			System.out.printf("#%d %d%n",tc, sum);
		}
	}
	static void check(int number, int dir) {
		rotateDir[number] = dir;//현재 방향 기록
		if(number>1 && rotateDir[number-1]==0) {
			if(getPole(number,6)!=getPole(number-1,2)) {//왼쪽방향으로 볼때, 서로 다름
				check(number-1,dir*-1);
			}
		}
		if(number<4 && rotateDir[number+1]==0) {
			if(getPole(number,2)!=getPole(number+1,6)) {//오른쪽방향으로 볼때, 서로 다름
				check(number+1,dir*-1);
			}
		}
	}
	
	static int getPole(int num, int index) {
	    return magnets.get(num - 1).toArray(new Integer[0])[index];
	}
	
}