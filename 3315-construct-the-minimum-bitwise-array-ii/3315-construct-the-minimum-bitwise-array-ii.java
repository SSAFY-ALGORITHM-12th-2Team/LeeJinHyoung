class Solution {
    // 마지막 끝자리가 1인 경우
    private int isLastdigitOne(int target){
        int ans=-1;
        int other=1;
        while ((target & other) != 0) {
            ans = target - other;
            other <<= 1;
        }
        return ans;
    }
    public int[] minBitwiseArray(List<Integer> nums) {
        // n은 100까지 밖에 안된다.
        int n=nums.size();
        int[] ans=new int[n];

        for(int num=0;num<n;num++){
            // 그렇다면 비트를 완전 쪼개서 대응하는 수밖에 없다.
            int target=nums.get(num);
            ans[num]=isLastdigitOne(target);
        }
        return ans;
    }
}