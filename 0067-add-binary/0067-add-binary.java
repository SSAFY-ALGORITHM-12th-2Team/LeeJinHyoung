class Solution {
    public String addBinary(String a, String b) {
        StringBuilder A=new StringBuilder(a);
        StringBuilder B=new StringBuilder(b);

        while(A.length()<B.length()){
            A.insert(0,"0");
        }
        while(A.length()>B.length()){
            B.insert(0,"0");
        }
        int[] aSplit=Arrays.stream(A.toString().split("")).mapToInt(Integer::parseInt).toArray();
        int[] bSplit=Arrays.stream(B.toString().split("")).mapToInt(Integer::parseInt).toArray();

        int carry=0;
        int[] array=new int[aSplit.length];
        for(int i=A.length()-1;i>=0;i--){
            array[i]=aSplit[i]+bSplit[i]+carry;
            carry=array[i]/2;
            array[i]=array[i]%2;
        }
        
        StringBuilder answer=new StringBuilder();
        if(carry==1){
            answer.append(1);    
        }
        for(int i:array)
            answer.append(String.valueOf(i));
            
        
        return answer.toString();
    }
}