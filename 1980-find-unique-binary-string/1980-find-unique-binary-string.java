
class Solution {
    Set<Integer> set;
    public String findDifferentBinaryString(String[] nums) {
        set=new HashSet<>();
        
        int len=nums[0].length();
        int max=(int)Math.pow(2,len+1)-1;

        for(int i=0;i<nums.length;i++){
            int num=Integer.parseInt(nums[i],2);
            set.add(num);
        }

        for(int i=0;i<=max;i++){
            if(!set.contains(i)){
                String num=Integer.toBinaryString(i);
                StringBuilder answer=new StringBuilder(num);
                System.out.println(answer);
                while(answer.length()<len){
                    answer.insert(0,"0");
                }
                return answer.toString();
            }
        }
        return "00";
    }
}