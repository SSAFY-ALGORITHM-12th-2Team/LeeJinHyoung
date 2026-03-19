class Solution {
    public int bitwiseComplement(int n) {
        if(n==0){
            return 1;
        }
        int bitLength = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        int mask = (1 << bitLength) - 1;
        int result = n ^ mask;
        return result;
    }
}