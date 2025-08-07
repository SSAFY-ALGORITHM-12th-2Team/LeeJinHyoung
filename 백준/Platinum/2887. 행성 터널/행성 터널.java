import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int answer = 0;

    private static class Edge implements Comparable<Edge> {
        int cost, to;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static class Planet {
        int x, y, z, index;

        public Planet(int x, int y, int z, int index) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.index = index;
        }
    }

    private static Planet[] planets;
    private static List<Edge>[] adjList;

    private static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        pq.offer(new Edge(0, 0));
        
        int count = 0;
        while (!pq.isEmpty() && count < N) {
            Edge cur = pq.poll();
            
            if (visited[cur.to]) continue;
            
            visited[cur.to] = true;
            answer += cur.cost;
            count++;
            
            for (Edge next : adjList[cur.to]) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            planets[i] = new Planet(pos[0], pos[1], pos[2], i);
        }
        
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        // X, Y, Z 좌표 기준 정렬 후 인접한 행성끼리만 간선 추가
        for (int d = 0; d < 3; d++) {
            final int dim = d;
            Arrays.sort(planets, Comparator.comparingInt(p -> (dim == 0 ? p.x : (dim == 1 ? p.y : p.z))));
            
            for (int i = 0; i < N - 1; i++) {
                int cost = Math.abs(
                    (dim == 0 ? planets[i].x - planets[i + 1].x :
                     (dim == 1 ? planets[i].y - planets[i + 1].y : planets[i].z - planets[i + 1].z)));
                adjList[planets[i].index].add(new Edge(planets[i + 1].index, cost));
                adjList[planets[i + 1].index].add(new Edge(planets[i].index, cost));
            }
        }
        
        prim();
        System.out.println(answer);
    }
}
