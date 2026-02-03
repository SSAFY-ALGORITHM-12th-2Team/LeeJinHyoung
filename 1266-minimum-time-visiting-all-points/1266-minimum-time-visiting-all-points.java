import java.awt.Point;

class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int answer=0;
        Point cur=new Point(points[0][0],points[0][1]);

        for(int[] point:points){
            while(!(cur.x==point[0]&&cur.y==point[1])){
                int dirX=point[0]-cur.x;
                int dirY=point[1]-cur.y;

                if(dirX!=0&&dirY!=0){
                    int normalize_dirX=dirX/Math.abs(dirX);
                    int normalize_dirY=dirY/Math.abs(dirY);

                    cur.x+=normalize_dirX;
                    cur.y+=normalize_dirY;
                }else if(dirX!=0&&dirY==0){
                    int normalize_dirX=dirX/Math.abs(dirX);
                    cur.x+=normalize_dirX;
                } else if(dirX==0&&dirY!=0){
                    int normalize_dirY=dirY/Math.abs(dirY);
                    cur.y+=normalize_dirY;
                }
                answer++;
            }
        }
        return answer;
    }
}