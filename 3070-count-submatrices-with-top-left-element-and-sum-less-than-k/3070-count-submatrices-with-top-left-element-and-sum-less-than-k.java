class Solution {


    public int countSubmatrices(int[][] grid, int k) {
        int[][] sum=new int[grid.length][grid[0].length];
        int answer=0;
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(r==0&&c==0){
                    sum[r][c]=grid[r][c];
                }else if(r==0){
                    sum[r][c]=sum[r][c-1]+grid[r][c];
                } else if(c==0){
                    sum[r][c]=sum[r-1][c]+grid[r][c];
                } else{
                    sum[r][c]=sum[r-1][c]+sum[r][c-1]+grid[r][c]-sum[r-1][c-1];
                }
                
                if(sum[r][c]<=k){
                    // System.out.println(r+" "+c);
                    answer++;
                }
            }
            
        }
        // for(int[] r:sum){
        //     for(int c:r){
        //         System.out.print(c+" ");
        //     }
        //     System.out.println();
        // }
        return answer;
    }
}