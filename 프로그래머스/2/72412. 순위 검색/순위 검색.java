import java.util.*;
class Solution {
    private static HashMap<String,ArrayList<Integer>>db=new HashMap<>();
    private static String[][] wildCard;
    private static int[] answer;

    private static void modifyQuery(String[] querySplit,int minScore,int index,int queryStart){
        String query=String.join(" ",querySplit);
        if(!query.contains("-")&&queryStart==querySplit.length){
            // 단순 선형 순회는 안된다
            // 이미 정렬 되어 있으므로 하한선만 찾으면 된다.
            if(db.get(query)==null) // 어차피 빈 해시맵은 찾을 필요 없다
                return;
            ArrayList<Integer>scoreList=db.get(query);
            int low=0;
            int high=scoreList.size()-1;
            while(low<=high){
                int mid=(low+high)/2;
                if(scoreList.get(mid)<minScore){
                    low=mid+1;
                } else {
                    high=mid-1;
                }
            }
            if(low<scoreList.size() && scoreList.get(low)>=minScore){
                answer[index]+=scoreList.size()-low;
            }
            return;
        }
        // 이제 여기서부터는 와일드카드가 있는 query 처리를 하면 된다.
        // 단 DFS로 처리를 해서 해결 해야 한다.
        // 그래야 와일드 카드의 전체를 들여다볼 수 있기 때문 
        else {
            if(querySplit[queryStart].equals("-")){
                for(int j=0;j<wildCard[queryStart].length;j++){
                    querySplit[queryStart]=wildCard[queryStart][j];
                    modifyQuery(querySplit,minScore,index,queryStart+1);
                    querySplit[queryStart]="-";
                }
            } else{
                modifyQuery(querySplit,minScore,index,queryStart+1);
            }
        }
    }

    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];

        wildCard=new String[4][];
        wildCard[0]=new String[]{"cpp", "java", "python"};
        wildCard[1]=new String[]{"backend", "frontend"};
        wildCard[2]=new String[]{"junior", "senior"};
        wildCard[3]=new String[]{"chicken", "pizza"}; // 와일드 카드 처리를 위한 배열 생성

        // info 내용에 따라서 DB에 저장
        for(int i=0;i<info.length;i++){
            String[] array=info[i].split(" ");
            List<String> combinations = new ArrayList<>();
            for (int mask = 0; mask < (1 << 4); mask++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    if ((mask & (1 << j)) == 0) {
                        sb.append("- ");
                    } else {
                        sb.append(array[j]).append(" ");
                    }
                }
                combinations.add(sb.toString().trim());
            }
            int score = Integer.parseInt(array[4]);
            for (String combination : combinations) {
                db.computeIfAbsent(combination, k -> new ArrayList<>()).add(score);
            }
        }

        // 전체 DB를 정렬
        for (ArrayList<Integer> scoreList : db.values()) {
            Collections.sort(scoreList);
        }

        // query에 따라서 결괏값 도출
        for(int i=0;i<query.length;i++){
            String[] querySplit=query[i].split(" ");
            String modifiedQuery="";
            for(int j=0;j<6;j++){
                if(querySplit[j].equals("and"))
                    continue;
                modifiedQuery+=querySplit[j]+" ";
            }
            modifiedQuery+=querySplit[6];
            int minScore=Integer.parseInt(querySplit[7]);
            String[] q=modifiedQuery.split(" ");
            modifyQuery(q,minScore,i,0);
        }
        return answer;
    }
}