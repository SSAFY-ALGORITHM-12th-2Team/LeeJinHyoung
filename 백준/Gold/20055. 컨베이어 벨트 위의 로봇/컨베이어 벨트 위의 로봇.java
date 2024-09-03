import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static ArrayList<Node> belt;
	static int N, K;

	static class Node {
		boolean robot;
		int strength;

		protected Node(boolean robot, int strength) {
			super();
			this.robot = robot;
			this.strength = strength;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		K = input[1];

		int[] input1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		belt = new ArrayList<Node>();

		for (int i = 1; i <= input1.length; i++) {
			belt.add(new Node(false, input1[i - 1]));
		} // 로봇이 올라오지 않고 내구도를 입력

		int Answer = 0;

		while (check()) {
			roll();// 벨트 돌리고
			move();// 로봇 옮기고
			ride();// 로봇 태우고
			Answer++;
//			for (int i = 0; i < belt.size(); i++) {
//				System.out.print(belt.get(i).strength + " " + belt.get(i).robot);
//			}
//			System.out.println();
//			System.out.println(Answer);
		}
		System.out.println(Answer);
	}

	public static boolean check() {// 내구도가 0인 벨트 체크
		int cnt = 0;
		for (Node n : belt) {
			if (n.strength == 0)
				cnt++;
			if (cnt >= K)// 내구도가 K 이상이므로
				return false;
		}
		return true;
	}

	public static void ride() {
		if (belt.get(0).strength > 0) {// 내구도가 0보다 크면
			belt.get(0).robot = true;// 로봇 태우고
			belt.get(0).strength--;// 내구도 뺀다
		}
	}

	public static void roll() {// 1번 연산 : 컨베이어 벨트를 따라 이동(이 경우에는 내구도 변화 없음)
		belt.add(0, belt.remove(belt.size() - 1));
		belt.get(belt.size() / 2).robot = false;
	}

	public static void move() {// 로봇이 스스로 움직여서 다음 칸으로 진출
		belt.get(belt.size() / 2 - 1).robot = false;// 내리는 위치에 도달했기에
		for (int i = belt.size() / 2 - 1; i > 0; i--) {
			if (belt.get(i - 1).robot == true/* 이전에 로봇이 있어야하고 */
					&& belt.get(i).robot == false/* 현재 로봇이 없어야하고 */
					&& belt.get(i).strength > 0/* 내구도가 0보다 커야한다 */) {
				belt.get(i).robot = belt.get(i - 1).robot;
				belt.get(i).strength--;
				belt.get(i - 1).robot = false;
			}
		}
	}
}