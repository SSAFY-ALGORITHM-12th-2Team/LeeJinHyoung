import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] parent = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int root = 0;
        for (int i = 0; i < N; i++) {
            if (parent[i] == -1) {
                root = i;
                continue;
            }
            tree[parent[i]].add(i);
        }

        System.out.println(dfs(root));
    }

    static int dfs(int node) {
        List<Integer> times = new ArrayList<>();
        for (int child : tree[node]) {
            times.add(dfs(child));
        }

        // 자식 중 오래 걸리는 순으로 먼저 전파
        times.sort(Collections.reverseOrder());

        int maxTime = 0;
        for (int i = 0; i < times.size(); i++) {
            maxTime = Math.max(maxTime, times.get(i) + i + 1);
        }

        return maxTime;
    }
}
