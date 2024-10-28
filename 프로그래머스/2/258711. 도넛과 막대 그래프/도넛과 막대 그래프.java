import java.util.*;
// 아 잠만 이거 시작 정점 진출 차수가 1개만 있으면 안되는구나(그럼 그냥 막대그래프잖아)
//근데 3개는 아닐 수도 있고 맞을 수도 있다 
// 2개나 3개일 때 가능
//단 진입 차수는 0개여야지

//그리고 시작정점은 저 성질이 많이 있을 수 없음
//도넛 그래프는 무조건 root가 아닌 이상 진입,진출이 1개
//막대 그래프는 마지막이 진출차수가 0

//막대 모양 그래프 : 마지막이 진입차수만 존재하고, 진출 차수는 없다
//8자 모양 그래프 : 
class Solution {
    static ArrayList<Integer>[] adjlist;
    static int[] inbound,outbound;
    static int doughnut=0,stick=0,eight=0;     
    
    public void solve(int start,boolean[] v){
        if(adjlist[start].size()==0){
            //System.out.println("stick "+start);
            stick++;
            return;
        }        
        else if(inbound[start]>=2&&outbound[start]==2){
            eight++;
            //System.out.println("eight : "+start);
            return;
        } else{ 
            for(int i=0;i<adjlist[start].size();i++){
                int node=adjlist[start].get(i);
                if(v[node]){
                    doughnut++;
                    //System.out.println("doughnut : "+node);
                    return;
                }
                if(!v[node]){
                    //System.out.println(node);
                    v[node]=true;
                    solve(node,v);
                    v[node]=false;
                }
            }
        }
    }    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        adjlist=new ArrayList[1000000];
        for(int i=1;i<adjlist.length;i++){
            adjlist[i]=new ArrayList<Integer>();
        }        
        inbound=new int[adjlist.length];
        outbound=new int[adjlist.length];
        for(int i=0;i<edges.length;i++){
            adjlist[edges[i][0]].add(edges[i][1]);
            inbound[edges[i][1]]++;
            outbound[edges[i][0]]++;
        }
        boolean[] v=new boolean[adjlist.length];
        for(int i=0;i<inbound.length;i++){
            if(inbound[i]==0&&outbound[i]>=2){//해당 정점이 시작점인지 확인하는 논리를 짜야 한다 
                answer[0]=i;
                solve(i,v);
                break;
            }
        }//진출 차수가 3이고 진입 차수가 0인 정점을 찾기
        answer[1]=doughnut;
        answer[2]=stick;
        answer[3]=eight;
        
        return answer;
    }
}