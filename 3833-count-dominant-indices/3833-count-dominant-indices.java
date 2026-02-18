class Solution {
    public int dominantIndices(int[] nums) {
        int answer=0;
        int n=nums.length;

        int[] sum=new int[n];
        sum[n-1]=nums[nums.length-1];
        
        for(int i=n-2;i>=0;i--){
            sum[i]=sum[i+1]+nums[i];
        }

        for(int i=0;i<n;i++){
            if(nums[i]>sum[i]/(n-i)){
                answer++;
            }
        }
        return answer;
    }
}