/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Set<Integer>set;
    private int answer;
    private StringBuilder binary;

    private void recursive(TreeNode root) {
        if (root == null) return; // 방어 코드

        binary.append(root.val);

        // 리프 노드인지 확인 (자식이 둘 다 없을 때)
        if (root.left == null && root.right == null) {
            int bin = Integer.parseInt(binary.toString(), 2);
            // 여기서 set 체크 없이 바로 더해도 무방합니다 (경로 자체가 유니크하므로)
            answer += bin;
            // System.out.println(binary.toString());
        } else {
            // 리프가 아닐 때만 아래로 더 내려감
            recursive(root.left);
            recursive(root.right);
        }
        // 백트래킹: 부모로 올라가기 전 마지막 글자 삭제
        binary.setLength(binary.length() - 1);
    }
    public int sumRootToLeaf(TreeNode root) {
        answer=0;
        set=new HashSet<>();
        binary=new StringBuilder();

        recursive(root);
        return answer;
    }
}