import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> list=new LinkedList<Integer>();
        
        for(String s: operations){
            String[] oper=s.split(" ");
            if(oper[0].equals("I")){
                list.add(Integer.parseInt(oper[1]));
                Collections.sort(list);
            } else if(oper[0].equals("D")){
                if(Integer.parseInt(oper[1])==1){
                    if(!list.isEmpty())
                        list.remove(list.size()-1);
                } else if(Integer.parseInt(oper[1])==-1){
                    if(!list.isEmpty())
                        list.remove(0);
                }
            }
        }
        int[] answer=new int[2];
        if(list.isEmpty()){
            answer[0]=0;
            answer[1]=0;
        } else{
            answer[0]=list.get(list.size()-1);
            answer[1]=list.get(0);
        }
        
        return answer;
    }
}