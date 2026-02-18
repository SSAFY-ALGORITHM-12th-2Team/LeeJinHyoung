class Solution {
    public int myAtoi(String s) {
        StringBuilder answer=new StringBuilder();
        // 1. 공백 삭제
        s=s.trim();
        // 2. 만약 제일 앞자리에 부호가 있다면 부호를 취급하고 없다면 양수 취급
        String split[]=s.split("");

        if(split[0].equals("-")||split[0].equals("+")){
            answer.append(split[0]);
        } else if(split[0].matches("^[0-9]*$")){
            answer.append("+");
            answer.append(split[0]);
        } else{
            return 0;
        }
        // 3. Integer.parseInt()를 사용한다면 어차피 건너뛰므로 상관 없다.
        for(int i=1;i<split.length;i++){
            if(split[i].matches("^[0-9]*$")){
                answer.append(split[i]);
            } else{
                break;
            }

            if(Long.parseLong(answer.toString())>Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            } else if(Long.parseLong(answer.toString())<Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        // 4. Long.parseLong을 사용해 반올림을 한다.
        if(answer.length()==0){
            return 0;
        } else if(answer.toString().equals("+") || answer.toString().equals("-")){
            return 0;
        }
    
        return Integer.parseInt(answer.toString());
    }
}