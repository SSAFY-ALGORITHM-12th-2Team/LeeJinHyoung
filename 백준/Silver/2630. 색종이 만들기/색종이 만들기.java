import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	private static int N, white, blue;
	private static int[][] colorPaper;

//	같은 색인지 확인
	public static boolean checkSameColor(Point LU, Point RD) {
		int initColor = colorPaper[LU.x][LU.y];
		for (int row = LU.x; row < RD.x; row++) {
			for (int col = LU.y; col < RD.y; col++) {
				if (initColor != colorPaper[row][col])
					return false;
			}
		}
		return true;
	}

//	checkSameColor 후 같지 않으면 자른다.
	public static void recursive(Point LU, Point RD) {
		if (checkSameColor(LU, RD)) {
//			System.out.println(LU.x + " " + LU.y + " " + RD.x + " " + RD.y);
			if (colorPaper[LU.x][LU.y] == 0) {
				white++;
			} else if (colorPaper[LU.x][LU.y] == 1) {
				blue++;
			}
			return;
		}
		recursive(LU, new Point((LU.x + RD.x) / 2, (LU.y + RD.y) / 2));
		recursive(new Point(LU.x, (LU.y + RD.y) / 2), new Point((LU.x + RD.x) / 2, RD.y));
		recursive(new Point((LU.x + RD.x) / 2, LU.y), new Point(RD.x, (LU.y + RD.y) / 2));
		recursive(new Point((LU.x + RD.x) / 2, (LU.y + RD.y) / 2), RD);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		colorPaper = new int[N][N];

		for (int i = 0; i < N; i++) {
			colorPaper[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		white = 0;
		blue = 0;
		recursive(new Point(0, 0), new Point(N, N));

		System.out.println(white);
		System.out.println(blue);
	}
}