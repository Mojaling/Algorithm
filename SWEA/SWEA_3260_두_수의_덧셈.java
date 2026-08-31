import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            String A = st.nextToken();
            String B = st.nextToken();

            ArrayDeque<Integer> a = new ArrayDeque<>();
            ArrayDeque<Integer> b = new ArrayDeque<>();
            ArrayDeque<Integer> result = new ArrayDeque<>();

            // 문자열의 각 숫자를 deque에 넣기
            for (int i = 0; i < A.length(); i++) {
                a.addLast(A.charAt(i) - '0');
            }

            for (int i = 0; i < B.length(); i++) {
                b.addLast(B.charAt(i) - '0');
            }

            int carry = 0;

            // 뒤쪽 숫자부터 하나씩 꺼내서 더하기
            while (!a.isEmpty() || !b.isEmpty()) {

                int numA = 0;
                int numB = 0;

                if (!a.isEmpty()) {
                    numA = a.pollLast();
                }

                if (!b.isEmpty()) {
                    numB = b.pollLast();
                }

                int sum = numA + numB + carry;

                result.addFirst(sum % 10);

                carry = sum / 10;
            }

            // 마지막 올림수가 남아있다면
            if (carry > 0) {
                result.addFirst(carry);
            }

            System.out.print("#" + tc + " ");

            while (!result.isEmpty()) {
                System.out.print(result.pollFirst());
            }

            System.out.println();
        }
    }
}