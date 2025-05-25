import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        List<Integer>[] network=new ArrayList[n];
        
        for(int i=0;i<n;i++){
            network[i]=new ArrayList<Integer>();
        }         
        
        for(int row=0;row<computers.length;row++){
            for(int col=0;col<computers[row].length;col++){
                if(row!=col&&computers[row][col]==1){
                    network[row].add(col);
                }
            }
        }
//         네트워크 연결 초기화
        boolean[] vis=new boolean[n];
        
        for(int vertex=0;vertex<n;vertex++){
            if(vis[vertex])
                continue;
            else{
                Queue<Integer> q=new ArrayDeque<Integer>();
                                    
                q.offer(vertex);
                answer++;
                
                while(!q.isEmpty()){
                    int poll=q.poll();
                    
                    if(vis[poll])
                        continue;
                    
                    vis[poll]=true;

                    
                    for(int i=0;i<network[poll].size();i++){
                        if(!vis[network[poll].get(i)])
                            q.offer(network[poll].get(i));
                    }
                }
            }                
        }
        
        
        return answer;
    }
}