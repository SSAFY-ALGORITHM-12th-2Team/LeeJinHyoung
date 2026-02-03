class Solution {
    // 합을 구하는 곳
    private int makesum(int[][] mat,int len,int row,int col){
        int sum=0;
        for(int r=row;r<row+len;r++){
            for(int c=col;c<col+len;c++){
                sum+=mat[r][c];
            }
        }
        return sum;
    }
    public int maxSideLength(int[][] mat, int threshold) {
        int answer=0;
        int m=mat.length;
        int n=mat[0].length;

        // 이분 탐색으로 길이만 찾는다면 나머지는 손쉽게 판단할 수 있다.
        int minlen=0;
        int maxlen=Math.min(m,n);
        int len=0;

        while(minlen<=maxlen){
            len=(minlen+maxlen)/2;
            boolean found=false;

            // 이건 출발 좌표 정하기
            L: for(int row=0;row<=m-len;row++){
                for(int col=0;col<=n-len;col++){
                    // 여기서부터는 더하기
                    int sum=makesum(mat,len,row,col);
                    if(sum<=threshold){
                        found=true;
                        break L;
                    }
                }   
            }
            
            if(found){
                answer=len;
                minlen=len+1;
            } else{
                maxlen=len-1;
            }
        } 
        return answer;
    }
}