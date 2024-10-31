import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		L: for (int tc = 1; tc <= T; tc++) {
			String[] p = br.readLine().split("");
			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			str = str.substring(1, str.length() - 1);
			String[] strsplit = str.split(",");

			// 명령어에 맞게 하고 문제가 생기면 에러를 낸다
			List<Integer> list = new ArrayList<Integer>();
			if (n != 0) {
				for (String s : strsplit) {
					list.add(Integer.parseInt(s));
				}
			}

			boolean dir = true;
			for (String input : p) {
				if (input.equals("R")) {
					if (dir) {
						dir = false;
					} else {
						dir = true;
					}
				} else if (input.equals("D")) {
					if (list.isEmpty()) {
						bw.write("error\n");
						continue L;
					} else {
						if (dir) {
							list.remove(0);
						} else {
							list.remove(list.size() - 1);
						}
					}
				}
			}
			if (list.isEmpty()) {
				bw.write("[]\n");
			} else {
				bw.write("[");
				if (dir) {
					for (int i = 0; i < list.size() - 1; i++) {
						bw.write(String.valueOf(list.get(i)) + ",");
					}
					bw.write(String.valueOf(list.get(list.size() - 1)) + "]\n");
				} else {
					for (int i = list.size() - 1; i > 0; i--) {
						bw.write(String.valueOf(list.get(i)) + ",");
					}
					bw.write(String.valueOf(list.get(0)) + "]\n");
				}
			}
		}
		bw.flush();
		bw.close();
	}
}
