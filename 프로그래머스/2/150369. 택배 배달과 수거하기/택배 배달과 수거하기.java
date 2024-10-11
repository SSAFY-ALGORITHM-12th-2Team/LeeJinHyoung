import java.util.*;
class Solution {
    //전략 : 가장 멀리 있는 집에 있는 물품을 다 쳐낸다
    static class home{
        int d,p;
        public home(int d,int p){
            this.d=d;
            this.p=p;
        }
    }
    static class truck{
        int d,p,cap;
        public int getCap(){
            return cap-(d+p);
        }
        public truck(int d,int p,int cap){
            this.d=d;
            this.p=p;
            this.cap=cap;
        }
    }
    private static int remainD(ArrayList<home> list){
        int sum=0;
        for(home h : list){
            sum+=h.d;
        }
        return sum;
    }
    private static int remainP(ArrayList<home> list){
        int sum=0;
        for(home h : list){
            sum+=h.p;
        }
        return sum;
    }    
    private static void print(ArrayList<home>neighbor){
        for(home h:neighbor){
            System.out.print(h.d+"/"+h.p+" ");
        }
        System.out.println();
    }    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        ArrayList<home>neighbor=new ArrayList<home>();
        for(int i=0;i<n;i++){
            neighbor.add(new home(deliveries[i],pickups[i]));
        }
        truck t;
        int rd=remainD(neighbor),rp=remainP(neighbor);
        int idx=0;
        int size=neighbor.size();
        while(rd>0||rp>0){
            t=new truck(rd>=cap?cap:rd,0,cap);//트럭 객체 생성 (매번 새 트럭 보내자)
            // print(neighbor);
            //System.out.println("rd rp"+rd+" "+rp);
            for(int i=size-1;i>=0;i--){//더이상 필요없는 집은 지워라
                if(neighbor.get(i).d==0&&neighbor.get(i).p==0){
                    neighbor.remove(i);
                    size--;
                } else {
                    break;
                }
            }
            if(rd>0){
            rd-=t.d;
            for(int i=size-1;t.d>0&&i>=0;i--){//적재한 걸 다 배달하지 못하면 최대 거리 집을 출발점으로 계속 돈다
                if(t.d>=neighbor.get(i).d){//트럭에 실린 양이 가구당 배달양보다 많이 실리면
                    t.d-=neighbor.get(i).d;
                    neighbor.get(i).d=0;
                } else{//트럭에 실린 양이 가구당 배달양보다 적다면
                    neighbor.get(i).d-=t.d;
                    t.d=0;
                }
            }
            }//System.out.println("rd rp"+rd+" "+rp);
            // 배달은 cap만큼 비워낸다
            // System.out.println("배송이 끝났습니다");
            // print(neighbor);
            // System.out.println(t.d+" "+t.p+" "+t.cap);
            if(rp>0){
            for(int i=size-1;t.p<cap&&i>=0;i--){
                if(t.p+neighbor.get(i).p<=cap){//트럭에 실린 양과 가구 수거양이 최대 적재량보다 적거나 같다면
                    t.p+=neighbor.get(i).p;
                    neighbor.get(i).p=0;
                } else{//트럭에 실린양과 가구 수거 양이 최대 적재양보다 많다면(가구 수거양중 일부만 수거해야 할 때)
                    neighbor.get(i).p-=cap-t.p;
                    t.p=cap; 
                }
            }
            rp-=t.p;
            }
            // System.out.println("수거가 끝났습니다");
            // print(neighbor);
            // System.out.println(t.d+" "+t.p+" "+t.cap);
            //System.out.println("rd rp"+rd+" "+rp);
            answer+=2*size;
            // System.out.println(answer);
        }
        return answer;
    }
}