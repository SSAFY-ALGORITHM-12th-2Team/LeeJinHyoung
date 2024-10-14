class Solution {
    //10,20,30,40퍼센트로 할인율이 달라지고 이모티콘마다 할인율이 다를 수도 있다 
    static int[][] emoPrice;
    static int[][] userGlobal;
    static int[] answer;
    
    static int i=0;

    private void recursive(int[] sel,boolean[] vis,int[] per,int idx){//이 함수는 누가 몇 퍼센트로 파는 물건을, 얼마나 사는지를 알 수 있다.
        if(idx==sel.length){
            //이렇게 한다면 sel에는 가격이 per에는 퍼센트가, idx에는 재귀 깊이가 들어간다
            int emoPlus=0;
            int total=0;
            
            for(int u=0;u<userGlobal.length;u++){//모든 user를 돈다
                int sum=0;//인당 구매비              
                for(int i=0;i<sel.length;i++){
                    if(userGlobal[u][0]<=per[i]){//기준 할인율보다 높으면 산다
                        sum+=sel[i];
                    }
                }
                if(sum>=userGlobal[u][1]){//만약 인당 구매비가 플러스 기준비를 넘으면 플러스를 산다
                    emoPlus++;
                    sum=0;
                }
                total+=sum;
            }
            if(emoPlus>answer[0] || (emoPlus==answer[0]&&total>answer[1])){
                answer[0]=emoPlus;
                answer[1]=total;
            }
            i++;
            return;
        }
        
        for(int j=1;j<=4;j++){
            if(!vis[idx]){
                vis[idx]=true;
                sel[idx]=emoPrice[idx][j];
                per[idx]=j*10;
                recursive(sel,vis,per,idx+1);                
                vis[idx]=false;
            }
        }
        // for(int i=0;i<sel.length;i++){//
        //     for(int j=1;j<=4;j++){
        //         if(!vis[i]){
        //             vis[i]=true;
        //             sel[i]=emoPrice[i][j];
        //             per[i]=j*10;
        //             recursive(sel,vis,per,idx+1);
        //             vis[i]=false;
        //         }
        //     }            
        // }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer=new int[2];
        emoPrice=new int[emoticons.length][5];//할인 포함 이모티콘 가격
        userGlobal=users;//유저 멤버 변수
        
        for(int i=0;i<emoticons.length;i++){
            int salesPer=10;
            emoPrice[i][0]=emoticons[i];
            for(int j=1;j<=4;j++){
                emoPrice[i][j]=emoticons[i]-emoticons[i]*salesPer/100;
                salesPer+=10;
            }
        }
        for(int i=0;i<emoPrice.length;i++){
            for(int j=0;j<emoPrice[i].length;j++){
                System.out.print(emoPrice[i][j]+" ");
            }
            System.out.println();
        }
        recursive(new int[emoticons.length],new boolean[emoticons.length],new int[emoticons.length],0);
        System.out.println(i);
        return answer;
    }
}