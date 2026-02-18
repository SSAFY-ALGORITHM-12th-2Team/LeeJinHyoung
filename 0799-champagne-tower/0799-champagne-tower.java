class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // 100행까지 있으므로 101x101 또는 102x102 크기로 설정합니다.
        double[][] tower = new double[102][102];
        
        // 첫 번째 잔에 모든 샴페인을 일단 붓습니다.
        tower[0][0] = (double) poured;

        for (int row = 0; row <= query_row; row++) {
            for (int col = 0; col <= row; col++) {
                // 현재 잔에서 넘치는 양을 계산합니다.
                double overflow = (tower[row][col] - 1.0) / 2.0;
                
                if (overflow > 0) {
                    // 왼쪽 아래와 오른쪽 아래로 절반씩 흘려보냅니다.
                    tower[row + 1][col] += overflow;
                    tower[row + 1][col + 1] += overflow;
                }
            }
        }

        // 구하고자 하는 잔의 값이 1보다 크면 1을 반환(잔이 꽉 찬 상태), 아니면 그 값을 반환합니다.
        return Math.min(1.0, tower[query_row][query_glass]);
    }
}