class Solution {
    public int minimumDeletions(String s) {
        char[] split=s.toCharArray();
        int acnt[]=new int[split.length];
        int bcnt[]=new int[split.length];
        int acount=0;
        int bcount=0;
        for(int i=0;i<split.length;i++){
            acnt[i]=acount;
            bcnt[split.length-1-i]=bcount;
            if(split[i]=='b'){
                bcount++;
            }
            if(split[split.length-1-i]=='a'){
                acount++;
            }
        }
        int answer=split.length;
        for(int i=0;i<split.length;i++){
            System.out.println(acnt[i]+" "+bcnt[i]);
            answer=Math.min(answer,acnt[i]+bcnt[i]);
        }
        return answer;
    }
}