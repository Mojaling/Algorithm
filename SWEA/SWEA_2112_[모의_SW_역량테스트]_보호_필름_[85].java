import java.util.*;
import java.io.*;

public class Solution {
	/*
	 * 두께 D, 가로 크기 W의 보호 필름(투명한 막 D장을 쌓음, W개 붙여서 만듬)
	 * 각 셀들은 특성 A / B를 가지고 있음. A=0, B=1
	 * 합격기준 K, 충격은 세로 방향.
	 * 동일한 특성의 셀들이 K개 이상 연속 -> 통과
	 * 약품 투여 가능. 투입하는 막의 모든 셀들 하나의 특성으로 변경.(가로)
	 * 약품 투입 횟수 최소로하여 성능검가 통과하는 방법찾고, 약품 투입 횟수 출력
	 */
	
	/*
	 * 어떤 순서로 풀수 있을까?
	 * 1.주어진 것에 대해서 세로줄로 검사를 함.
	 * (stack 형식으로 맨앞꺼 넣고, 다른애가 오면 pop후에 stack, 같은애가 오면 그냥 count)
	 * 2.K넘는지를 확인. 하나라도 불만족할경우 패스.
	 * (count가 K와 같아질 경우 다음라인 체크)
	 * 3.위에서부터 한줄씩 A or B 투여. 다시 K넘는지 확인. 하나라도 불만족할때마다 패스.
	 * 4.최대 K개까지 넣을 수 있겠다.
	 */
	static int[][] map;
	static int D;
	static int W;
	static int K;
	static boolean[] clear;
	static int minResult;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times =1;times<=T;times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());//D는 두께(세로로 몇개 쌓냐)
			W = Integer.parseInt(st.nextToken());//W는 길이(가로로 몇개 붙이냐)
			K = Integer.parseInt(st.nextToken());//K는 합격기준
			map = new int[D][W];
			for(int i=0;i<D;i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(st1.nextToken());
				}
			}
			minResult = K;
			dfs(0,0);
			System.out.printf("#%d %d%n",times, minResult);
		}
	}
	
	//1.각 줄 마다 세로줄로 검사를 함.
//	static void stack(int c) {
//		if(c==W) {//c는 W-1까지 존재.
//			return;
//		}
//		int nowR = 0;
//		int nowC = c;
//		int count =0;
//		Queue<Integer> stack = new ArrayDeque<Integer>();
//		stack.add(map[nowR][nowC]);
//		count++;
//		for(int i=1;i<D;i++) {
//			if(count==K) {//count와 K가 같아질경우
//				clear[nowC] = true;//해당 열 통과
//				nowC++;
//				stack(nowC);
//				return;//종료
//			}
//			if(stack.peek().equals(map[i][nowC])) {//이미 넣은거와 같은 애일경우
//				count++;
//				continue;
//			}else {//다른 거일 경우
//				stack.poll();
//				stack.offer(map[i][nowC]);
//				count = 1;
//			}
//		}
//	}
	//1.각 줄 마다 세로줄로 검사를 함.
	static boolean check() {
		if(K == 1) {
			return true;
		}
		for(int c=0;c<W;c++) {
			boolean pass = false;
			int count = 1;
			for(int r = 1; r<D; r++) {
				if(map[r][c] == map[r-1][c]) {
					count++;
				}else {
					count = 1;
				}
				if(count == K) {
					pass = true;
					break;
				}
			}
			if(!pass) {
				return false;
			}
		}
		return true;
	}
	//3.위에 줄서부터 A,B, 암것도 안할지 고르기.
	static void dfs(int row, int count) {
		if(count >= minResult) {//count가 K보다 크다면 계산할 필요가없음.
			return;
		}
		if(row == D) {
			if(check()) {
				minResult = Math.min(minResult, count);
			}
			return;
		}
		dfs(row+1, count);
		
		int[] backup = map[row].clone();
		
		Arrays.fill(map[row], 0);
		dfs(row + 1, count + 1);
		
		Arrays.fill(map[row], 1);
		dfs(row + 1, count + 1);
		
		map[row] = backup;//선택이 끝난후 원상복구
	}
}
