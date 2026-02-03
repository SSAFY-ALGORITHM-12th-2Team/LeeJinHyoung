class Solution {
    public int solution(int[] money) {
        int n = money.length;
        if (n == 1) return money[0];
        if (n == 2) return Math.max(money[0], money[1]);

//         첫번째집 털었다면 마지막 집 불가
        int robFirst = rob(money, 0, n - 2);
//         첫번째 집 털지 않았다면 마지막집 털기
        int notRobFirst = rob(money, 1, n - 1);

        return Math.max(robFirst, notRobFirst);
    }

    private int rob(int[] money, int start, int end) {
        int prev2 = 0;         // i-2번째 최대값
        int prev1 = 0;         // i-1번째 최대값
        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}
