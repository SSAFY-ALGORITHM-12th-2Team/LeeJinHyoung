class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int min=0;
        int max=Integer.MAX_VALUE;
        
        int lev=0;
        
        while(min+2<=max&&min<max){
            lev=(min+max)/2;
            long time=0;    
            if(diffs[0]<=lev){            
                time+=times[0];        
            } else{      
                time+=(times[0])*(diffs[0]-lev)+times[0];        
            }         
            for(int i=1;i<diffs.length;i++){     
                if(diffs[i]<=lev){             
                    time+=times[i];            
                } else{                
                    time+=(times[i]+times[i-1])*(diffs[i]-lev)+times[i];         
                }        
            }
            if(time<limit){
                max=lev;
            } else{
                min=lev;
            }
        }
        long time=0;
        if(diffs[0]<=min){            
            time+=times[0];        
        } else{      
            time+=(times[0])*(diffs[0]-min)+times[0];        
        }         
        for(int i=1;i<diffs.length;i++){     
            if(diffs[i]<=min){             
                time+=times[i];            
            } else{                
                time+=(times[i]+times[i-1])*(diffs[i]-min)+times[i];         
            }              
        }
        System.out.println(time);
        if(min==0){
            return 1;
        } else if(time<=limit){
            return min;
        } else{
            return max;
        }
    }
}