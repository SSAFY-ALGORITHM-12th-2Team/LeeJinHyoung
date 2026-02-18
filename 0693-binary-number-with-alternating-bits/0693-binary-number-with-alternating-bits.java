class Solution {
    public boolean hasAlternatingBits(int n) {
        String[] split=Integer.toBinaryString(n).split("");
        for(int i=1;i<split.length;i++){
            if(split[i].equals("0")&&split[i-1].equals("0")
                ||split[i].equals("1")&&split[i-1].equals("1")){
                return false;
            }
        }
        return true;
    }
}