class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        // 우선 정답은 long을 반환한다.
        long answer=0L;
        int n=bottomLeft.length;
        // 1000 * 1000이면 십만이니까 그냥 이중 for문 돌려서 교차하는지 확인하면 될 것 같다.
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                // 서로 같은 사각형끼리는 잴 필요 없으니까
                if(i==j)
                    continue;
                // 교차하는지 안하는지 확인한다.
                int bottomLeftX1=bottomLeft[i][0];
                int bottomLeftY1=bottomLeft[i][1];

                int topRightX1=topRight[i][0];
                int topRightY1=topRight[i][1];

                int bottomLeftX2=bottomLeft[j][0];
                int bottomLeftY2=bottomLeft[j][1];

                int topRightX2=topRight[j][0];
                int topRightY2=topRight[j][1];

                // 만약 bottomLeft가 topRight보다 모두 큰 좌표에 있다면 겹치지 않으므로 이 경우도 패스
                // 첫번째 사각형 좌하점의 X축과 Y축이 모두 두번째 사각형 우상점 X축과 Y축보다 크다면 패스
                if((topRightY1<=bottomLeftY2||topRightY2<=bottomLeftY1)
                ||(bottomLeftX1>=topRightX2||bottomLeftX2>=topRightX1))
                    continue;

                int horizontal=0;
                int vertical=0;
                if (topRightX1 <= bottomLeftX2 || topRightX2 <= bottomLeftX1 ||
                    topRightY1 <= bottomLeftY2 || topRightY2 <= bottomLeftY1)
                    continue;

                int overlapX = Math.min(topRightX1, topRightX2) - Math.max(bottomLeftX1, bottomLeftX2);
                int overlapY = Math.min(topRightY1, topRightY2) - Math.max(bottomLeftY1, bottomLeftY2);

                int side = Math.min(overlapX, overlapY);
                answer = Math.max(answer, (long) side * side);
            }
        }
        return answer;
    }
}