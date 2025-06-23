import java.util.*;
class Solution {
    static class Point{
        int row;
        int col;
        
        public Point(int row,int col){
            this.row=row;
            this.col=col;
        }
    }
    public int solution(int n, int w, int num) {
        int answer = 0;
        
//         우선 택배상자를 쌓아야한다.
//         최대 숫자만큼 배열을 만들어 계산하는 방법이 빠를듯 한데
//         어차피 w의 상한은 10이고 100을 곱해도 1000이므로 가능
        int[][] boxes=new int[n/w+1][w];
        
        int box_number=1;
        HashMap<Integer, Point> indices=new HashMap<>();
        
        for(int row=0;row<n/w+1;row++){
            if(row%2==0){
                for(int col=0;col<w;col++){
                    if(box_number>n)
                        break;
                    indices.put(box_number,new Point(row,col));
                    boxes[row][col]=box_number++;
                }
            } else{
                for(int col=w-1;col>=0;col--){
                    if(box_number>n)
                        break;
                    indices.put(box_number,new Point(row,col));
                    boxes[row][col]=box_number++;
                }
            }
        }
//        자 이제 박스를 쌓았다. 
//        Map에 미리 저장한 인덱스를 활용해 아래로 내려가면서 치워야할 박스를 구하자.
        for(int[] row:boxes){
            for(int b: row){
                System.out.print(b+" ");
            }
            System.out.println();
        }
        
        Point pos=indices.get(num);
        for(int row=pos.row;row<n/w+1;row++){
            if(boxes[row][pos.col]!=0)
                answer++;
            else
                break;
        }
        
        return answer;
    }
}