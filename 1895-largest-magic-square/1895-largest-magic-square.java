class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // prefix sums
        int[][] row = new int[m][n + 1];
        int[][] col = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i][j + 1] = row[i][j] + grid[i][j];
                col[i + 1][j] = col[i][j] + grid[i][j];
            }
        }

        int maxSide = Math.min(m, n);

        // try bigger squares first
        for (int k = maxSide; k >= 2; k--) {
            for (int i = 0; i + k <= m; i++) {
                for (int j = 0; j + k <= n; j++) {
                    if (isMagic(i, j, k, grid, row, col)) return k;
                }
            }
        }

        return 1;
    }

    private boolean isMagic(int r, int c, int k, int[][] grid, int[][] row, int[][] col) {
        int target = row[r][c + k] - row[r][c];

        // check rows
        for (int i = 0; i < k; i++) {
            if (row[r + i][c + k] - row[r + i][c] != target) return false;
        }

        // check cols
        for (int j = 0; j < k; j++) {
            if (col[r + k][c + j] - col[r][c + j] != target) return false;
        }

        // check diagonals
        int d1 = 0, d2 = 0;
        for (int i = 0; i < k; i++) {
            d1 += grid[r + i][c + i];
            d2 += grid[r + i][c + k - 1 - i];
        }

        return d1 == target && d2 == target;
    }
}
