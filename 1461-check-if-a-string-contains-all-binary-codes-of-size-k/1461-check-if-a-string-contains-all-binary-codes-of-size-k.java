class Solution {
    public boolean hasAllCodes(String s, int k) {
        boolean[] check=new boolean[(int)Math.pow(2,k)];

        for(int i=0;i<s.length()-k+1;i++){
            String sub=s.substring(i,i+k);
            check[Integer.parseInt(sub,2)]=true;
        }

        for(boolean v:check){
            if(!v)
                return false;
        }
        return true;
    }
}