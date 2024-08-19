import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	private static int[] price;
	private static int[] month;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			price = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			month = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			min = price[3];
			// 우선 1년 이용권으로 결제할 경우 생각해보자

			// 여기까지 해서 순수하게 하나의 이용권으로 이용 경우는 제외

			// 어떻게 해야 전수조사를 돌리지..?
			// DFS 돌리지 뭐
			// 잠만 DFS 돌리면 달은 확실히 들어가는데 그 달이 연속할지 어떻게 알지?
			dfs(0, 0);
			System.out.println("#" + tc + " " + min);
		}
	}// 54:11

	private static void dfs(int month_idx, int money) {
		// TODO Auto-generated method stub
		if (month_idx >= 12) {
			min = Math.min(min, money);
			return;
		}
		// 1일 이용권으로 해당 달을 이용할 경우
		// 1달 이용권으로 해당 달을 이용할 경우
		// 3달 이용권으로 해당 달을 이용할 경우

		dfs(month_idx + 1, money + (month[month_idx] * price[0]));
		dfs(month_idx + 1, money + price[1]);
		dfs(month_idx + 3, money + price[2]);
		// 각 가지에서 dfs를 파고들어야 한다
	}
}