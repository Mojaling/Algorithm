import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스위치 블록(16진수 문자) -> 스위치 자세(2진수 4자리) 복원
 *
 * 핵심 아이디어
 *  - 글자 하나 = 스위치 4개 = 0~15 사이의 값 하나
 *  - 그 값을 "항상 4자리"의 2진수로 펼쳐서 이어 붙이면 끝
 *  - 앞쪽 0도 반드시 남겨야 하므로 Integer.toBinaryString() 같은 걸
 *    그냥 쓰면 안 되고(예: 4 -> "100"), 4자리 고정 출력이 필요하다.
 */
public class Solution {

    public static void main(String[] args) throws Exception {
        // SWEA에서 입력 파일(16780_input.txt)로 테스트하려면 아래 주석을 풀면 된다.
        // System.setIn(new java.io.FileInputStream("res/16780_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 출력이 최대 50줄 x 400자라 그리 크진 않지만,
        // 매번 println 하는 것보다 StringBuilder에 모아 한 번에 내보내는 게 빠르다.
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            // 한 줄에 "N 설정코드" 형태로 들어오므로 공백 기준으로 쪼갠다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 글자 수
            String code = st.nextToken();             // 설정 코드 (길이 N)

            sb.append('#').append(tc).append(' ');

            // 왼쪽 글자부터 순서대로 처리
            for (int i = 0; i < N; i++) {
                int value = hexValue(code.charAt(i)); // 글자 -> 0~15

                // 가장 왼쪽 스위치가 큰 자리이므로 3번 비트부터 0번 비트 순으로 꺼낸다.
                // (value >> bit) & 1 은 value의 bit번째 비트만 남기는 표준적인 방법.
                for (int bit = 3; bit >= 0; bit--) {
                    sb.append((value >> bit) & 1);
                }
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

    /**
     * 16진수 글자 하나를 정수 값(0~15)으로 바꾼다.
     * '0'~'9' -> 0~9,  'A'~'F' -> 10~15
     *
     * 문자끼리 빼면 아스키 코드 차이가 나온다는 성질을 이용한 것.
     * (Character.digit(c, 16) 으로 대체해도 결과는 같다.)
     */
    private static int hexValue(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        return c - 'A' + 10; // 문제 조건상 대문자 A~F만 들어온다
    }
}