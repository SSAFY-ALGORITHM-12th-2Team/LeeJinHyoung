import java.util.*;
import java.io.*;

public class Main {
	private static boolean[] vis;

	private static class Node {
		int root;
		Node left;
		Node right;

		public Node(int root) {
			this.root = root;
		}

		public void insert(int num) {
			if (num < this.root) {// 입력이 루트보다 작다면
				if (this.left == null) {// 루트의 왼쪽 자식 노드가 없다면
					this.left = new Node(num);
				} else// 왼쪽 자식 노드가 있다면 재귀
					this.left.insert(num);
			} else {// 입력이 루트보다 크다면
				if (this.right == null) {// 루트의 오른쪽 자식노드가 없다면
					this.right = new Node(num);
				} else// 루트의 오른쪽 자식 노드가 있다면
					this.right.insert(num);
			}
		}
	}

	public static void postOrder(Node node) {
		if (node == null) {
			return; // 노드가 null이면 바로 종료
		}

		// 왼쪽 서브트리 순회
		postOrder(node.left);
		// 오른쪽 서브트리 순회
		postOrder(node.right);

		// 현재 노드 출력 (현재 노드의 값 또는 root 필드)
		System.out.println(node.root); // `root`는 노드의 root 값을 나타내는 필드로 가정
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫 번째 입력을 루트 노드로 설정
		String firstInput = br.readLine();
		if (firstInput == null || firstInput.isEmpty()) {
			return; // 입력이 비어있으면 프로그램 종료
		}
		Node root = new Node(Integer.parseInt(firstInput));

		// 루프를 통해 입력 처리
		String input;
		while ((input = br.readLine()) != null && !input.isEmpty()) { // null 또는 빈 줄일 때 종료
			int num = Integer.parseInt(input);
			root.insert(num);
		}

		// 후위 순회 출력
		postOrder(root);
	}

}