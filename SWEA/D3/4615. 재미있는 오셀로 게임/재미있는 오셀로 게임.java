import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] dx = { 1, 0, -1, 0, 1, -1, -1, 1 };
        int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };

        for (int tc = 1; tc <= T; tc++) {
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = input[0];
            int M = input[1];

            String[][] board = new String[N][N];

            // 보드 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = ".";
                }
            }

            board[N / 2 - 1][N / 2 - 1] = "W";
            board[N / 2][N / 2] = "W";
            board[N / 2 - 1][N / 2] = "B";
            board[N / 2][N / 2 - 1] = "B";

            for (int i = 0; i < M; i++) {
                int[] o = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int col = o[0] - 1;
                int row = o[1] - 1;

                String currentPlayer = o[2] == 1 ? "B" : "W";
                board[row][col] = currentPlayer;

                for (int j = 0; j < 8; j++) {
                    int k = 1;
                    boolean validMove = false;

                    while (true) {
                        int ny = row + dy[j] * k;
                        int nx = col + dx[j] * k;

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) break;

                        if (board[ny][nx].equals(".")) break;

                        if (board[ny][nx].equals(currentPlayer)) {
                            validMove = true;
                            break;
                        }
                        k++;
                    }

                    if (validMove) {
                        for (int mul = 1; mul < k; mul++) {
                            int ny = row + dy[j] * mul;
                            int nx = col + dx[j] * mul;
                            board[ny][nx] = currentPlayer;
                        }
                    }
                }
            }

            int w = 0;
            int b = 0;
            for (String[] i : board) {
                for (String j : i) {
                    if (j.equals("W")) w++;
                    else if (j.equals("B")) b++;
                }
            }
            System.out.println("#" + tc + " " + b + " " + w);
        }
    }
}
