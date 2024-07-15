import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int H = input[0];
			int W = input[1];
			String[][] map = new String[H][];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().split("");
			}
			int N = Integer.parseInt(br.readLine());
			String[] order = br.readLine().split("");// 입력 끝

			int tank_row = 0;
			int tank_col = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j].equals("<") || map[i][j].equals(">") || map[i][j].equals("v")
							|| map[i][j].equals("^")) {
						tank_row = i;
						tank_col = j;
					}
				}
			} // 탱크 찾아라

			for (int i = 0; i < N; i++) {
				if (order[i].equals("U")) {
					map[tank_row][tank_col] = "^";
					int ny = tank_row - 1;
					if (ny >= 0 && map[ny][tank_col].equals(".")) {
						map[ny][tank_col] = "^";
						map[tank_row][tank_col] = ".";
						tank_row = ny;
					}
				} else if (order[i].equals("D")) {
					map[tank_row][tank_col] = "v";
					int ny = tank_row + 1;
					if (ny < H && map[ny][tank_col].equals(".")) {
						map[ny][tank_col] = "v";
						map[tank_row][tank_col] = ".";
						tank_row = ny;
					}
				} else if (order[i].equals("L")) {
					map[tank_row][tank_col] = "<";
					int nx = tank_col - 1;
					if (nx >= 0 && map[tank_row][nx].equals(".")) {
						map[tank_row][nx] = "<";
						map[tank_row][tank_col] = ".";
						tank_col = nx;
					}
				} else if (order[i].equals("R")) {
					map[tank_row][tank_col] = ">";
					int nx = tank_col + 1;
					if (nx < W && map[tank_row][nx].equals(".")) {
						map[tank_row][nx] = ">";
						map[tank_row][tank_col] = ".";
						tank_col = nx;
					}
				} else if (order[i].equals("S")) {
					if (map[tank_row][tank_col].equals("^")) {
						int bullet_row = tank_row;
						while(bullet_row>=0) {
							if(map[bullet_row][tank_col].equals("*")) {
								map[bullet_row][tank_col]=".";								
								break;
							} else if(map[bullet_row][tank_col].equals("#")) {								
								break;
							} else {
								bullet_row--;
							}
						}
					} else if (map[tank_row][tank_col].equals("v")) {
						int bullet_row = tank_row;
						while(bullet_row<H) {
							if(map[bullet_row][tank_col].equals("*")) {
								map[bullet_row][tank_col]=".";								
								break;
							} else if(map[bullet_row][tank_col].equals("#")) {								
								break;
							} else {
								bullet_row++;
							}
						}
					} else if (map[tank_row][tank_col].equals(">")) {
						int bullet_col = tank_col;
						while(bullet_col<W) {
							if(map[tank_row][bullet_col].equals("*")) {
								map[tank_row][bullet_col]=".";
								break;
							} else if(map[tank_row][bullet_col].equals("#")) {
								break;
							} else {
								bullet_col++;
							}
						}
					} else if (map[tank_row][tank_col].equals("<")) {
						int bullet_col = tank_col;
						while(bullet_col>=0) {
							if(map[tank_row][bullet_col].equals("*")) {
								map[tank_row][bullet_col]=".";
								break;
							} else if(map[tank_row][bullet_col].equals("#")) {
								break;
							} else {
								bullet_col--;
							}
						}
					}
				}
			} // 명령별 수행

			System.out.print("#" + tc + " ");
			for (String[] i : map) {
				for (String j : i) {
					System.out.print(j);
				}
				System.out.println();
			}
		}
	}
}