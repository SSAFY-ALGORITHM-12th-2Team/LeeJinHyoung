class Solution {
    public String clearDigits(String s) {
        List<String> ary = new ArrayList<>(Arrays.asList(s.split("")));

        for(int i=0;i<ary.size();i++){
            if(ary.get(i).matches("[+-]?\\d*(\\.\\d+)?")){
                ary.remove(i);
                ary.remove(i-1);
                i-=2;
            }
        }
        StringBuilder answer=new StringBuilder();
        for(String str:ary){
            answer.append(str);
        }
        return answer.toString();
    }
}