import java.util.*;

class Solution {
    static class Event {
        double y;
        double x1, x2;
        int delta; // +1 add, -1 remove
        Event(double y, double x1, double x2, int delta) {
            this.y = y; this.x1 = x1; this.x2 = x2; this.delta = delta;
        }
    }

    static class SegTree {
        int n;                 // number of elementary segments = xs.length - 1
        int[] cover;           // cover count
        double[] len;          // covered length of this node
        double[] xs;           // coordinate values

        SegTree(double[] xs) {
            this.xs = xs;
            this.n = xs.length - 1;
            this.cover = new int[4 * n];
            this.len = new double[4 * n];
        }

        void update(int idx, int l, int r, int ql, int qr, int delta) {
            if (qr <= l || r <= ql) return;
            if (ql <= l && r <= qr) {
                cover[idx] += delta;
                pull(idx, l, r);
                return;
            }
            int mid = (l + r) >>> 1;
            update(idx << 1, l, mid, ql, qr, delta);
            update(idx << 1 | 1, mid, r, ql, qr, delta);
            pull(idx, l, r);
        }

        void pull(int idx, int l, int r) {
            if (cover[idx] > 0) {
                len[idx] = xs[r] - xs[l];
            } else if (r - l == 1) {
                len[idx] = 0.0;
            } else {
                len[idx] = len[idx << 1] + len[idx << 1 | 1];
            }
        }

        double coveredLength() {
            return n <= 0 ? 0.0 : len[1];
        }
    }

    public double separateSquares(int[][] squares) {
        int m = squares.length;
        List<Event> events = new ArrayList<>(2 * m);
        double[] xCoords = new double[2 * m];

        for (int i = 0; i < m; i++) {
            double x = squares[i][0];
            double y = squares[i][1];
            double l = squares[i][2];
            double x1 = x, x2 = x + l;
            double y1 = y, y2 = y + l;

            events.add(new Event(y1, x1, x2, +1));
            events.add(new Event(y2, x1, x2, -1));

            xCoords[2 * i] = x1;
            xCoords[2 * i + 1] = x2;
        }

        Arrays.sort(xCoords);
        // unique
        double[] xs = unique(xCoords);
        if (xs.length <= 1) return 0.0;

        events.sort(Comparator.comparingDouble(e -> e.y));

        // map x to index
        Map<Double, Integer> xIndex = new HashMap<>();
        for (int i = 0; i < xs.length; i++) xIndex.put(xs[i], i);

        SegTree st = new SegTree(xs);

        // 1) compute total union area
        double total = 0.0;
        double prevY = events.get(0).y;
        int i = 0;

        while (i < events.size()) {
            double currY = events.get(i).y;
            double dy = currY - prevY;
            if (dy > 0) {
                total += st.coveredLength() * dy;
                prevY = currY;
            }
            // apply all events at currY
            while (i < events.size() && events.get(i).y == currY) {
                Event e = events.get(i);
                int l = xIndex.get(e.x1);
                int r = xIndex.get(e.x2);
                if (l < r) st.update(1, 0, st.n, l, r, e.delta);
                i++;
            }
        }

        double half = total / 2.0;

        // 2) sweep again to find minimal y where areaBelow == half
        st = new SegTree(xs);
        prevY = events.get(0).y;
        double areaSoFar = 0.0;
        i = 0;

        while (i < events.size()) {
            double currY = events.get(i).y;
            double dy = currY - prevY;
            double covered = st.coveredLength();

            if (dy > 0 && covered > 0) {
                double add = covered * dy;
                if (areaSoFar + add >= half - 1e-12) {
                    // answer within [prevY, currY)
                    return prevY + (half - areaSoFar) / covered;
                }
                areaSoFar += add;
                prevY = currY;
            } else if (dy > 0) {
                // covered == 0 => area doesn't change
                prevY = currY;
            }

            while (i < events.size() && events.get(i).y == currY) {
                Event e = events.get(i);
                int l = xIndex.get(e.x1);
                int r = xIndex.get(e.x2);
                if (l < r) st.update(1, 0, st.n, l, r, e.delta);
                i++;
            }
        }

        // If total == 0 (shouldn't), or half reached at top
        return prevY;
    }

    private static double[] unique(double[] arr) {
        int n = arr.length;
        double[] tmp = new double[n];
        int k = 0;
        double prev = Double.NaN;
        for (double v : arr) {
            if (k == 0 || v != prev) {
                tmp[k++] = v;
                prev = v;
            }
        }
        return Arrays.copyOf(tmp, k);
    }
}
