class Solution {

    public int[] minBitwiseArray(List<Integer> nums) {
        int n=nums.size();
        int[] ans=new int[n];
        Arrays.fill(ans,-1);

        for(int i=0;i<nums.size();i++){
            // x or (x+1) = num이 되게 해야 한다.
            for(int a=0;a<nums.get(i);a++){
                if((a|(a+1))==nums.get(i)){
                    ans[i]=a;
                    break;
                }
            }
        }
        return ans;
    }
}