import java.io.*;
import java.util.*;

public class Solution{
	
	static int N = 4001;
	static int[][] map = new int[N][N];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int times=1;times<=T;times++) {
			int totalEnergy = 0; //누적
			int count = Integer.parseInt(br.readLine()); //원자 수
			ArrayDeque<Unit> dq = new ArrayDeque<>(); //살아있는 원자들 리스트
			
			for(int i=0;i<count;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				//음수 보정 및 0.5초 단위 충돌 대응을 위한 2배처리
				//-1000~1000 -> 0~2000 -> 0~4000
				int x = (Integer.parseInt(st.nextToken())+1000) << 1;
				int y = (Integer.parseInt(st.nextToken())+1000) << 1;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				map[y][x] = e;//그 위치에 있는 원자들이 가진 에너지를 누적
				dq.addLast(new Unit(x, y, dir, e));
			}
			
			//살아있는 원자들을 이동처리.
			
			while(!dq.isEmpty()) {
				Unit cur = dq.pollFirst();
				
				//이게 왜 되는건지 헷갈리네...
				if(map[cur.y][cur.x]!=cur.e) {//이 위치에서 충돌
					totalEnergy += map[cur.y][cur.x];
					map[cur.y][cur.x] = 0;
					continue;
				}
				
				// 이동, 현위치에서 원자의 방향으로 다음 위치 계산
				map[cur.y][cur.x] = 0;
				int nx = cur.x + dx[cur.dir];
				int ny = cur.y + dy[cur.dir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				
				cur.x = nx;
				cur.y = ny;
				map[cur.y][cur.x] += cur.e;
				
				dq.addLast(cur);
			}
			
			System.out.printf("#%d %d%n",times, totalEnergy);
		}
	}
	
	static class Unit{
		int x, y, dir, e;
		
		public Unit(int x, int y, int dir, int e) {
			this.x=x;
			this.y=y;
			this.dir = dir;
			this.e = e;
		}
	}
}