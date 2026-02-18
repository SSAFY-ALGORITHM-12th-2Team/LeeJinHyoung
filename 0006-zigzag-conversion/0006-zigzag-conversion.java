class Solution {
    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }
        StringBuilder[] row=new StringBuilder[numRows];
        for(int i=0;i<row.length;i++){
            row[i]=new StringBuilder();
        }
        int r=0;
        boolean isDown=true;

        String[] sSplit=s.split("");
        for(String str:sSplit){
            if(isDown){
                row[r].append(str);
                if(r==numRows-1){
                    isDown=false;
                    r--;
                } else{
                    r++;
                }
            } else{
                row[r].append(str);
                if(r==0){
                    isDown=true;
                    r++;
                } else{
                    r--;
                }
            }
        }
        StringBuilder answer=new StringBuilder();
        for(int i=0;i<numRows;i++){
            answer.append(row[i]);
        }
        return answer.toString();
    }
}