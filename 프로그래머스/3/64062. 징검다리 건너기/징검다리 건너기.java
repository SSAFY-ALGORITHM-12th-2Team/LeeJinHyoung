class Solution {
    public boolean check(int mid, int[] stones, int k) {
        int zeroCnt = 0;
        for (int s : stones) {
            if (s - mid < 0) {
                zeroCnt++;
                if (zeroCnt >= k) return false;
            } else {
                zeroCnt = 0;
            }
        }
        return true;
    }
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int low=0;
        int high=200000000;
        
        while(low<=high){
            int mid=(low+high)/2;
            
            if (check(mid, stones, k)) { // mid명이 가능하면
                answer = mid;            // 최댓값 갱신
                low = mid + 1;            // 더 오른쪽 탐색
            } else {
                high = mid - 1;
            }
        }
        
        return answer;
    }    
}