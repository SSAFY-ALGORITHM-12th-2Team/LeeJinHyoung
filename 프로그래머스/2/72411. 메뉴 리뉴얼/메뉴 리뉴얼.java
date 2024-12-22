import java.util.*;
class Solution {
    private static HashMap<String,Integer> map;
    
    private static class Node implements Comparable<Node>{
        String key;
        int value;
        public Node(String key,int value){
            this.key=key;
            this.value=value;
        }
        
        public int compareTo(Node o){
            return o.value-this.value;
        }
    }
    
    private static void powerSet(String[] order,boolean[] used,int idx){
        if(idx==used.length){
            String key="";
            for(int i=0;i<order.length;i++){
                if(used[i]){
                    key+=order[i];
                }                    
            }
            char[] charArray=key.toCharArray();
            Arrays.sort(charArray);
            key = new String(charArray);
            if(map.containsKey(key)){
                map.replace(key,map.get(key)+1);
            } else{
                map.put(key,1);
            }
            return;
        }
        used[idx]=true;
        powerSet(order,used,idx+1);
        used[idx]=false;
        powerSet(order,used,idx+1);
    }
    
    public String[] solution(String[] orders, int[] course) {        
        //문제의 요지 : course의 수대로 조합할 수 있는 코스 요리의 조합 중 가장 손님이 많이 주문한 코스요리를 골라라
        //요컨대 2개짜리 고를 때 AC와 AD를 고를 수 있을 텐데 AC는 4명 AD는 2명밖에 안되므로 AC를 선택한다
        // HashMap으로 선택해서 부분집합을 모두 넣으면 가능한가?? 2^20인데  1024*1024면 104만이니까 충분히 가능할 듯 하다
        map=new HashMap<String,Integer>();
        
        for(int i=0;i<orders.length;i++){
            String[] orderArr=orders[i].split("");
            powerSet(orderArr,new boolean[orderArr.length],0);
        }// 부분집합을 돌려서 Map을 채운다.
        
        Iterator iter=map.keySet().iterator();
        
        ArrayList<Node>[] candidate=new ArrayList[course.length];
        for(int i=0;i<candidate.length;i++){
            candidate[i]=new ArrayList<Node>();
        } 
        
        while(iter.hasNext()){
            String next=(String)iter.next();
            for(int i=0;i<course.length;i++){
                if(next.length()==course[i]){
                    candidate[i].add(new Node(next,map.get(next)));
                    break;
                }
            }            
        }//1번 순회할 때 가능한 경우의수를 후보군에 넣는다.
        //이때 길이가 일치하지 않는 것들은 버린다
        ArrayList<Node>ans=new ArrayList<>();
        for(int i=0;i<candidate.length;i++){
            Collections.sort(candidate[i]);
            if(!candidate[i].isEmpty()){
                if(candidate[i].get(0).value>=2){
                    ans.add(candidate[i].get(0));
                }                
                for(int j=1;j<candidate[i].size();j++){
                    if(candidate[i].get(j).value>=2&&ans.get(ans.size()-1).value==candidate[i].get(j).value){
                        ans.add(candidate[i].get(j));
                    } else{
                        break;
                    }
                }
            }           
        }//정렬을 하면 가장 많은 부분집합 횟수를 가진 조합이 나오게 된다 
        
        
        String[] answer=new String[ans.size()];
        for(int i=0;i<answer.length;i++){
            answer[i]=ans.get(i).key;
        }
        Arrays.sort(answer);
        return answer;
    }
}