// SWEA #5648 · [모의 SW 역량테스트] 원자 소멸 시뮬레이션
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRFInKex8DFAUo
// Language: Java
// Execution Time: 1871 ms
// Memory: 157364 KB

import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 범위가 -1000~1000
	 */
	static int N=4001;
	static int[][] map = new int[N][N];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			int totalSum=0;
			int count = Integer.parseInt(br.readLine());//count는 원자의 개수.
			Deque<Atom> dq = new ArrayDeque<Atom>();
			for(int i=0;i<count;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken())+1000)*2;
				int y = (Integer.parseInt(st.nextToken())+1000)*2;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				map[y][x] = e;
				dq.addLast(new Atom(x, y, dir, e));
			}
			
			while(!dq.isEmpty()) {
				Atom now = dq.pollFirst();
				
				if(map[now.y][now.x]!=now.e) {//한바퀴를 돌고나서 에너지가 다르다는 것은 충돌 했다는 것.
					totalSum+= map[now.y][now.x];
					map[now.y][now.x]=0;
					continue;
				}
				
				map[now.y][now.x] = 0;//기존칸 비우고
				int nx = now.x + dx[now.dir];
				int ny = now.y + dy[now.dir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				
				now.x = nx;
				now.y = ny;
				map[now.y][now.x] += now.e;
				dq.addLast(now);
			}
			
			System.out.printf("#%d %d%n",times, totalSum);
		}
	}
	static class Atom{
		int x, y, dir, e;
		public Atom(int x, int y, int dir, int e) {
			// TODO Auto-generated constructor stub
			this.x=x;
			this.y=y;
			this.dir=dir;
			this.e=e;
		}
	}
}