class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] memo=new int[triangle.length][triangle.length];
        
        memo[0][0]=triangle[0][0];
        for(int row=1;row<triangle.length;row++){
            for(int col=0;col<triangle[row].length;col++){
                if(col==0){
                    memo[row][col]=memo[row-1][col]+triangle[row][col];
                } else if(col==row){
                    memo[row][col]=memo[row-1][col-1]+triangle[row][col];
                } else{
                    int left=memo[row-1][col-1]+triangle[row][col];
                    int right=memo[row-1][col]+triangle[row][col];
                    
                    memo[row][col]=Math.max(left,right);
                }
            }
        }
        for(int col=0;col<memo[triangle.length-1].length;col++){
            answer=Math.max(memo[triangle.length-1][col],answer);
        }        
        
        return answer;
    }
}