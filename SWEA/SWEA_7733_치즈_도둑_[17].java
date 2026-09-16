import java.io.*;
import java.util.*;

public class Solution{
	/*
	 * 한변의 길이 N
	 * 100일동안 먹음. X번째날, 맛있는 정도가 X인 칸을 먹음.
	 * 먹은 칸이 벽이라할때, 나뉜 덩어리.
	 * 덩어리가 가장 많을 때의 덩어리 구하기.
	 */
	
	/*
	 * 1일 -> 1다 먹음, 2일-> 2다 먹음.
	 * 먹었을경우 다 0으로 바꾸고, 그것을 벽처럼 쓰자.
	 * 섬의 개수를 따로 빼서, 매번 가장 큰 수가 나오게끔 하자.
	 */
	
	/*
	 * 1.탐색이 되지 않은 미지의 땅 찾기.
	 * 2. 인접한땅 따라 탐색(방문관리)
	 * 
	 * 1일차에 탐색중 ==1을 만나게 된다면 그것을 0으로 만듬.
	 * 그럼 남은 숫자들은 1보다 다 큰 숫자를 가질 것임.
	 * 돌다가 1보다 큰 숫자를 만나면 0으로 만들면서 섬의 수를 세고
	 * 다음에 원복을 하고
	 * 다시 2일차로 돌아가야하나? 
	 */
	static int N;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int times=1;times<=T;times++) {
			N = Integer.parseInt(br.readLine());//N은 한변의 길이.
			map = new int[N][N];
			for(int i=0;i<N;i++) {//map 배열.
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxResult = 1;
			for(int day=1;day<=100;day++) {//100일 동안에
				visited = new boolean[N][N];
				int count =0;
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(map[r][c]==day) {
							map[r][c] = 0;//해당 날짜와 같은 taste는 0인 벽으로 변환.
							visited[r][c] = true;//방문할 필요없음을 나타냄.
						}
					}
				}//0인 벽으로 전환 완료.
				
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(map[r][c]!=0 && !visited[r][c]) {//미지의 땅
							count++;//새로운 섬의 시작이므로 카운팅
							dfs(r, c);
						}
					}
				}
				maxResult = Math.max(maxResult, count);
			}
			System.out.println("#"+times+" "+maxResult);
		}
	}
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	private static void dfs(int r, int c) {
		visited[r][c] = true;//방문 막기.
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
			if(map[nr][nc]!=0) {
				dfs(nr, nc);
			}
		}
		
	}
}