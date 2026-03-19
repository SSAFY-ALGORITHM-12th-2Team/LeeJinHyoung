class Solution {
    private void divide(StringBuilder s) {
        s.deleteCharAt(s.length() - 1);
    }
    private void add(StringBuilder s) {
        int index = s.length() - 1;

        // Iterating while the character is 1 and changing to 0
        while (index >= 0 && s.charAt(index) != '0') {
            s.setCharAt(index, '0');
            index--;
        }

        if (index < 0) {
            s.insert(0, '1');
        } else {
            s.setCharAt(index, '1');
        }
    }

    public int numSteps(String s) {
        StringBuilder bin=new StringBuilder(s);

        int answer=0;
        while(bin.length()>1){
            if(bin.charAt(bin.length()-1)=='0'){
                divide(bin);
            } else if(bin.charAt(bin.length()-1)=='1'){
                add(bin);
            }
            answer++;
        }
        return answer;
    }
}