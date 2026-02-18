class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int[] result=new int[nums.length];

        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                int dist=(i+nums[i])%nums.length;
                result[i]+=nums[dist];
            } else if(nums[i]<0){
                int dist=(i+nums[i])%nums.length;
                if(dist<0){
                    dist=nums.length+dist;
                }
                result[i]+=nums[dist];
            } else{
                result[i]+=nums[i];
            }
        }

        return result;
    }
}