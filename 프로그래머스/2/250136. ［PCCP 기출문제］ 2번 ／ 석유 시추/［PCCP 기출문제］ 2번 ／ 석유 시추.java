import java.util.*;
import java.awt.*;
class Solution {
    static int h,w;
    static int[][] map;
    static boolean[][] vis;
    
    static int[] dr={1,0,-1,0};
    static int[] dc={0,1,0,-1};
    
    static HashMap<Integer,Integer> hashmap;
    
    public static void bfs(int[][] land,int r,int c,int idx){
        ArrayList<Point> chunk=new ArrayList<>();
        Queue<Point> q=new ArrayDeque<>();
        q.offer(new Point(r,c));
        
        int oil=0;
        while(!q.isEmpty()){
            Point cur=q.poll();
            chunk.add(cur);
            for(int d=0;d<4;d++){
                int nr=cur.x+dr[d];
                int nc=cur.y+dc[d];
                
                if(nr>=0&&nc>=0&&nr<h&&nc<w&&!vis[nr][nc]&&land[nr][nc]==1){
                    vis[nr][nc]=true;
                    q.offer(new Point(nr,nc));
                    oil++;
                }
            }
        }
        hashmap.put(idx,oil==0?1:oil);  
        for(Point p:chunk){
            map[p.x][p.y]=idx;
        }          
    }    
    
    public int solution(int[][] land) {
        h=land.length;
        w=land[0].length;
        map=new int[h][w];
        hashmap=new HashMap<Integer,Integer>();
        
        vis=new boolean[land.length][land[0].length];
        int idx=1;
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[i].length;j++){
                if(land[i][j]==1&&!vis[i][j]){
                    bfs(land,i,j,idx++);
                }
            }
        }
        // for(int[] ar:map){
        //     for(int i:ar){
        //         System.out.print(i);
        //     }
        //     System.out.println();
        // }
        //여기까지 하면 각 덩어리마다 유전 인덱스를 붙인다
        //유전 인덱스 변수는 map
           
        boolean[] used=new boolean[idx];
        int[] sum=new int[w];
        //System.out.println(idx);
        for(int c=0;c<w;c++){
            for(int r=0;r<h;r++){
                if(used[map[r][c]]==false&&map[r][c]>0){
                    used[map[r][c]]=true;
                    //System.out.print(hashmap.get(map[r][c])+" ");
                    sum[c]+=hashmap.get(map[r][c]);
                }                              
            }
            //System.out.println();
            Arrays.fill(used,false);
        }
        int max=Integer.MIN_VALUE;
        for(int i:sum){
            //System.out.print(i+" ");
            max=Math.max(i,max);
        }        
        return max;
    }
}