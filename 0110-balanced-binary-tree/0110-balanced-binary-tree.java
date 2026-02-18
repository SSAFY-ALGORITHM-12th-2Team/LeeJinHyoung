class Solution {
    public boolean isBalanced(TreeNode root) {
        // -1이 반환되면 불균형, 아니면 균형
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0; // 빈 노드 높이는 0

        // 1. 왼쪽 높이 가져오기
        int left = getHeight(node.left);
        if (left == -1) return -1; // 이미 왼쪽에서 균형 깨짐

        // 2. 오른쪽 높이 가져오기
        int right = getHeight(node.right);
        if (right == -1) return -1; // 이미 오른쪽에서 균형 깨짐

        // 3. 현재 노드에서 균형 체크 (핵심!)
        if (Math.abs(left - right) > 1) return -1;

        // 4. 내 높이를 부모에게 보고 (둘 중 큰 거 + 나 자신 1)
        return Math.max(left, right) + 1;
    }
}