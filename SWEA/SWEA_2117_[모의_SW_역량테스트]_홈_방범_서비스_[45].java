import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * N*N크기, 마름모 모양에 제공.
	 * 운영 비용 : K*K+(K-1)*(K-1)
	 * 제공받는 집들은 각각 M의 비용을 제공.
	 * 손해를 보지 않으면서 홈방법 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾고
	 * 집들의 수를 출력
	 */
	
	/*
	 * 1. 마름모의 중심위치, 크기
	 * 2. 마름모는 맨해튼 거리를 통해관리
	 * 3. K는 map 중앙에서 map을 다 덮을 수 있을크기면됨.
	 * 4. 하나의 중심에서 해야할일 ->영역안에 집의 수, 손해아닌지 확인, 만족시 집수의 최댓값 갱신 
	 */
	static int N, M;
	static int K;
	static int[][] map;
	static List<Loc> house;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//N은 map의 크기.
			K = N+1;
			M = Integer.parseInt(st.nextToken());//M은 집에서 내주는 비용.
			house = new ArrayList<>();
			map = new int[N][N];
			for(int r=0;r<N;r++) {//map 배열, house 좌표 받아두기
				st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c]==1) {
						house.add(new Loc(r,c));
					}
				}
			}
			result = 0;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					for(int k=1;k<=K;k++) {
						int cnt = count(r, c, k);
						if(cnt*M - cost(k)>=0) {
							result = Math.max(result, cnt);
						}
					}
				}
			}
			System.out.printf("#%d %d%n",tc, result);
		}
	}
	static int count(int r, int c, int k) {
		int cnt=0;
		for (Loc h : house) {
			int dist = Math.abs(h.r-r) + Math.abs(h.c-c);
			if(dist<k) cnt++;
		}
		return cnt;
	}
	static int cost(int k) {
		return k*k+(k-1)*(k-1);
	}
	static class Loc{
		int r,c;
		public Loc(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
}