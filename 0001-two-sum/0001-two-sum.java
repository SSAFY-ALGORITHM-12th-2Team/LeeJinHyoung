class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 값과 값이 저장된 인덱스
        Map<Integer,Integer> indice=new HashMap<>();
        int[] answer=new int[2];
        for(int i=0;i<nums.length;i++){
            if(indice.containsKey(target-nums[i])&&i!=indice.get(target-nums[i])){
                answer[0]=i;
                answer[1]=indice.get(target-nums[i]);
                break;
            }
            indice.put(nums[i],i);
        }
        return answer;
    }
}