import java.io.*;
import java.util.*;

class Solution {
	/*
	 * 바다와 무인도
	 * 각 칸에는 Xor 1~9
	 * 연결되는 숫자 하나의 무인도
	 * 숫자는 식량. 최대 몇일 머무를 수 있는지.
	 * 각 섬에서 최대 몇일 머무를 수 있는지 배열 오름차순. 이를 return
	 * 무인도가 없다면 -1
	 * 
	 * 연결되있는 숫자들은 모두 더하기.
	 * X는 스킵.
	 */
    public int[] solution(String[] maps) {
    	int N = maps.length;
    	int M = maps[0].length();
    	int[][] map = new int[N][M];
    	for(int i=0;i<N;i++) {//map 세팅
    		for(int j=0;j<M;j++) {
    			if(maps[i].charAt(j) == 'X') {//X부분은 0으로 바꿈.
    				map[i][j] = 0;
    			}else {
    				map[i][j] = maps[i].charAt(j) - '0';
    			}
    		}
    	}
    	
    	boolean[][] visited = new boolean[map.length][map[0].length];
    	
    	for(int r=0;r<N;r++) {
    		for(int c=0;c<M;c++) {
    			if(map[r][c]==0) {
    				continue;
    			}else {
    				int now = map[r][c];
    				visited[r][c] = true;
    				
    				int[] dr = {-1,1,0,0};
    				int[] dc = {0,0,-1,1};
    				for(int d=0;d<4;d++) {
    					int nr = r + dr[d];
    					int nc = c + dc[d];
    					
    					if(nr<0 || nr >=N || nc<0 || nc>=M || map[nr][nc]==0 || visited[nr][nc]) {
    						continue;
    					}else {
    						
    					}
    				}
    			}
    		}
    	}
    	
    	int[] answer = {};
        return answer;
    }
}