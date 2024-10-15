import java.util.*;
class Solution {
    //그래프 문제다
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        HashMap<String,Integer> indices=new HashMap<String,Integer>();
        for(int i=0;i<friends.length;i++){
            indices.put(friends[i],i);
        }
        
        int[][] adjMatrix= new int[friends.length][friends.length];
        for(int i=0;i<gifts.length;i++){
            String[] split=gifts[i].split(" ");
            adjMatrix[indices.get(split[0])][indices.get(split[1])]++;
        }//이번달까지의 기록
        
        int[][] giftIndex=new int[friends.length][2];
        for(int give=0;give<friends.length;give++){
            int sum=0;
            for(int take=0;take<friends.length;take++){
                sum+=adjMatrix[give][take];
            }
            giftIndex[give][0]+=sum;
        }
        for(int take=0;take<friends.length;take++){
            int sum=0;
            for(int give=0;give<friends.length;give++){
                sum+=adjMatrix[give][take];
            }
            giftIndex[take][1]+=sum;
        }//선물지수 표 만들기
        
        int[] nextMonth=new int[friends.length];
        
        for(int i=0;i<friends.length;i++){
            for(int j=i+1;j<friends.length;j++){
                if(adjMatrix[i][j]>adjMatrix[j][i]){
                    nextMonth[i]++;
                } else if(adjMatrix[i][j]<adjMatrix[j][i]){
                    nextMonth[j]++;
                } 
                else if((adjMatrix[i][j]==0&&adjMatrix[j][i]==0)||(adjMatrix[i][j]==adjMatrix[j][i])){
                    if(giftIndex[i][0]-giftIndex[i][1]>giftIndex[j][0]-giftIndex[j][1]){
                        nextMonth[i]++;
                    } else if(giftIndex[i][0]-giftIndex[i][1]<giftIndex[j][0]-giftIndex[j][1]){
                        nextMonth[j]++;
                    } else if(giftIndex[i][0]-giftIndex[i][1]==giftIndex[j][0]-giftIndex[j][1]){
                        continue;//이때는 없다
                    }
                }
            }
        }
        
        for(int i:nextMonth){
            answer=Math.max(i,answer);
            System.out.print(i+" ");
        }
        return answer;
    }
}