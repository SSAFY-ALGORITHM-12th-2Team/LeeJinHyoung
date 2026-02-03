class Solution {

    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (int[] s : squares) {
            double y = s[1];
            double side = s[2];
            totalArea += side * side;
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y + side);
        }

        double left = minY;
        double right = maxY;
        double target = totalArea / 2.0;

        // 충분한 정밀도 확보
        for (int i = 0; i < 100; i++) {
            double mid = (left + right) / 2.0;
            if (areaBelow(squares, mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private double areaBelow(int[][] squares, double h) {
        double area = 0;

        for (int[] s : squares) {
            double y = s[1];
            double side = s[2];
            double top = y + side;

            if (h <= y) {
                continue;
            } else if (h >= top) {
                area += side * side;
            } else {
                area += side * (h - y);
            }
        }

        return area;
    }
}
