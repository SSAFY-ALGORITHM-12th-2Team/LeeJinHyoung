class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;        
        int lastAttack=attacks[attacks.length-1][0];
        
        int idx=0;
        int count=0;
        System.out.println(0+" "+health);
        for(int time=1;time<=lastAttack;time++){
            if(attacks[idx][0]==time){
                answer-=attacks[idx][1];
                count=0;
                idx++;
            } else{
                count++;
                if(count==bandage[0]){
                    answer+=bandage[2];
                    count=0;
                }  
                if(answer+bandage[1]<=health){
                    answer+=bandage[1];
                } else{
                    answer=health;
                }
            }
            if(answer<=0){
                return -1;
            }  
            System.out.println(time+" "+answer);
        }
        System.out.println("health"+answer);
        if(answer<=0){
            return -1;
        }        
        return answer;
    }
}