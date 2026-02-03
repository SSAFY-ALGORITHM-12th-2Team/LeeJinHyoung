import java.util.*;
class Solution {
    class Node{
        int v;
        int w;
        public Node(int v,int w){
            this.v=v;
            this.w=w;
        }
    }
    private List<Node>[] edge;

    private int dijkstra(int n) {
        // 최소 힙 (가중치가 작은 것이 우선순위)
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        boolean[] traverse=new boolean[n];

        // 1. 시작점 설정
        cost[0] = 0;
        pq.offer(new Node(0, 0)); // Node(목적지 노드 번호 v, 가중치 w)라고 가정

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.v;
            int curDist = cur.w;

            if(curIdx==n-1){
                return cost[curIdx];
            }
            // 2. 이미 방문했거나, 기존에 발견한 최단 거리보다 크면 무시
            if (cost[curIdx] < curDist) continue;

            // 3. 인접 노드 탐색
            for (Node next : edge[curIdx]) {
                // 현재 노드를 거쳐서 다음 노드로 가는 거리 계산
                int nextDist = cost[curIdx] + next.w; 

                // 기존 거리보다 짧으면 갱신
                if (nextDist < cost[next.v] && !traverse[next.v]) {
                    cost[next.v] = nextDist;
                    pq.offer(new Node(next.v, nextDist));
                }
            }
        }
        return cost[n - 1] == Integer.MAX_VALUE ? -1 : cost[n - 1];
    }

    public int minCost(int n, int[][] edges) {
        // 원하는 정답은 0->n-1까지 갈 수 있는 최소의 비용을 구하라
        // edge 생성 및 초기화
        edge=new ArrayList[n];
        for(int i=0;i<n;i++){
            edge[i]=new ArrayList<>();
        }
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];

            edge[u].add(new Node(v,w));
            edge[v].add(new Node(u,w*2));
        }
        // 역전 방문 배열 생성
        
        return dijkstra(n);
    }
}