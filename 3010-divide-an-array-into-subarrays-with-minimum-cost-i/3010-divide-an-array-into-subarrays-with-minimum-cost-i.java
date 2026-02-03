class Solution {
    public int minimumCost(int[] nums) {
        int first=nums[0];
        int answer=Integer.MAX_VALUE;

        for(int j=1;j<nums.length-1;j++){
            for(int k=j+1;k<nums.length;k++){
                answer=Math.min(first+nums[j]+nums[k],answer);
            }
        }
        
        return answer;
    }
}