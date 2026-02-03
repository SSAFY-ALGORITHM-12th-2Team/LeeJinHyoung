class Solution {
    private boolean isSorted(List<Integer> list){
        int size=list.size();
        if(size==1){
            return true;
        }
        for(int i=1;i<size;i++){
            if(list.get(i-1)>list.get(i)){
                return false;
            }
        }
        return true;
    }
    private List<Integer> doSum(List<Integer> list){
        int size=list.size();
        int minIndex=0;
        int minValue=list.get(0)+list.get(1);
        for(int i=0;i<size-1;i++){
            int sum=list.get(i)+list.get(i+1);
            if(minValue>sum){
                minValue=sum;
                minIndex=i;
            }
        }
        list.remove(minIndex);
        list.remove(minIndex);
        list.add(minIndex,minValue);
        return list;
    }
    public int minimumPairRemoval(int[] nums) {
        int answer=0;
        List<Integer> list=new ArrayList<Integer>();

        for(int i:nums){
            list.add(i);
        }

        while(!isSorted(list)){
            answer++;
            list=doSum(list);
        }
        return answer;
    }
}