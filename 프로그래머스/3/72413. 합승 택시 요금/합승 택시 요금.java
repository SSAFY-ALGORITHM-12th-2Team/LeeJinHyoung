import java.util.*;
class Solution {
    private static List<Node>[] adjList;
    
    private static class Node implements Comparable<Node>{
        int dest;
        int cost;
        public Node(int dest,int cost){
            this.dest=dest;
            this.cost=cost;
        }
        public int compareTo(Node o){
            return this.cost-o.cost;
        }
    }
    
    private static int[] dijkstra(int start,int n){
        PriorityQueue<Node> pq=new PriorityQueue<>();//우선순위 큐 
        pq.offer(new Node(start,0));        
        int[] fare=new int[n+1];//운임요금 저장할 배열
        Arrays.fill(fare,Integer.MAX_VALUE);
        fare[start]=0;
        
        while(!pq.isEmpty()){
            Node cur=pq.poll();            
            for(Node node: adjList[cur.dest]){
                if(fare[node.dest]>fare[cur.dest]+node.cost){
                    fare[node.dest]=fare[cur.dest]+node.cost;
                    pq.offer(node);
                }
            }            
        }
        return fare;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        adjList=new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            adjList[i]=new ArrayList<Node>();
        }
                
        for(int i=0;i<fares.length;i++){
            int[] Edge=fares[i];
            adjList[Edge[0]].add(new Node(Edge[1],Edge[2]));
            adjList[Edge[1]].add(new Node(Edge[0],Edge[2]));//무방향 그래프
        }//Graph 만들기
        //전략 : 합승을 안하는 경우 + 합승을 하는 경우를 고려한다?
        //시작점을 제외하고 모두 다익스트라를 한번 돌려서 각 정점에서 a,b에 도달하는 경우의수를 모두 탐색한다.
        //요컨대 시작점-합승 끝 -각자 가는 구간 이렇게 다익스트라 구간
        
        int[][] totalCost=new int[3][n+1];//s-임의의 점으로 가는 비용
        totalCost[0]=dijkstra(s,n);
        totalCost[1]=dijkstra(a,n);
        totalCost[2]=dijkstra(b,n);
        
        //합승을 안하는 경우
        int separate=totalCost[0][a]+totalCost[0][b];        
        //합승을 하는 경우
        for(int i=1;i<=n;i++){
            int togetherCost=totalCost[0][i]+totalCost[1][i]+totalCost[2][i];
            answer=Math.min(togetherCost,answer);
        }
        answer=Math.min(separate,answer);
        return answer;
    }
}