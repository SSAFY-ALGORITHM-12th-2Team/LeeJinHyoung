
import java.util.*;
class Solution {
    private static boolean decision(String new_id){//여기서는 규칙이 맞는 아이디인지 판단
        String[] id=new_id.split("");
        if(id.length<3||id.length>15){//ID 길이 조건
            return false;
        }
        if(!new_id.matches("^[a-z0-9._-]+$")){//알파벳 소문자, 숫자,빼기, 밑줄, 마침표 문자만 사용 
            return false;
        }
        if(id[0].equals(".")||id[id.length-1].equals(".")){//처음이나 끝에 사용한 경우
            return false;
        }
        boolean cond=false;
        for(int i=0;i<id.length-1;i++){
            if(id[i].equals(".")&&id[i+1].equals(".")){
                return false;
            }
        }
        return true;
    }
    
    private String modifyID(String new_id){
        String modified=new_id;
        
        modified= modified.toLowerCase();//Step1
        String[] array=modified.split("");
        ArrayList<String> list=new ArrayList<String>(Arrays.asList(array));
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!list.get(i).matches("^[a-z0-9._-]+$")) {
                list.remove(i);
            }
        }//step2    
        
        for(int start=0;start<list.size()-1;start++){
            int end=start+1;
            while(end<list.size()&&list.get(start).equals(".")&&list.get(end).equals(".")){
                list.remove(end);
            }
        }//step3
        
        if(list.get(0).equals(".")){
            list.remove(0);
        }
        if(list.size()>0&&list.get(list.size()-1).equals(".")){
            list.remove(list.size()-1);
        }//step4
        
        if(list.size()==0){
            list.add("a");
        }//step5
        if(list.size()>=16){
            for(int i=list.size()-1;i>=15;i--){
                list.remove(i);
            }
            if(list.size()>0&&list.get(list.size()-1).equals(".")){
                list.remove(list.size()-1);
            }
        }//step6
        if(list.size()<=2){
            while(list.size()<3){
                list.add(list.get(list.size()-1));                
            }
            
        }//step7
        modified= String.join("",list);
        return modified;
    }
    
    public String solution(String new_id) {
        String answer = "";      
        
        boolean rightID=decision(new_id);
        if(rightID){
            return new_id;
        }            
        else{
            return modifyID(new_id);
        }
    }
}