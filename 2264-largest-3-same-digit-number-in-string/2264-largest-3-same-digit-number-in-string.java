class Solution {
    public String largestGoodInteger(String num) {
        int answer_num=-1;
        String answer="";
        String[] split=num.split("");

        for(int i=0;i<split.length-2;i++){
            if(split[i].equals(split[i+1])&&split[i+1].equals(split[i+2])){
                StringBuilder temp=new StringBuilder(split[i]);
                temp.append(split[i+1]);
                temp.append(split[i+2]);

                answer_num=Math.max(answer_num,Integer.parseInt(temp.toString()));
            }
        }
        if(answer_num==-1){
            return answer;
        }
        return answer_num==0?String.valueOf("000"): String.valueOf(answer_num);
    }
}