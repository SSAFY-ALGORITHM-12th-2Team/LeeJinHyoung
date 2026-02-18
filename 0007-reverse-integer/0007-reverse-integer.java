class Solution {
    public int reverse(int x) {
        StringBuilder answer=new StringBuilder();
        long origin=x;
        if(x<0){
            origin*=-1;
        }

        int[] array=Arrays.stream(String.valueOf(origin).split("")).mapToInt(Integer::parseInt).toArray();
        for(int i:array){
            answer.insert(0,i);
        }
        if(x<0){
            answer.insert(0,"-");
        }
        long a=Long.parseLong(answer.toString());
        if(a>Integer.MAX_VALUE||a<Integer.MIN_VALUE){
            return 0;
        }
        return Integer.parseInt(answer.toString());
    }
}