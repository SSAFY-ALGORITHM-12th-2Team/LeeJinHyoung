import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] meetings = new int[n][2];

        for (int i = 0; i < n; i++) {
            meetings[i][0] = sc.nextInt(); // 시작 시간
            meetings[i][1] = sc.nextInt(); // 끝 시간
        }

        // 끝나는 시간 기준 정렬
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]); // 끝나는 시간 같으면 시작 시간 오름차순
            return Integer.compare(a[1], b[1]);
        });

        int count = 0;
        int endTime = 0;

        for (int[] meeting : meetings) {
            if (meeting[0] >= endTime) {
                count++;
                endTime = meeting[1];
            }
        }

        System.out.println(count);
    }
}
