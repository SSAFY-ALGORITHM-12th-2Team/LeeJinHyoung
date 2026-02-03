import java.io.*;
import java.util.*;

public class Main {
    private static int n, sticker[][], dp[][];

    private static int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            sticker = new int[2][n];

            for (int i = 0; i < 2; i++) {
                sticker[i] = Arrays.stream(br.readLine().split(" "))
                                   .mapToInt(Integer::parseInt)
                                   .toArray();
            }

            dp = new int[3][n];

            dp[0][0] = 0;
            dp[1][0] = sticker[0][0];
            dp[2][0] = sticker[1][0];

            if (n > 1) {
                dp[0][1] = max3(dp[0][0], dp[1][0], dp[2][0]);
                dp[1][1] = Math.max(dp[0][0], dp[2][0]) + sticker[0][1];
                dp[2][1] = Math.max(dp[0][0], dp[1][0]) + sticker[1][1];
            }

            for (int i = 2; i < n; i++) {
                dp[0][i] = max3(dp[0][i-1], dp[1][i-1], dp[2][i-1]);
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + sticker[0][i];
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + sticker[1][i];
            }

            sb.append(max3(dp[0][n-1], dp[1][n-1], dp[2][n-1])).append("\n");
        }

        System.out.print(sb);
    }
}
