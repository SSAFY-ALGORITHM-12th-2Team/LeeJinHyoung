import java.util.*;
import java.awt.Point;
class Solution {
    private static class Node{
        Point point;
        int number;
        public Node(Point point,int number){
            this.point=point;
            this.number=number;
        }
    }
    
    private static int[][] board;
    
    public static ArrayList<Node> fillList(int[] query,ArrayList<Node>list){
        for(int col=query[1];col<query[3];col++){
            list.add(new Node(new Point(query[0],col+1),board[query[0]][col]));
        } //가장 윗변
        for(int row=query[0];row<query[2];row++){
            list.add(new Node(new Point(row+1,query[3]),board[row][query[3]]));
        } //오른쪽 변
        for(int col=query[3];col>query[1];col--){
            list.add(new Node(new Point(query[2],col-1),board[query[2]][col]));
        } //아랫쪽 변
        for(int row=query[2];row>query[0];row--){
            list.add(new Node(new Point(row-1,query[1]),board[row][query[1]]));
        } //왼쪽 변
        return list;
    }
    
    public static int fillBoard(ArrayList<Node>list){
        int min=Integer.MAX_VALUE;        
        for(int i=0;i<list.size();i++){
            Node node=list.get(i);
            board[node.point.x][node.point.y]=node.number;
            min=Math.min(node.number,min);
        }
        return min;
    }
    
    public static int rotate(int[] query){
        //여기서 board를 변화시킨다.         
        ArrayList<Node>list=new ArrayList<>();
        fillList(query,list);
        int min=fillBoard(list);
        
        return min;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        //테두리만 움직인다.
        board=new int[rows][columns];
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                board[i][j]=columns*i+j+1;
            }
        }
        for(int i=0;i<queries.length;i++){
            for(int j=0;j<4;j++){
                queries[i][j]--;
            }
        }//각 숫자가 1카운트이므로 1씩 더 크다
        
        for(int i=0;i<queries.length;i++){
            answer[i]=rotate(queries[i]);
            // for(int row=0;row<rows;row++){
            //     for(int col=0;col<columns;col++){
            //         System.out.print(board[row][col]+" ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
        }
        return answer;
    }
}