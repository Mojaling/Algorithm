class Solution {
	/*
	 * n개의 음이 아닌 정수
	 * 순서 안바꾸고, 더하거나 빼서 타겟 넘버 만들기.
	 * 그 방법의 수를 return
	 */
	static int target;
	static int count;
	static int[] numbers;
	static int sum;
    public int solution(int[] numbers, int target) {
    	count =0;
    	sum =0;
    	this.numbers = numbers;
    	this.target = target;
    	for(int i=0;i<numbers.length;i++) {
    		sum += numbers[i];
    	}
    	dfs(0, sum);
    	
        int answer = count;
        return answer;
    }
    
    static void dfs(int index, int sum) {

        if(index == numbers.length) {
            if(sum == target) {
                count++;
            }
            return;
        }

        // 현재 숫자를 + 그대로 유지
        dfs(index + 1, sum);

        // 현재 숫자를 +에서 -로 변경
        dfs(index + 1, sum - 2 * numbers[index]);
    }
}