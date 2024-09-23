class Solution {
    static int[][] dungeons;
    static int max;
    
    private void perm(int[] arr,boolean[] vis,int cnt,int k){
        if(cnt==arr.length){
            int c=0;
            for(int i=0;i<arr.length;i++){
                if(k>=dungeons[arr[i]][0]){
                    k-=dungeons[arr[i]][1];
                } else{
                    max=Math.max(c,max);
                    return;
                }
                c++;
            }
            for(int i:arr){
                System.out.print(i);
            }
            max=Math.max(max,c);
            return;
        }
        for(int i=0;i<vis.length;i++){
            if(!vis[i]){
                vis[i]=true;
                arr[cnt]=i;
                perm(arr,vis,cnt+1,k);
                vis[i]=false;
            }
        }        
    }    
    
    public int solution(int k, int[][] dungeons) {
        this.dungeons=dungeons;//깊은 복사하고
        max=0;
        perm(new int[dungeons.length],new boolean[dungeons.length],0,k);
        
        return max;
    }
}