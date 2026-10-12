import java.io.*;
import java.util.*;

public class Solution{
	/*
	 *  각 달의 이용 계획 수립하여 가장 적은 비용으로 수영장을 이용할 수 있는 방법
	 *  1일 이용권 : 1일 이용 가능
	 *  1달 이용권 : 매달1일서부터 1달 이용 가능.
	 *  3달 이용권: 연속된 3달 이용 가능. 해가 넘어가게끔은 안됨.
	 *  1년 이용권 : 1월 1일서부터 1년.
	 *  
	 *  해당 달에 수영장을 이용할 날의 수.
	 *  각 이용권의 요금, 각 달의 이용 계획 주어짐.
	 *  가장 적은 비용으로 이용할 방법을 찾고, 그 비용을 정답으로 출력
	 */
	
	/*
	 * 1일 10원 / 1달 40원 / 3달 100원 / 1년 300원
	 */
	
	/*
	 * 1달을 1일로 나눈값 보다 나가는 날이 많다면, 1달로 끊어야함.
	 * 3
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] tickets = new int[4];
			for(int i=0;i<4;i++) {
				tickets[i]=Integer.parseInt(st.nextToken());
			}
			int[] months = new int[13];
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=12;i++) {
				months[i] = Integer.parseInt(st.nextToken());
			}
			int[] dp = new int[13];   // dp[i] = 1월~i월까지의 최소 비용
			dp[0] = 0;                // 아직 아무 달도 없으면 비용 0

			for (int i = 1; i <= 12; i++) {
			    // ① i월 "한 달만" 해결하는 가장 싼 비용 (1일권 vs 1달권)
			    int oneMonth = Math.min(months[i]*tickets[0], tickets[1]);

			    // ② 3달권이 i월에 "끝난다"고 볼 때, 그 앞까지의 비용
			    int prev;
			    if(i<=2) {
			    	prev = dp[0];
			    }else {
			    	prev = dp[i-3];
			    }
			    // 몇 월까지의 dp일까? (음수가 되면 0으로)

			    dp[i] = Math.min(dp[i - 1] + oneMonth, prev + tickets[2]);
			}

			int answer = Math.min(dp[12], tickets[3]);   // 1년권과 비교
			System.out.printf("#%d %d%n",tc, answer);
		}
	}
}