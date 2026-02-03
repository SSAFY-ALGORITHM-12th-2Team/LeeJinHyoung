/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode answer=new ListNode(0);
        answer.next=new ListNode();
        ListNode curr=answer.next;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            
            // 현재 자릿수의 합 계산
            int sum = carry + x + y;
            carry = sum / 10; // 다음으로 넘길 올림수 (10 이상이면 1, 아니면 0)
            
            // 결과 리스트에 새 노드 추가 (1의 자리만 저장)
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            // 다음 노드로 이동
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return answer.next.next;
    }
}