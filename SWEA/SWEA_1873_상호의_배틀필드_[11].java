import java.util.*;
import java.io.*;
import java.math.BigDecimal;
/*
 * .평지 / *벽돌벽 / #강철벽 / -물 / ^ 위쪽 바라보기 / V 아래쪽 바라보기 / < 왼쪽 바라보기 / > 오른쪽 바라보기
 * U 위로이동, D아래이동, L좌로이동, R우로이동, S 바라보고 있는방향으로 쏘기
 * 포탄이 벽돌벽 맞을시 칸이 평지가됨. 강철벽은 무적.
 * 모든 입력 처리 후에 게임 맵의 상태?
 */

public class Solution {
	static int tankR;
	static int tankC;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int times = 1; times <= T; times++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());//H는 높이
			int W = Integer.parseInt(st.nextToken());//W는 너비
			char[][] map = new char[H][W];
			for(int i=0;i<H;i++) {//map 세팅.
				String line = br.readLine();
				for(int j=0;j<W;j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == '<' ||
							map[i][j] == '>'||
							map[i][j] == 'v'||
							map[i][j] == '^') {
						tankR = i;
						tankC = j;
					}
				}
			}
			int N = Integer.parseInt(br.readLine());//N은 사용자가 넣을 입력의 개수
			String commands = br.readLine();
			char direction = map[tankR][tankC];
			for(char x : commands.toCharArray()) {
				if(x == 'U') {
					direction = '^';
					map[tankR][tankC] = '^';
					
					int nr = tankR - 1;
					int nc = tankC;
					
					if(nr>=0 && nr<H && map[nr][nc]=='.') {
						map[tankR][tankC] = '.';
						map[nr][nc] = '^';
						tankR = nr;
						tankC = nc;
					}
				}
				else if(x == 'D') {
					direction = 'v';
					map[tankR][tankC] = 'v';
					
					int nr = tankR + 1;
					int nc = tankC;
					
					if(nr>=0 && nr<H && map[nr][nc]=='.') {
						map[tankR][tankC] = '.';
						map[nr][nc] = 'v';
						tankR = nr;
						tankC = nc;
					}
				}
				else if(x == 'L') {
					direction = '<';
					map[tankR][tankC] = '<';
					
					int nr = tankR;
					int nc = tankC - 1;
					
					if(nc>=0 && nc<W && map[nr][nc]=='.') {
						map[tankR][tankC] = '.';
						map[nr][nc] = '<';
						tankR = nr;
						tankC = nc;
					}
				}
				else if(x == 'R') {
					direction = '>';
					map[tankR][tankC] = '>';
					
					int nr = tankR;
					int nc = tankC + 1;
					
					if(nc>=0 && nc<W && map[nr][nc]=='.') {
						map[tankR][tankC] = '.';
						map[nr][nc] = '>';
						tankR = nr;
						tankC = nc;
					}
				}
				else if(x == 'S') {					
					if(direction == '^') {
						int sr = tankR - 1;
						int sc = tankC;
						
						while(sr >= 0) {
							if(map[sr][sc] == '*') {
							    map[sr][sc] = '.';
							    break;
							}
							else if(map[sr][sc] == '#') {
							    break;
							}
							
							sr--;
						}
						//위로 발사
					}
					else if(direction =='v') {
						int sr = tankR + 1;
						int sc = tankC;
						
						while(sr < H) {
							if(map[sr][sc] == '*') {
							    map[sr][sc] = '.';
							    break;
							}
							else if(map[sr][sc] == '#') {
							    break;
							}
							
							sr++;
						}
						//아래로 발사
					}
					else if(direction == '<') {
					    int sr = tankR;
					    int sc = tankC - 1;

					    while(sc >= 0) {

					        if(map[sr][sc] == '*') {
					            map[sr][sc] = '.';
					            break;
					        }
					        else if(map[sr][sc] == '#') {
					            break;
					        }

					        sc--;
					    }
					}
					else if(direction == '>') {
					    int sr = tankR;
					    int sc = tankC + 1;

					    while(sc < W) {

					        if(map[sr][sc] == '*') {
					            map[sr][sc] = '.';
					            break;
					        }
					        else if(map[sr][sc] == '#') {
					            break;
					        }

					        sc++;
					    }
					}
				}
			}
			System.out.print("#" + times + " ");

			for(int i = 0; i < H; i++) {
			    for(int j = 0; j < W; j++) {
			        System.out.print(map[i][j]);
			    }
			    System.out.println();
			}
		}
	}
}