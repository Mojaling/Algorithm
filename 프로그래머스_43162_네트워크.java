import java.util.*;

class Solution {

    static int[] parent;

    public int solution(int n, int[][] computers) {

        /*
         * 1. 처음에는 모든 컴퓨터가
         *    서로 다른 네트워크라고 생각.
         */
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        /*
         * 2. 연결되어 있는 컴퓨터들을 찾는다.
         *
         * computers[i][j] == 1
         * → i와 j는 같은 네트워크
         * → union(i, j)
         */
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {

                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        /*
         * 3. 이제 같은 네트워크에 있는 컴퓨터들은
         *    결국 같은 대표자를 가지게 된다.
         *
         * 대표자가 몇 종류인지 세면 된다.
         */

        Set<Integer> roots = new HashSet<>();

        for(int i = 0; i < n; i++) {
            roots.add(find(i));
        }

        return roots.size();
    }

    /*
     * x가 속한 네트워크의 대표자를 찾는다.
     */
    static int find(int x) {

        // 내가 대표자라면
        if(parent[x] == x) {
            return x;
        }

        // 아니라면 부모를 따라가면서 대표자를 찾는다.
        // 동시에 경로 압축.
        return parent[x] = find(parent[x]);
    }

    /*
     * a가 속한 네트워크와
     * b가 속한 네트워크를 하나로 합친다.
     */
    static void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        // 이미 같은 그룹이면 아무것도 안 함
        if(rootA == rootB) {
            return;
        }

        // 서로 다른 그룹이면 하나로 합침
        parent[rootB] = rootA;
    }
}