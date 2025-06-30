import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	private static int N;
	private static node tree;

	static class node {
		String root;
		node left;
		node right;

		public node() {
			super();
		}

		public node(String root, node left, node right) {
			super();
			this.root = root;
			this.left = left;
			this.right = right;
		}
	}

	public static node findRoot(String target) {
//		항상 루트 노드는 A이기에 
		Queue<node> q = new ArrayDeque<>();
		q.offer(tree);

		while (!q.isEmpty()) {
			node curNode = q.poll();

			if (curNode.root.equals(target))
				return curNode;

			if (curNode.left != null)
				q.offer(curNode.left);
			if (curNode.right != null)
				q.offer(curNode.right);
		}
		return null;
	}

	public static void pre(node n) {
		System.out.print(n.root);
		if (n.left != null)
			pre(n.left);
		if (n.right != null)
			pre(n.right);
	}

	public static void inorder(node n) {
		if (n.left != null)
			inorder(n.left);
		System.out.print(n.root);
		if (n.right != null)
			inorder(n.right);
	}

	public static void post(node n) {
		if (n.left != null)
			post(n.left);

		if (n.right != null)
			post(n.right);
		System.out.print(n.root);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		tree = new node("A", null, null);
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
//			BFS해서 붙일 노드를 찾는다.
			if (findRoot(input[0]) == null)
				continue;
			node curRoot = findRoot(input[0]);
			if (!input[1].equals((".")))
				curRoot.left = new node(input[1], null, null);
			if (!input[2].equals((".")))
				curRoot.right = new node(input[2], null, null);
		}
//		삽입 완료

//		1. 전위 순회부터 시작
		pre(tree);
		System.out.println();
		inorder(tree);
		System.out.println();
		post(tree);
	}
}