import java.util.*;

class Solution {
    private static boolean flag;
    private static List<String> answer;
    private static void dfs(HashMap<String,List<String>> map,String from){
        if(map.get(from)==null||map.get(from).isEmpty()){
            Iterator it=map.keySet().iterator();
            while(it.hasNext()){
                String key=(String) it.next();
                if(!map.get(key).isEmpty()){
                    return;
                }
            }
            flag=true;
            answer.add(0,from);
            return;
        }
        List<String> dest=map.get(from);
        for(int i=0;i<dest.size();i++){
            String next=dest.remove(i);
            dfs(map,next);
            dest.add(i,next);
            if(flag){
                answer.add(0,from);
                return;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
//         Map에 넣어서 선후 관계를 잡는다.
        HashMap<String,List<String>> map=new HashMap<>();
        for(String[] ticket: tickets){
            if(map.containsKey(ticket[0])){
                List<String> temp=map.get(ticket[0]);
                temp.add(ticket[1]);
                map.replace(ticket[0],temp);
            } else{
                List<String> temp=new ArrayList<>();
                temp.add(ticket[1]);
                map.put(ticket[0],temp);
            }
        }
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            String from=(String) it.next();
            Collections.sort(map.get(from));
        }
//         이 문제는 사이클이 존재한다. 사이클 파훼법이 필요함
        answer=new ArrayList<String>();
//         항상 ICN이 출발위치
        dfs(map,"ICN");
        return answer.toArray(new String[0]);
    }
}