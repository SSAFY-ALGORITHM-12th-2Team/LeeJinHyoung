class Solution {
    // Not으로 바꾸는 과정
    public String invert(String temp){
        char[] arr=temp.toCharArray();
        StringBuilder ret=new StringBuilder();
        for(char c :arr){
            if(c=='0'){
                ret.append("1");
            } else if(c=='1'){
                ret.append("0");
            }
        }
        return ret.reverse().toString();
    }

    public String makeNthBit(StringBuilder s){
        String inverted=invert(s.toString());
        s.append("1");
        s.append(inverted);
        return s.toString();
    }

    public char findKthBit(int n, int k) {
        StringBuilder s=new StringBuilder("0");

        for(int i=2;i<=n;i++){
            String str=makeNthBit(s);
            s.setLength(0);
            s.append(str);
        }
        
        return s.charAt(k-1);
    }
}