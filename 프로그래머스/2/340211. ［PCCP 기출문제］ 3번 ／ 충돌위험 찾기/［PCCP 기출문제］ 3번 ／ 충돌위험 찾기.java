import java.awt.*;
import java.util.*;
class Solution {    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        ArrayList<Point>[] robot=new ArrayList[routes.length];//로봇당 가는 경로를 저장할 인접리스트
        for(int i=0;i<robot.length;i++){
            robot[i]=new ArrayList<>();
            robot[i].add(new Point(points[routes[i][0]-1][0],points[routes[i][0]-1][1]));
            //System.out.println(robot[i].get(0).x+" "+robot[i].get(0).y);
        }//초기 시작값 세팅
        
        for(int i=0;i<routes.length;i++){
            for(int j=1;j<routes[i].length;j++){
                int next_r=points[routes[i][j]-1][0];//routes[i][j]가 다음 위치
                int next_c=points[routes[i][j]-1][1];
                while(next_r!=robot[i].get(robot[i].size()-1).x){
                    if(next_r<robot[i].get(robot[i].size()-1).x){
                        robot[i].add(new Point(robot[i].get(robot[i].size()-1).x-1,robot[i].get(robot[i].size()-1).y));
                    } else if(next_r>robot[i].get(robot[i].size()-1).x){
                        robot[i].add(new Point(robot[i].get(robot[i].size()-1).x+1,robot[i].get(robot[i].size()-1).y));
                    }
                } 
                while(next_c!=robot[i].get(robot[i].size()-1).y){
                    if(next_c<robot[i].get(robot[i].size()-1).y){
                        robot[i].add(new Point(robot[i].get(robot[i].size()-1).x,robot[i].get(robot[i].size()-1).y-1));
                    } else if(next_c>robot[i].get(robot[i].size()-1).y){
                        robot[i].add(new Point(robot[i].get(robot[i].size()-1).x,robot[i].get(robot[i].size()-1).y+1));
                    }
                }
            }
        }//여기까지 한다면 시간 순서대로 인접리스트에 좌표가 들어가게 된다.
        // for(ArrayList<Point>list:robot){
        //     for(Point p:list){
        //         System.out.print(p.x+" "+p.y);
        //     }
        //     System.out.println();
        // } 
        
        while(true){
            HashMap<Point,Integer>map=new HashMap<>();
            for(ArrayList<Point>list:robot){
                if(list.isEmpty()){
                    continue;
                }
                Point cur=list.remove(0);
                if(map.containsKey(cur)){
                    map.replace(cur,map.get(cur)+1);
                } else if(!map.containsKey(cur)){
                    map.put(cur,1);
                }                
            }
            if(map.isEmpty()){
                break;
            }
            for(Integer i:map.values()){
                if(i>=2){
                    answer++;
                }
            }
            map.clear();            
        }
        
        
        
        
        return answer;
    }
}