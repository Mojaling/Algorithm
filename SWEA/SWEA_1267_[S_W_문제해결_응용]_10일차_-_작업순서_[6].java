import java.util.*;
import java.io.*;
/*
 * 해야할 V 개의 작업. 선행 관계있음.
 * 일을 끝낼 수 있는 작업 순서 찾기. 여러개여도 하나만 제시하면됨.
 */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int times = 1; times <= 10; times++) {//10개의 테스트 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); //V는 정점의 개수
			int E = Integer.parseInt(st.nextToken()); //E는 간선의 개수
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			boolean[][] connected = new boolean[V+1][V+1]; //정점은 1~V까지.
			int[] indegree = new int[V+1];
			while(st1.hasMoreTokens()) {//간선 배치.
				int from = Integer.parseInt(st1.nextToken());
				int to = Integer.parseInt(st1.nextToken());
				connected[from][to] = true;
				indegree[to]++;//indegree가 0이 아니라는것은, 먼저 해야할것들이 있다는것.
			}
			Queue<Integer> queue = new ArrayDeque<>();
			for(int i=1; i<=V;i++) {//바로 시작할 수 있는 애들 queue에 장전.
				if(indegree[i]==0) {
					queue.offer(i);
				}
			}
			List<Integer> result = new ArrayList<>();//작업 수행 순서
			while(!queue.isEmpty()) {
				int now = queue.poll();
				
				result.add(now);
				
				for(int next = 1; next<=V;next++) {
					if(connected[now][next]) {
						indegree[next]--;
						
						if(indegree[next] == 0) {
							queue.offer(next);
						}
					}
				}
			}
			System.out.print("#"+times+" ");
			for(int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}