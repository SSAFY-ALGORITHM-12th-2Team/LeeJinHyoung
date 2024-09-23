//전략 : 우선 1개일 때 겹치는지 확인
//만약 겹치지 않는다면 후보키 +1개
//겹친다면 다수의 컬럼을 사용했을 때 후보키 가능한지 확인 
import java.util.*;

class Solution {
    static boolean[] used;//후보키로 사용되었다면 표시한다
    static String[][] data;
    static int answer;
    static Set<String> minSet;
    
    public static int remain(){//남아있는 갯수 확인
        int i=0;
        for(boolean b : used){
            if(!b){
                i++;
            }
        }
        return i;
    }    
    
    public static void combi(int[] arr,boolean[] vis,int cnt,int start){
        //2개 이상을 조합으로 선택할 경우
        if(cnt==arr.length){            
            //조합을 짜면 만약 1,2가 성립하면 어떻게           
            Set<String>set=new TreeSet<>();
            for(int i=0;i<data.length;i++){
                StringBuilder sb=new StringBuilder("");
                for(int j=0;j<arr.length;j++){
                    sb.append(data[i][arr[j]]);
                }      
                String s=sb.toString();
                //System.out.println(s);
                if(set.contains(s)){
                    return;
                } else{
                    set.add(s);
                }
            }
            //유일성 체크 끝
            //최소성 체크를 해야한다
            
            String o="";
            for(int i=0;i<arr.length;i++){
                o+=arr[i];
            }           
            if(arr.length==1){//배열 길이가 1일때는 유일성 체크 통과시 최소성은 무조건 만족
                System.out.println("true"+o);
                answer++;
                minSet.add(o);
                return;
            }
            Iterator iter=minSet.iterator();
            L:while(iter.hasNext()){
                String e=(String)iter.next();
                String[] s=e.split("");
                boolean[] v=new boolean[s.length];
                for(int i=0;i<s.length;i++){
                    if(o.contains(s[i])){
                        v[i]=true;
                    }
                }
                boolean flag=true;
                for(int i=0;i<v.length;i++){
                    if(!v[i]){
                        flag=false;
                        break;
                    }
                }
                
                if(flag==true){
                    return;
                }
            }    
            minSet.add(o);
            answer++;
            return;
        }
        for(int i=start;i<vis.length;i++){
            if(!vis[i]){
                vis[i]=true;
                arr[cnt]=i;    
                combi(arr,vis,cnt+1,i+1);                
                vis[i]=false;
            }
        }
    }
    
    public int solution(String[][] relation) {
        answer = 0;
        data=relation;
        minSet=new TreeSet<>();
        used=new boolean[relation[0].length];
        for(int size=1;size<used.length;size++){
            //1개 이상의 키로 후보키 선정이 끝났을 때
            combi(new int[size],new boolean[used.length],0,0);//컬럼을 선택해라
        }        
        //이제 2가지가 후보키 선정이 끝났을 때
        if(answer==0){
            return answer=1;
        } else{
            return answer;
        }
    }
}