class Solution {
    public List<Long> mergeAdjacent(int[] nums) {
        List<Long> list=new ArrayList<>();
        list.add((long)nums[0]);

        for(int i=1;i<nums.length;i++){
            list.add((long)nums[i]);
            while(list.size()>1&&list.get(list.size()-2).equals(list.get(list.size()-1))){
                long sum=(long)list.get(list.size()-2)+list.get(list.size()-1);
                
                list.remove(list.size()-1);
                list.remove(list.size()-1);
                list.add(sum);
            }
        }
        return list;
    }
}