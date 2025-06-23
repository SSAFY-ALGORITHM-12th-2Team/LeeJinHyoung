import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        int[] sortedA=A.clone();
        int[] sortedB=B.clone();
        
        Arrays.sort(sortedA);
        Arrays.sort(sortedB);
        
        boolean[] used=new boolean[A.length];
        
        int bHead=0;
        int bTail=sortedB.length-1;
        
        for(int i=sortedA.length-1;i>=0;i--){
            if(sortedA[i]>sortedB[bTail]){
                used[bHead++]=true;
            } else if(sortedA[i]==sortedB[bTail]){
                used[bTail]=true;
            } else if(sortedA[i]<sortedB[bTail]){
                used[bTail--]=true;
                answer++;
            }
        }
        return answer;
    }
}