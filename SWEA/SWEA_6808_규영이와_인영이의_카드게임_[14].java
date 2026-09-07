import java.util.*;
import java.io.*;
/*
 * 1~18까지 적힌 18장의 카드
 * 9장씩 나누어가지고, 아홉 라운드에 걸쳐 게임 진행.
 * 1장씩 내고, 두 사람이 낸 카드에 적힌 수를 비교해서 점수 계산.
 * 높은 숫자 -> 두 카드에 적힌 수의 합만큼 점수.
 * 낮은 숫자 -> X
 * 총점이 더 높은 사람이 승자, 총점이 같으면 무승부.
 * 규영이 내는 카드의 순서 고정, 인영이가 어떻게 내는지에 따라서 9!
 * 이기는 경우의 수, 지는 경우의 수 구하기.
 */

public class Solution {
	static int winCount;
	static int loseCount;
	static boolean[] visited;
	static int[] cards1;
	static int[] cards2;
	static boolean[] used;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int times = 1; times <= T; times++) {
			cards1 = new int[9];
			visited = new boolean[19];//1~18까지.
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<9;i++) {
				cards1[i] = Integer.parseInt(st.nextToken());
				visited[cards1[i]] = true;
			}
			cards2 = new int[9];
			used = new boolean[9];
			int idx = 0;
			for(int i=1 ; i<=18;i++) {
				if (!visited[i]) {
					cards2[idx++] = i;
				}
			}
			winCount = 0;
			loseCount = 0;
			dfs(0,0);
			System.out.printf("#%d %d %d%n",times, winCount, loseCount);
		}
	}
	
	static void dfs(int round, int score) {
		if(round == 9) {
			if(score >=86) {
				loseCount++;
			}else {
				winCount++;
			}
			return;
		}
		
		for(int i=0;i<9;i++) {
			
			if(used[i]) {
				continue;
			}
			
			used[i] = true;
			
			if(cards2[i] > cards1[round]) {
				dfs(round+1, score + cards1[round] + cards2[i]);
			}else {
				dfs(round+1, score);
			}
			used[i] = false;
		}
	}
}