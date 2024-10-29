class Solution {
    static int[][] graph;
    public void selection(String str,int score){
        if(str.equals("RT")){
            if(score<4){
                graph[0][0]+=4-score;
            } else if(score>4){
                graph[0][1]+=score-4;
            }
        } else if(str.equals("TR")){
            if(score<4){
                graph[0][1]+=4-score;
            } else if(score>4){
                graph[0][0]+=score-4;
            }
        } else if(str.equals("CF")){
            if(score<4){
                graph[1][0]+=4-score;
            } else if(score>4){
                graph[1][1]+=score-4;
            }
        } else if(str.equals("FC")){
            if(score<4){
                graph[1][1]+=4-score;
            } else if(score>4){
                graph[1][0]+=score-4;
            }
        } else if(str.equals("JM")){
            if(score<4){
                graph[2][0]+=4-score;
            } else if(score>4){
                graph[2][1]+=score-4;
            }
        } else if(str.equals("MJ")){
            if(score<4){
                graph[2][1]+=4-score;
            } else if(score>4){
               graph[2][0]+=score-4;
            }
        } else if(str.equals("AN")){
            if(score<4){
                graph[3][0]+=4-score;
            } else if(score>4){
                graph[3][1]+=score-4;
            }
        } else if(str.equals("NA")){
            if(score<4){
                graph[3][1]+=4-score;
            } else if(score>4){
                graph[3][0]+=score-4;
            }
        }
    }
    public String solution(String[] survey, int[] choices) {
        String answer="";
        graph=new int[4][2];
        
        for(int i=0;i<survey.length;i++){
            selection(survey[i],choices[i]);
        }
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
        
        if(graph[0][0]>=graph[0][1]){
            answer+="R";
        } else if(graph[0][0]<graph[0][1]){
            answer+="T";                
        } 
        if(graph[1][0]>=graph[1][1]){
            answer+="C";                
        } else if(graph[1][0]<graph[1][1]){
            answer+="F";
        }
        if(graph[2][0]>=graph[2][1]){
            answer+="J"; 
        } else if(graph[2][0]<graph[2][1]){
            answer+="M";
        }
        if(graph[3][0]>=graph[3][1]){
            answer+="A"; 
        } else if(graph[3][0]<graph[3][1]){
            answer+="N";
        }
        
        return answer;
    }
}