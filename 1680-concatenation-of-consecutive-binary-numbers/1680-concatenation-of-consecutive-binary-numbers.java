class Solution {
    public int concatenatedBinary(int n) {
        StringBuilder sb=new StringBuilder();

        for(int i=1;i<=n;i++){
            sb.append(Integer.toBinaryString(i));
            long value=Long.parseLong(sb.toString(),2)%(long)(Math.pow(10,9)+7);
            sb.setLength(0);
            sb.append(Long.toBinaryString(value));
        }

        return Integer.parseInt(sb.toString(),2);
    }
}