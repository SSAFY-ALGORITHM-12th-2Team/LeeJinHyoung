class Solution {
    public int longestBalanced(String s) {
        int answer=0;
        char[] array=s.toCharArray();

        int[] alphabet=new int[26];
        for(int left=0;left<array.length;left++){
            Arrays.fill(alphabet,0);
            L:for(int right=left;right<array.length;right++){
                int index=array[right]-'a';
                alphabet[index]++;

                boolean flag=true;
                for(int a:alphabet){
                    if(a>0&&a!=alphabet[index]){
                        flag=false;
                    }
                }
                if(flag){
                    answer=Math.max(answer,(right-left+1));
                }
            }
        }

        return answer;
    }
}