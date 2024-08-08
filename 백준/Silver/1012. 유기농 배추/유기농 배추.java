import java.util.*;
import java.util.stream.Stream;
import java.io.*;
class Node {
	int row;
	int column;
	
	Node(int r,int c){
		this.row=r;
		this.column=c;
	}
}
public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
			
		for(int i=0;i<T;i++) {			
			int[] MN=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int[][] field=new int[MN[1]][MN[0]];
			boolean[][] vis=new boolean[MN[1]][MN[0]];
			
			int[] dX= {1,0,-1,0};
			int[] dY= {0,1,0,-1};
			
			for(int j=0;j<MN[1];j++) {
				Arrays.fill(field[i], 0);
				Arrays.fill(vis[i], false);
			}//field 초기화
			
			Queue<Node> q=new LinkedList<>();
			for(int j=0;j<MN[2];j++) {
				int[] pos=Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int column=pos[0];
				int row=pos[1];		
				field[row][column]=1;				
			}// 배추 위치 입력 받아 1 넣기
			
			//입력 끝
			int lava=0;
			
			for(int j=0;j<MN[1];j++) {
				for(int k=0;k<MN[0];k++) {					
					if(field[j][k]==1&&vis[j][k]==false) {
						lava++;
						q.offer(new Node(j,k));
					}
					
					while(!q.isEmpty()) {
						Node cur=q.poll();
						for(int l=0;l<4;l++) {
							int nX=cur.row+dX[l];
							int nY=cur.column+dY[l];
							
							if(nX<0||nY<0||nX>=MN[1]||nY>=MN[0])continue;
							if(vis[nX][nY]==true ||field[nX][nY]==0)continue;
							
							vis[nX][nY]=true;
							q.offer(new Node(nX,nY));							
						}
					}
					
				}
			}
			System.out.println(lava);			
		}	
	}
}