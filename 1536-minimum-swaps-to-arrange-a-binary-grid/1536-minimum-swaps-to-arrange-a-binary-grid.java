class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];

        // 1. 각 행의 trailing zero 개수 계산
        for(int i = 0; i < n; i++) {
            int count = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(grid[i][j] == 0) count++;
                else break;
            }
            zeros[i] = count;
        }

        int answer = 0;

        // 2. 위에서부터 조건 맞추기
        for(int i = 0; i < n; i++) {
            int needed = n - 1 - i;
            int j = i;

            // 조건 만족하는 행 찾기
            while(j < n && zeros[j] < needed) {
                j++;
            }

            if(j == n) return -1; // 불가능

            // 인접 swap 으로 끌어올리기
            while(j > i) {
                int temp = zeros[j];
                zeros[j] = zeros[j - 1];
                zeros[j - 1] = temp;
                j--;
                answer++;
            }
        }

        return answer;
    }
}