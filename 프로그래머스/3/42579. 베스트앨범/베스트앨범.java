import java.util.*;

class Solution {
    static class totalPlay implements Comparable<totalPlay>{
        int play;    
        
        int songIndex;
        
        public totalPlay(int play,int songIndex){
            this.play=play;
            this.songIndex=songIndex;
        }
        
        public int compareTo(totalPlay o){
            return o.play-this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
//         우선 장르별 재생 횟수를 구해 내림차순 정렬한다.
        Map<String,Integer> totalGenre=new HashMap<String,Integer>();
        
        for(int i=0;i<genres.length;i++){
            if(totalGenre.containsKey(genres[i]))
                totalGenre.replace(genres[i],totalGenre.get(genres[i])+plays[i]);
            else
                totalGenre.put(genres[i],plays[i]);
        }
        List<String> keySet = new ArrayList<>(totalGenre.keySet());
//         장르 내림차순 정렬
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return totalGenre.get(o2).compareTo(totalGenre.get(o1));
            }
        });     
        
        Map<String,PriorityQueue<totalPlay>> genreToSong=new HashMap<>();
        
        for(int i=0;i<genres.length;i++){
            if(!genreToSong.containsKey(genres[i])){
                PriorityQueue<totalPlay> pq=new PriorityQueue<>();
                pq.offer(new totalPlay(plays[i],i));
                genreToSong.put(genres[i],pq);
            } else{
                PriorityQueue<totalPlay> pq=genreToSong.get(genres[i]);
                pq.offer(new totalPlay(plays[i],i));
                genreToSong.replace(genres[i],pq);
            }
        }
        
        ArrayList<Integer> temp=new ArrayList<Integer>();
        
        for(String s:keySet){
            PriorityQueue<totalPlay> pq=genreToSong.get(s);
            for(int cnt=0;cnt<2;cnt++){
                if(!pq.isEmpty())
                    temp.add(pq.poll().songIndex);
            }
        }
        int[] answer=new int[temp.size()];
        for(int i=0;i<answer.length;i++){
            answer[i]=temp.get(i);
        }
        return answer;        
    }
}