import java.util.*;

public class Main {
    private static char[] array;
    private static boolean[][] memo;
    private static int answer;

    private static void findMin(int s, int chunk, int[] dp) {
        if (s < 0) { // 종료 조건
            answer = Math.min(answer, chunk);
            return;
        }
        if (dp[s] <= chunk) { // 이미 더 작은 값이 있는 경우
            return;
        }
        dp[s] = chunk;

        for (int start = 0; start <= s; start++) {
            if (memo[start][s]) {
                findMin(start - 1, chunk + 1, dp);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        if (input == null || input.isEmpty()) {
            System.out.println(0);
            return;
        }

        array = input.toCharArray();
        memo = new boolean[array.length][array.length];

        // DP 테이블 채우기 (팰린드롬 여부)
        for (int e = 0; e < memo.length; e++) {
            memo[e][e] = true; // 한 글자는 항상 팰린드롬
            for (int s = 0; s < e; s++) {
                if (array[s] == array[e] && (e - s == 1 || memo[s + 1][e - 1])) {
                    memo[s][e] = true;
                }
            }
        }

        // 최소 분할 수 찾기
        answer = Integer.MAX_VALUE;
        int[] dp = new int[memo.length]; // 최소 분할 수 저장
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int s = 0; s < memo.length; s++) {
            if (memo[s][memo.length - 1]) {
                findMin(s - 1, 1, dp);
            }
        }
        System.out.println(answer);
    }
}