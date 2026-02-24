class Solution {
    public boolean isPrimeNumber(int n){
        if(n<2){
            return false; // 1은 소수가 아니기에 false
        }else{
            for(int i = 2; i < n; i++){
                if(n % i == 0) return false; // 나머지연산을 했을 때 0이 나오면 소수가 아니므로 false
            }
            return true; // 위의 case
        }
    }
    public int countPrimeSetBits(int left, int right) {
        int answer=0;
        for(int i=left;i<=right;i++){
            if(isPrimeNumber(Integer.bitCount(i))){
                answer++;
            }   
        }
        return answer;
    }
}