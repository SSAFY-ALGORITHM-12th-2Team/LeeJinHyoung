class Solution {
    public int lengthOfLongestSubstring(String s) {
        int answer=0;
        char[] array=s.toCharArray();

        for(int sp=0;sp<array.length;sp++){
            int len=0;
            // 알파벳 Check
            boolean[] alphabet=new boolean[128];

            for(int length=0;length<array.length-sp;length++){
                // char로 알파벳 확인하기
                char c=array[sp+length];
                if(alphabet[c])
                    break;
                alphabet[c]=true;
                len++;
            }
            answer=Math.max(len,answer);
        }
        return answer;
    }
}