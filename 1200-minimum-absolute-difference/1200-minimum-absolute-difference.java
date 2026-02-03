class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        TreeMap<Integer,List<List<Integer>>> map=new TreeMap<>();

        for(int a=0;a<arr.length-1;a++){
            int b=a+1;
            if(map.containsKey(arr[b]-arr[a])){
                List<Integer> temp=new ArrayList<>();
                temp.add(arr[a]); temp.add(arr[b]);
                List<List<Integer>> list=map.get(arr[b]-arr[a]);
                list.add(temp);
                map.replace(arr[b]-arr[a],list);
            } else{
                List<Integer> temp=new ArrayList<>();
                temp.add(arr[a]); temp.add(arr[b]);
                List<List<Integer>> list=new ArrayList<>();
                list.add(temp);
                map.put(arr[b]-arr[a],list);
            }
        }
        Iterator it=map.keySet().iterator();
        int key=0;
        if(it.hasNext())
            key=(int)it.next();
        return map.get(key);
    }
}