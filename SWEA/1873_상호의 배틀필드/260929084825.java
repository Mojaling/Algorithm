// SWEA #1873 · 상호의 배틀필드
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc
// Language: Java
// Execution Time: 142 ms
// Memory: 34044 KB

import java.io.*;
import java.util.*;
public class Solution {
	/*
	 * 
	 */
	
	static int H,W;
	static char[][] map;
	static Node tank;
	static int N;
	static char[] commands;
	
	static int[] dr = {0,-1,0,1};
	static int[] dc = {-1,0,1,0};
	static class Node{
		int r, c, dir;
		public Node(int r, int c, int dir) {
			// TODO Auto-generated constructor stub
			this.r=r;
			this.c=c;
			this.dir=dir;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());//H는 행의 길이
			W = Integer.parseInt(st.nextToken());//W는 열의 길이
			map = new char[H][W];
			for(int r=0;r<H;r++) {
				String line = br.readLine();
				for(int c=0;c<W;c++) {
					map[r][c] = line.charAt(c);
					if(map[r][c]=='<') {
						tank = new Node(r,c,0);
					}else if(map[r][c]=='^') {
						tank = new Node(r,c,1);
					}else if(map[r][c]=='>') {
						tank = new Node(r,c,2);
					}else if(map[r][c]=='v') {
						tank = new Node(r,c,3);
					}
				}
			}
			N = Integer.parseInt(br.readLine());//N은 입력 개수
			commands = new char[N];
			String line2 = br.readLine();
			for(int i=0;i<N;i++) {
				commands[i] = line2.charAt(i);
			}
			
			for(char x : commands) {
				if(x=='S') {
					shoot(tank);
				}else {
					move(x, tank);
				}
			}
			
			System.out.print("#"+tc+" ");
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	static void shoot(Node tank) {
		int r = tank.r;
		int c = tank.c;
		int d = tank.dir;
		
		int nr = r + dr[d];
		int nc = c + dc[d];
		while(nr>=0 && nr<H && nc>=0 && nc<W) {
			if(map[nr][nc]=='#') {//철벽에 부딪히면 끝.
				return;
			}else if(map[nr][nc]=='*') {//벽돌에 부딪힐시
				map[nr][nc] = '.';
				return;
			}
			nr+=dr[d];
			nc+=dc[d];
		}
		return;
	}
	
	static void move(char dir, Node tank) {
		int r = tank.r;
		int c = tank.c;
		
		if(dir=='L') {
			map[r][c]='<';
			int nr = r + dr[0];
			int nc = c + dc[0];
			if(nc>=0 && map[nr][nc]=='.') {
				map[r][c]='.';
				tank.c=nc;
				map[nr][nc]='<';
			}
			tank.dir=0;
		}else if(dir=='U') {
			map[r][c]='^';
			int nr = r + dr[1];
			int nc = c + dc[1];
			if(nr>=0 && map[nr][nc]=='.') {
				map[r][c]='.';
				tank.r=nr;
				map[nr][nc]='^';
			}
			tank.dir=1;
		}else if(dir=='R') {
			map[r][c]='>';
			int nr = r + dr[2];
			int nc = c + dc[2];
			if(nc<W && map[nr][nc]=='.') {
				map[r][c]='.';
				tank.c=nc;
				map[nr][nc]='>';
			}
			tank.dir=2;
		}else if(dir=='D') {
			map[r][c]='v';
			int nr = r + dr[3];
			int nc = c + dc[3];
			if(nr<H && map[nr][nc]=='.') {
				map[r][c]='.';
				tank.r=nr;
				map[nr][nc]='v';
			}
			tank.dir=3;
		}
	}
}
