import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer>st=new Stack<Integer>();
        
        int n=order.length;
        int idx=0;
        
        for(int i=1;i<=n;i++){//우선 1부터 n까지 모든 컨베이어 벨트의 끝을 간다
             
            if(i==order[idx]){//컨베이어벨트에 있을 경우
                answer++;
                idx++;                          
            } else{
                st.push(i);
            }  
            while(!st.isEmpty()&&st.peek()==order[idx]){ //한번 순회했으면 스택을 비워가면서 넣을 수 있는지 확인한다
                answer++;
                idx++;
                st.pop();
            }       
        }
        
        return answer;
    }
}