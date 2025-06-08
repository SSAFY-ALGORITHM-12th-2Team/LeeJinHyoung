import java.util.*;
import java.awt.*;

class Solution {
    private static int answer;
    private static int n;
    private static int m;
    private static boolean[][] hazards;
    
    private static int[][] memo;
    
    public void dfs(){
        for(int row=0;row<n;row++){
            for(int col=0;col<m;col++){
                if(row==0&&col==0){
                    memo[row][col]=1;
                } else if(row==0&&!hazards[row][col]){
                    memo[row][col]+=memo[0][col-1]%1000000007;
                } else if(col==0&&!hazards[row][col]){
                    memo[row][col]+=memo[row-1][0]%1000000007;
                } else if(!hazards[row][col]){
                    memo[row][col]=(memo[row-1][col]+memo[row][col-1])%1000000007;
                }
            }
        }    
    }
    
    public int solution(int m, int n, int[][] puddles) {
        answer = 0;
        this.n=n;
        this.m=m;
        hazards=new boolean[n][m];
        memo=new int[n][m];
        
        for(int[] puddle:puddles){
            hazards[puddle[1]-1][puddle[0]-1]=true;
        }
        dfs();
        return memo[n-1][m-1]%1000000007;
    }
}