import java.util.*;
class Solution {
    private static int[] network;
    
    private static void init(int n){
        network=new int[n];
        for(int i=0;i<network.length;i++){
            network[i]=i;
        }
    }
    
    private static int find(int a){
        if(network[a]==a){
            return a;
        }
        return network[a]=find(network[a]);
    }
    
    private static void union(int a,int b){
        int rootA=find(a);
        int rootB=find(b);
        if(rootA!=rootB) network[rootB]=rootA;
    }
    
    public int solution(int n,int[][] computers){
        int answer=0;
        
        init(n); 
        
        for(int i=0;i<computers.length;i++){
            for(int j=i;j<computers[i].length;j++){
                if(computers[i][j]==1){
                    union(i,j);
                }
            }
        }
        for(int i=0;i<n;i++){
            System.out.print(i+" ");
            if(network[i]==i)
                answer++;
        }
        return answer;
    }
//     public int solution(int n, int[][] computers) {
//         int answer = 0;
//         List<Integer>[] network=new ArrayList[n];
        
//         for(int i=0;i<n;i++){
//             network[i]=new ArrayList<Integer>();
//         }         
        
//         for(int row=0;row<computers.length;row++){
//             for(int col=0;col<computers[row].length;col++){
//                 if(row!=col&&computers[row][col]==1){
//                     network[row].add(col);
//                 }
//             }
//         }
// //         네트워크 연결 초기화
//         boolean[] vis=new boolean[n];
        
//         for(int vertex=0;vertex<n;vertex++){
//             if(vis[vertex])
//                 continue;
//             else{
//                 Queue<Integer> q=new ArrayDeque<Integer>();
                                    
//                 q.offer(vertex);
//                 answer++;
                
//                 while(!q.isEmpty()){
//                     int poll=q.poll();
                    
//                     if(vis[poll])
//                         continue;
                    
//                     vis[poll]=true;

                    
//                     for(int i=0;i<network[poll].size();i++){
//                         if(!vis[network[poll].get(i)])
//                             q.offer(network[poll].get(i));
//                     }
//                 }
//             }                
//         }
        
        
//         return answer;
//     }
}