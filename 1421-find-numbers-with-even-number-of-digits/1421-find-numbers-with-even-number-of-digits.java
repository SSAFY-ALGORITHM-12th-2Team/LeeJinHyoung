class Solution {
    public int findNumbers(int[] nums) {
        int answer=0;

        for(int i:nums){
            String s=String.valueOf(i);
            if(s.length()%2==0){
                answer++;
            }
        }
        return answer;
    }
}