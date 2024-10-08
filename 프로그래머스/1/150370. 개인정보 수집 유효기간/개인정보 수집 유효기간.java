import java.util.*;
import java.util.stream.Stream;
class Solution {
    static class info{
        int year;
        int month;
        int day;
        public info(int year,int month,int day){
            this.year=year;
            this.month=month;
            this.day=day;
        }        
    }
    static class term{
        String name;
        int month;
        public term(String name,int month){
            this.name=name;
            this.month=month;
        }
    } 
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        List<Integer>a=new ArrayList<Integer>();
        String[] todaysplit=today.split("\\.");     
        info todayDate=new info(Integer.parseInt(todaysplit[0]),Integer.parseInt(todaysplit[1]),Integer.parseInt(todaysplit[2]));
        
        HashMap<String,Integer> termsMap=new HashMap<>();
        
        for(String s:terms){
            String[] split=s.split(" ");
            termsMap.put(split[0],Integer.parseInt(split[1]));
        }
        
        for(int i=0;i<privacies.length;i++){
            String[] split=privacies[i].split(" ");
            String date=split[0];
            String[] dateSplit=date.split("\\.");
            info validDate=new info(Integer.parseInt(dateSplit[0]),Integer.parseInt(dateSplit[1])+termsMap.get(split[1]),Integer.parseInt(dateSplit[2])-1);
            if(validDate.day<1){
                validDate.day=28;
                validDate.month--;    
                if(validDate.month<1){
                    validDate.month=12;
                    validDate.year--;
                }
            }            
            if(validDate.month>12){
                int year=validDate.month/12;
                validDate.month%=12;
                validDate.year+=year;
            }//12월 넘으면 연 하나 넘겨준다
            if(validDate.month<1){
                validDate.month=12;
                validDate.year--;
            }
            System.out.println("유효기간"+validDate.year+"."+validDate.month+"."+validDate.day);
            System.out.println("오늘날짜"+todayDate.year+"."+todayDate.month+"."+todayDate.day);
            
            if(validDate.year<todayDate.year){
                a.add(i+1);
            }else if(validDate.year==todayDate.year&&validDate.month<todayDate.month){
                a.add(i+1);
            } else if(validDate.year==todayDate.year&&validDate.month==todayDate.month&&validDate.day<todayDate.day){
                a.add(i+1);
            }
        }
        for(int i:a){
            System.out.println(i);
        }
        answer=a.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}