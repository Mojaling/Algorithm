import java.io.*;
import java.util.*;

public class Main{
    /*
     * 1이상 K이하의 정수 고르는걸 N번 반복할때
     * 나오는 모든 수열을 구해주기.
     */
    static int K,N;
    static List<int[]> list;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());//1~K이하의 정수.
        N = Integer.parseInt(st.nextToken());//N번 반복.
        list = new ArrayList<>();
        arr= new int[N];
        dfs(1);
        System.out.print(sb);
    }
    static void dfs(int depth) {
        if(depth==N+1) {
            for(int i=0;i<arr.length;i++){
                sb.append(arr[i]+" ");
            }
            sb.append('\n');
            return;
        }
        for(int i=1;i<=K;i++) {
            arr[depth-1] = i;
            dfs(depth+1);
        }
        
    }
}