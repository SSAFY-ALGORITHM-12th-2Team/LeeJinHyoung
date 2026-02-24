class Solution {
    public int binaryGap(int n) {
        int answer=0;
        char binary[]=Integer.toBinaryString(n).toCharArray();

        int prev=-1;

        for(int i=0;i<binary.length;i++){
            if(prev==-1&&binary[i]=='1'){
                prev=i;
            } else if(binary[i]=='1'){
                answer=answer>i-prev?answer:i-prev;
                prev=i;
            }
        }
        return answer;
    }
}