import java.util.*;

class Solution {
    private Map<String,List<String>> adjMap;
    
    private static class word{
        String word;
        int count;
        
        public word(String w,int c){
            this.word=w;
            this.count=c;
        }
    }
    
    public void init(String a,String b){
        String[] aSplit=a.split("");
        String[] bSplit=b.split("");
        
        int d=0;
        
        for(int i=0;i<aSplit.length;i++){
            if(!aSplit[i].equals(bSplit[i])){
                d++;
            }
        }
        
        if(d==1){
            if(adjMap.containsKey(a)){
                List<String> temp=adjMap.get(a);
                temp.add(b);
                adjMap.replace(a,temp);
            } else{
                List<String> temp=new ArrayList<>();
                temp.add(b);
                adjMap.put(a,temp);
            }
        }
    }    
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        adjMap=new HashMap<>();
        
        boolean flag=false;
        for(String w:words){
            if(target.equals(w)){
                flag=true;
            }
        }
        if(!flag)
            return 0;
        
        for(int i=0;i<words.length;i++){
            init(begin,words[i]);
        }
        
        for(int i=0;i<words.length;i++){
            for(int j=0;j<words.length;j++){
                if(i==j)
                    continue;
                init(words[i],words[j]);
            }
        }
        
        Queue<word> q=new ArrayDeque<>();
        q.offer(new word(begin,0));
        
        while(!q.isEmpty()){
            word cur=q.poll();
            
            if(cur.word.equals(target)){
                answer=cur.count;
                break;
            }
            
            List<String> outBound=null;
            if(adjMap.get(cur.word)!=null){
                outBound=adjMap.get(cur.word);
                for(String s:outBound){
                    q.offer(new word(s,cur.count+1));
                }
            }
        }
        
        return answer;
    }
}