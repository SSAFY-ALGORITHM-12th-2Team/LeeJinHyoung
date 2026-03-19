class Solution {
    public boolean check_row(int[][] mat,int r){
        int answer=0;
        for(int i=0;i<mat[r].length;i++){
            if(mat[r][i]==1){
                answer++;
            }
        }
        return answer==1?true:false;
    }
    public boolean check_col(int[][] mat,int c){
        int answer=0;
        for(int i=0;i<mat.length;i++){
            if(mat[i][c]==1){
                answer++;
            }
        }
        return answer==1?true:false;
    }
    public int numSpecial(int[][] mat) {
        int answer=0;
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j]==1&&check_row(mat,i)&&check_col(mat,j)){
                    System.out.println(i+" " +j);
                    answer++;
                }
            }
        }
        return answer;
    }
}