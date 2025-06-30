import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split("");
        String[] b = br.readLine().split("");

        int[][] memo = new int[a.length][b.length];

        // 첫 행과 첫 열 초기화
        memo[0][0] = a[0].equals(b[0]) ? 1 : 0;

        for (int i = 1; i < a.length; i++) {
            memo[i][0] = a[i].equals(b[0]) ? 1 : memo[i - 1][0];
        }

        for (int i = 1; i < b.length; i++) {
            memo[0][i] = b[i].equals(a[0]) ? 1 : memo[0][i - 1];
        }

        // LCS 점화식 적용
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                if (a[i].equals(b[j])) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }

        System.out.println(memo[a.length - 1][b.length - 1]);
    }
}