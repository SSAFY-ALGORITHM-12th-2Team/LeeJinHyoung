class Solution {
    public int sumFourDivisors(int[] nums) {
        int answer=0;

        for(int i:nums){
            int count=0;
            int root=(int)Math.sqrt(i);

            List<Integer> divisors=new ArrayList<Integer>();
            for(int div=1;div<=root;div++){
                if(i%div==0){
                    divisors.add(div);
                    if(div!=i/div){
                        divisors.add(i/div);
                    }
                }
            }
            if(divisors.size()==4){
                for(int divisor:divisors){
                    answer+=divisor;
                }
            }
        }
        return answer;
    }
}