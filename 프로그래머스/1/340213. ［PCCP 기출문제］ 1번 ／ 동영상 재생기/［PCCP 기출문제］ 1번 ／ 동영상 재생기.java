class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        String vl[]=video_len.split(":");
        String p[]=pos.split(":");
        String os[]=op_start.split(":");
        String oe[]=op_end.split(":");
        int videoLen=Integer.parseInt(vl[0])*60+Integer.parseInt(vl[1]);
        int Pos=Integer.parseInt(p[0])*60+Integer.parseInt(p[1]);
        int opStart=Integer.parseInt(os[0])*60+Integer.parseInt(os[1]);        
        int opEnd=Integer.parseInt(oe[0])*60+Integer.parseInt(oe[1]);
        
        for(int i=0;i<commands.length;i++){
            if(Pos>=opStart&&Pos<=opEnd){
                Pos=opEnd;                
            }
            System.out.println(Pos);
            if(commands[i].equals("prev")){
                if(Pos>=10){
                    Pos-=10;
                } else{
                    Pos=0;
                }
            } else if(commands[i].equals("next")){
                if(Pos<=videoLen-10){
                    Pos+=10;
                } else{
                    Pos=videoLen;
                }
            }
        }
        if(Pos>=opStart&&Pos<=opEnd){
                Pos=opEnd;                
        }
        String time=Pos/60<10?"0"+String.valueOf(Pos/60):String.valueOf(Pos/60);
        String min =Pos%60<10?"0"+String.valueOf(Pos%60):String.valueOf(Pos%60);
        answer=time+":"+min;
        
        return answer;
    }
}