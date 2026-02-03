import java.util.Arrays;

public class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // 1. 각 방향별로 가장 길게 연속된 구간(+1)을 구함
        int maxH = getMaxGap(hBars);
        int maxV = getMaxGap(vBars);
        
        // 2. 정사각형이므로 두 방향 중 더 짧은 쪽이 한 변의 길이를 결정
        int side = Math.min(maxH, maxV);
        
        // 3. 넓이 반환
        return side * side;
    }

    private int getMaxGap(int[] bars) {
        // 연속 구간을 찾기 위해 정렬 필수
        Arrays.sort(bars);
        
        int maxConsecutive = 1;
        int currentConsecutive = 1;
        
        for (int i = 1; i < bars.length; i++) {
            // 앞의 바와 번호가 연속되는지 확인
            if (bars[i] == bars[i - 1] + 1) {
                currentConsecutive++;
            } else {
                currentConsecutive = 1;
            }
            maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
        }
        
        // 바가 N개 연속되면 실제 구멍의 길이는 N + 1
        return maxConsecutive + 1;
    }
}