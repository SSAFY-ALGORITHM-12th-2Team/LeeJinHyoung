import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        ArrayList<Integer> list=new ArrayList<Integer>();//바구니
        for(int i=0;i<moves.length;i++){//움직이는 횟수만큼
            for(int j=0;j<board.length;j++){//board를 행
                if(board[j][moves[i]-1]>0){
                    if(list.size()>0&&list.get(list.size()-1)==board[j][moves[i]-1]){  
                        answer+=2;
                        list.remove(list.size()-1);
                        board[j][moves[i]-1]=0;
                        break;
                    } else{
                        list.add(board[j][moves[i]-1]);
                        board[j][moves[i]-1]=0;    
                        break;
                    }
                }                
            }
            // for(int r=0;r<board.length;r++){
            //     for(int c=0;c<board[r].length;c++){
            //         System.out.print(board[r][c]);
            //     }
            //     System.out.println();
            // }
            // for(int e:list)
            //     System.out.print(e);
            // System.out.println();
        }
        
        return answer;
    }
}