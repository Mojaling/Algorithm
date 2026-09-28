import java.io.*;
import java.util.*;
/*
 * 신청된 회의들 중 가능한 많은 수의 회의가 열리도록 스케줄 배정.
 */

/*
 * dfs로 times에서 하나씩 넣어보기?
 * 그중에서 count가 가장 높은거 고르기.
 */
public class Test2_이원희 {
	static int N;
	static boolean[] visited;
	static int maxCount;
	static List<int[]> times;
	public static void main(String[] args) throws Exception{
		//---------솔루션 코드를 작성하세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());//N은 회의 개수
		visited = new boolean[501];//0~500까지 시간이 있음.
		maxCount = 0;
		times = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			times.add(new int[] {from, to});
		}
		dfs(0,0);
		System.out.println(maxCount);
	}
	
	static void dfs(int depth, int count) {
		if(depth ==N) {
			maxCount = Math.max(maxCount, count);
			return;
		}
		
		
		int[] now = times.get(depth);
		int from = now[0];
		int to = now[1];
		//만약 고른다면 방문 이력이 없어야함.
		if(isOK(from, to)) {//주어진 시간내에 방문 이력이 없을시
			for(int j=from; j<to;j++) {//시간 전부 방문 처리 후
				visited[j] = true;
			}
			dfs(depth+1, count+1); //depth도 깊어지고 골랐으므로 count도 늘림
			for(int j=from; j<to;j++) {//dfs 끝난후에 다시 원복.
				visited[j] = false;
			}
		}
		//고르지 않고 넘어감.
		dfs(depth+1, count);
	}
	
	static boolean isOK(int from, int to) {
		for(int i=from; i<to; i++) {
			if(visited[i]) {//주어진 시간내에 하나라도 이미 방문한 이력이 있을시
				return false;
			}
		}
		return true;
	}

}
