import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문제에서 테스트 케이스는 항상 10개
		for (int times = 1; times <= 10; times++) {

			int N = Integer.parseInt(br.readLine());
			int result = 1; // 처음에는 정상이라고 가정

			for (int i = 0; i < N; i++) {

				StringTokenizer st = new StringTokenizer(br.readLine());

				String nodeNum = st.nextToken(); // 노드 번호
				String second = st.nextToken();  // 숫자 or 연산자

				if (Character.isDigit(second.charAt(0))) {
					// 숫자라면 자식이 없어야 함
					if (st.countTokens() != 0) {
						result = 0;
					}

				} else {
					// 연산자라면 왼쪽, 오른쪽 자식이 있어야 함
					if (st.countTokens() != 2) {
						result = 0;
					}
				}
			}

			System.out.printf("#%d %d%n", times, result);
		}
	}
}