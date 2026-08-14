import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for (int tc = 1; tc <= T; tc++) {
            String line = sc.nextLine();

            Deque<Character> stack = new ArrayDeque<>();

            boolean singleQuote = false;
            boolean doubleQuote = false;
            boolean isValid = true;

            for (char ch : line.toCharArray()) {

                // 작은따옴표 시작/종료
                if (ch == '\'' && !doubleQuote) {
                    singleQuote = !singleQuote;
                    continue;
                }

                // 큰따옴표 시작/종료
                if (ch == '"' && !singleQuote) {
                    doubleQuote = !doubleQuote;
                    continue;
                }

                // 따옴표 내부의 괄호는 무시
                if (singleQuote || doubleQuote) {
                    continue;
                }

                // 여는 괄호
                if (ch == '(' || ch == '{') {
                    stack.push(ch);
                }

                // 닫는 소괄호
                else if (ch == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        isValid = false;
                        break;
                    }
                    stack.pop();
                }

                // 닫는 중괄호
                else if (ch == '}') {
                    if (stack.isEmpty() || stack.peek() != '{') {
                        isValid = false;
                        break;
                    }
                    stack.pop();
                }
            }

            if (!stack.isEmpty()) {
                isValid = false;
            }

            System.out.println("#" + tc + " " + (isValid ? 1 : 0));
        }
    }
}