import java.util.*;
class Solution {
    static int[] answer;
    static int[][] dice;
    
    static int AWin;
    static int[] A;
    static int[] B;
    
    static HashMap<Integer,Integer>hashmapA;
    static HashMap<Integer,Integer>hashmapB;
    
    private static void aSum(int[] sel,int idx,int[] A){
        if(idx==sel.length){
            int sum=0;
            for(int i:sel){
                sum+=i;
            }
            if(hashmapA.containsKey(sum)){
                hashmapA.replace(sum,hashmapA.get(sum)+1);
            } else {
                hashmapA.put(sum,1);
            }
            return;
        }
        for(int i=0;i<6;i++){
            sel[idx]=dice[A[idx]][i];
            aSum(sel,idx+1,A);
        }                
    }    
    private static void bSum(int[] sel,int idx,int[] B){//고른 B의 주사위를 돌리면 나올 수 있는 눈의 합
        if(idx==sel.length){
            int sum=0;
            for(int i:sel){
                sum+=i;
            }
            if(hashmapB.containsKey(sum)){
                hashmapB.replace(sum,hashmapB.get(sum)+1);
            } else {
                hashmapB.put(sum,1);
            }
            return;
        }
        for(int i=0;i<6;i++){
            sel[idx]=dice[B[idx]][i];
            bSum(sel,idx+1,B);
        }
    }
    // public static void aCount(int[] sel,int idx,int[] A){//A가 선택한 주사위로 뽑을 수 있는 경우의 수
    //     if(sel.length==idx){
    //         int sum=0;//A가 뽑은 수의 합
    //         for(int i:sel){
    //             sum+=i;
    //         }
    //         int a=0;
    //         return;
    //     }
    //     for(int i=0;i<6;i++){
    //         sel[idx]=dice[A[idx]][i];
    //         aCount(sel,idx+1,A);
    //     }
    // }    
    public void selDice(int[] sel,boolean[] vis,int idx,int start){//주사위를 고른다
        if(idx==sel.length){
            //sel은 A가 선택한 경우의 수
            A=Arrays.copyOf(sel,sel.length);//A가 선택한 주사위
            B=new int[sel.length];
            int bIdx=0;
            for(int i=0;i<dice.length;i++){
                if(!vis[i]){
                    B[bIdx++]=i;
                }
            }//A가 선택하지 않아 B가 선택한 주사위
            //여기까지는 검증 완
            hashmapA=new HashMap<Integer,Integer>();
            hashmapB=new HashMap<Integer,Integer>();
            aSum(new int[sel.length],0,A);
            bSum(new int[sel.length],0,B);            
            //B값은 HashMap으로 뽑고 값 나올때마다 for문 돌리자
            //승리할 확률이므로 무승부는 안세도 된다 
            Iterator iterA=hashmapA.keySet().iterator();
            
            int cnt=0;
            while(iterA.hasNext()){
                int itera=(int)iterA.next();
                Iterator iterB=hashmapB.keySet().iterator();
                while(iterB.hasNext()){
                    int iterb=(int)iterB.next();
                    //System.out.print(iterb+" ");
                    if(itera>iterb)
                        cnt+=hashmapA.get(itera)*hashmapB.get(iterb);
                }
                //System.out.println();
            }
            //System.out.println("cnt"+cnt);
            if(AWin<cnt){
                for(int i=0;i<answer.length;i++){
                    answer[i]=sel[i]+1;
                }
                AWin=cnt;
            }

            return;
        }
        for(int i=start;i<dice.length;i++){//조합 코드(주사위는 중복이 불가하므로)
            if(!vis[i]){
                vis[i]=true;
                sel[idx]=i;
                selDice(sel,vis,idx+1,i+1);                
                vis[i]=false;
            }
        }  
    }
    public int[] solution(int[][] dice) {
        answer=new int[dice.length/2];//주사위는 A가 선택한 갯수만큼 나와야 하므로
        AWin=Integer.MIN_VALUE;
        this.dice=dice;//깊은 복사
        selDice(new int[dice.length/2],new boolean[dice.length],0,0);//주사위를 선택하자
        
        return answer;
    }
}