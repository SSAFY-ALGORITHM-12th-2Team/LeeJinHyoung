class Solution {
    static int possible;
    public static void recursive(String[] split,int begin,int end,boolean root){        
        if(begin==end){
            //System.out.println(begin+" "+end+" "+split[begin]+" "+root);
            if(!root&&split[begin].equals("1")){
                possible=0;
            } else{
                possible=1;
            }
            return;
        }
        //System.out.println(begin+" "+end);
        int mid=(begin+end)/2;
        //System.out.println("mid"+mid);
        if(split[mid].equals("1")){
            if(!root){
                possible=0;
                return;
            }
            recursive(split,begin,mid-1,root);
            if(possible==0){
                return;
            }
            recursive(split,mid+1,end,root);
        } else if(split[mid].equals("0")){
            recursive(split,begin,mid-1,false);
            if(possible==0){
                return;
            }
            recursive(split,mid+1,end,false);
        }
    }
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        L:for(int i=0;i<numbers.length;i++){
            String binString=Long.toBinaryString(numbers[i]);
            int digit=0;
            for(digit=0;digit<50;digit++){
                if(Math.pow(2,digit)-1>=binString.length()){
                    break;
                }
            }
            String zero="";
            for(int j=0;j<Math.pow(2,digit)-1-binString.length();j++){
                zero+="0";
            }
            binString=zero+binString;
            //System.out.println(binString);
            String[] split=binString.split("");
            //인덱스가 홀수이고 0이면은 서브트리의 루트노드가 더미노드이므로 불가 근데 000111111111111 같은 경우는 반례네
            recursive(split,0,split.length-1,true);
            answer[i]=possible;
        }        
        return answer;
    }
}