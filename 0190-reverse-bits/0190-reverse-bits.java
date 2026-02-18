class Solution {
    public int reverseBits(int n) {
        String binary=Integer.toBinaryString(n);
        StringBuilder binSb=new StringBuilder(binary);
        while(binSb.length()<32){
            binSb.insert(0,"0");
        }
        return Integer.parseInt(binSb.reverse().toString(),2);
    }
}