class Solution {
    public boolean checkOnesSegment(String s) {
        int number=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0')
                continue;
            while(i<s.length()&&s.charAt(i)=='1'){
                i++;
            }
            number++;
        }
        return number==1?true:false;
    }
}