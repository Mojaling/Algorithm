import java.io.*;
import java.util.*;

class Solution {
	/*
	 * 가장빠르게 상대진영 도착.
	 * 최솟값을 return
	 * 도착못할때는 -1
	 * 
	 * Queue에 넣음
	 * ↓
	 * while(!q.isEmpty())
	 * ↓
	 * 하나 꺼냄
	 * ↓
	 * 주변 칸 Queue에 넣음
	 */
    public int solution(int[][] maps) {
    	//bfs 순서
    	//1.Queue선언하기, 시작점넣기.
    	Queue<int[]> queue = new ArrayDeque<>();
    	boolean[][] visited = new boolean[maps.length][maps[0].length];
    	visited[0][0] = true;
    	queue.offer(new int[] {0, 0, 1});//row, col, count
    	
    	while(!queue.isEmpty()) {
    		int[] now = queue.poll();
    		int r = now[0];
    		int c = now[1];
    		int count = now[2];
    		if(r==maps.length-1 && c==maps[0].length-1) {
    			return count;
    		}
    		
    		int[] dr = {-1,1,0,0};
    		int[] dc = {0,0,-1,1};
    		
    		for(int d=0;d<4;d++) {
    			int nr = r + dr[d];
    			int nc = c + dc[d];
    			if(nr<0 || nr>=maps.length || nc<0 || nc>=maps[0].length || maps[nr][nc] == 0 || visited[nr][nc]) {
    				continue;
    			}else {
    				visited[nr][nc] = true;
    				queue.offer(new int[] {nr, nc, count+1});
    			}
    		}
    	}
    	return -1;
    }
}