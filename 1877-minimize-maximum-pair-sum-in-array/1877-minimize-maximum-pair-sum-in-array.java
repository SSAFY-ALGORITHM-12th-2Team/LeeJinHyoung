import java.util.PriorityQueue;

class Solution {
    public int minPairSum(int[] nums) {
        // nums의 갯수는 짝수고 10만 이하이다.
        // 어떻게 해야 통과가 되나?
        int answer=0;
        Arrays.sort(nums);
        for(int i=0;i<nums.length/2;i++){
            answer=Math.max(answer,nums[i]+nums[nums.length-1-i]);
        }
        return answer;
    }
}