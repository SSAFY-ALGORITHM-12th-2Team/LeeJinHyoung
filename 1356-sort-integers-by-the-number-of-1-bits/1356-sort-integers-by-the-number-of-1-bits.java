class Solution {
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr)
            .boxed()
            .sorted((a,b)->{
                if(Integer.bitCount(a)==Integer.bitCount(b)){
                    return a-b;
                }
                return Integer.bitCount(a)-Integer.bitCount(b);}) 
            .mapToInt(Integer::intValue)
            .toArray();
    }
}