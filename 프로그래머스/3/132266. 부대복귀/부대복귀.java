import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
//         인접리스트 초기화
        List<Integer> adjList[]=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            adjList[i]=new ArrayList<Integer>();
        }
        
//         입력 완료
        for(int i=0;i<roads.length;i++){
//             양방향 그래프
            adjList[roads[i][0]].add(roads[i][1]);
            adjList[roads[i][1]].add(roads[i][0]);
        }
//        n번의 BFS를 진행하자
        boolean[] vis;
        int[] dist;
        for(int i=0;i<sources.length;i++){
            if(destination==sources[i]){
                answer[i]=0;
            } else{
                vis=new boolean[n+1];
                dist=new int[n+1];
            
                Queue<Integer> q=new ArrayDeque<>();
            
                q.offer(sources[i]);
                
                dist[sources[i]]=0;
                vis[sources[i]]=true;
                
                while(!q.isEmpty()){
                    int poll=q.poll();                     
//                      목적지라면 BFS 탈출
                    if(dist[destination]!=0)
                        break;
                    
                    for(int next:adjList[poll]){
                        if(!vis[next]){
                            vis[next]=true;
                            dist[next]=dist[poll]+1;
                            q.offer(next);
                        }
                    }
                }
                answer[i]=dist[destination]==0?-1:dist[destination];
            }
        }
        return answer;
    }
}