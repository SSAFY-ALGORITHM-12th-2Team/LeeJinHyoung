import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        int remain_time=n;
        int[] remain_works=new int[works.length];
        
        for(int i=0;i<remain_works.length;i++){
            remain_works[i]=works[i];
        }
        Arrays.sort(remain_works);
        
        L:while(remain_time>0){
            int most=remain_works[remain_works.length-1];
            
            for(int i=remain_works.length-1;i>=0;i--){
//                 남은 시간이 하나도 없는 경우
                if(remain_time==0)
                    break L;
                else if(most>remain_works[i])
                    break;
                else{
                    remain_works[i]--;
                    remain_time--;
                }
            }
        }
        
        for(int i:remain_works){
            if(i<0)
                continue;
            answer+=Math.pow(i,2);
        }
        
        return answer;
    }
}