import java.util.*;
import java.io.*;

public class Main {
	private static class SegmentTree {
		long tree[]; // 각 원소가 담길 트리
		int treeSize; // 트리의 크기

		public SegmentTree(int arrSize) {
			// 트리 높이 구하기
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			// 높이를 이용한 배열 사이즈 구하기
			this.treeSize = (int) Math.pow(2, h + 1);
			// 배열 생성
			tree = new long[treeSize];
		}

		public long init(long[] array, int node, int start, int end) {

			// 배열의 시작과 끝이 같다면 leaf 노드이므로
			// 원소 배열 값 그대로 담기
			if (start == end) {
				return tree[node] = array[start];
			}

			// leaf 노드가 아니면, 자식노드 합 담기
			return tree[node] = init(array, node * 2, start, (start + end) / 2)
					+ init(array, node * 2 + 1, (start + end) / 2 + 1, end);
		}

		public long sum(int node, int start, int end, int left, int right) {
			// 범위를 벗어나게 되는 경우 더할 필요 없음
			if (left > end || right < start) {
				return 0;
			}

			// 범위 내 완전히 포함 시에는 더 내려가지 않고 바로 리턴
			if (left <= start && end <= right) {
				return tree[node];
			}

			// 그 외의 경우 좌 / 우측으로 지속 탐색 수행
			return sum(node * 2, start, (start + end) / 2, left, right)
					+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}

		public void update(int node, int start, int end, int idx, long diff) {
			// 만약 변경할 index 값이 범위 바깥이면 확인 불필요
			if (idx < start || end < idx)
				return;

			// 차를 저장
			tree[node] += diff;

			// 리프노드가 아니면 아래 자식들도 확인
			if (start != end) {
				update(node * 2, start, (start + end) / 2, idx, diff);
				update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int[] cond = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = cond[0];
		int Q = cond[1];
		SegmentTree tree = new SegmentTree(N);
		long[] inputArray = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

		long[] array = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			array[i] = inputArray[i - 1];
		}
		tree.init(array, 1, 1, N);
		for (int i = 0; i < Q; i++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			int x = input[0];
			int y = input[1];
			int a = input[2];
			int b = input[3];

//			left와 right 크기 비교 후 작은 수를 left로 초기화
			int left = x;
			int right = y;
			if (x > y) {
				left = y;
				right = x;
			}
//			입력이 int 한계라면 합은 long일 것이다.
			long sum = tree.sum(1, 1, N, left, right);
			answer.append(sum + "\n");
			long diff = b - array[a];
			array[a] = b;
			tree.update(1, 1, N, a, diff);
		}
		System.out.println(answer);
	}
}