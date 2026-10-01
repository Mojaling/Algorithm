// PROGRAMMERS #154540 · 무인도 여행
// https://school.programmers.co.kr/learn/courses/30/lessons/154540
// Language: Java
// Execution Time: 44.02 ms
// Memory: 81.868 MB

import java.io.*;
import java.util.*;

class Solution {
	/*
	 * X, 1~9 X는 바다. 숫자는 식량.
	 * 상하좌우 연결은 하나.
	 * 각 섬에서 최대 며칠씩 머무를 수 있는지를 오름차순으로 return
	 * 지낼수 있는 무인도가 없다면 -1
	 */
	static int H,W;
	static int[][] map;
	static List<Integer> answer;
	static int totalCount;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
    public int[] solution(String[] maps) {
        H = maps.length;
        W = maps[0].length();
        map = new int[H][W];
        for(int r=0;r<H;r++) {
        	String line = maps[r];
        	for(int c=0;c<W;c++) {
        		if(line.charAt(c)=='X') {
        			map[r][c] = 0;
        		}else {
        			map[r][c] = line.charAt(c) - '0';
        		}
        	}
        }
        boolean noOne = true;
        answer = new ArrayList<Integer>();
        for(int r=0;r<H;r++) {
        	for(int c=0;c<W;c++) {
        		totalCount =0;
        		if(map[r][c]!=0) {
        			noOne = false;
        			int original = map[r][c];
        			map[r][c]=0;
        			dfs(r,c,original);
        			if(totalCount!=0) {
        				answer.add(totalCount);
        			}
        		}
        	}
        }
        answer.sort((a,b) -> Integer.compare(a, b));
        int[] result = new int[answer.size()];
        for(int i=0;i<answer.size();i++) {
        	result[i] = answer.get(i);
        }
        
        
        if(noOne) {
        	return new int[] {-1};
        }else {
        	return result;
        }
        
    }
    static void dfs(int r, int c, int count) {
    	totalCount+= count;
    	for(int d=0;d<4;d++) {
    		int nr = r + dr[d];
    		int nc = c + dc[d];
    		
    		if(nr<0||nr>=H||nc<0||nc>=W||map[nr][nc]==0) continue;
    		int original = map[nr][nc];
    		map[nr][nc]=0;
    		dfs(nr,nc,original);
    	}
    }
}