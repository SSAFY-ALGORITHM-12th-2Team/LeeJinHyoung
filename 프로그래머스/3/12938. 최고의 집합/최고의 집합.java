import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer={};
        if(n>s){
            answer=new int[1];
            answer[0]=-1;
            return answer;
        }
        int total=s;
        answer=new int[n];
//         O(n)인데 어디서 시간 초과가 난다는거지..?
        for(int i=n;i>0;i--){
            int div=total/i;
            total-=div;
            answer[n-i]=div;;
        }
        return answer;
    }
}