import java.util.*;
import java.io.*;

public class Main {
//	계산하기
	private static int calculate(Map<String, List<String>> clothes) {
		Set<String> keySet = clothes.keySet();
		Iterator keyIter = keySet.iterator();
		int[] clothesType = new int[keySet.size()];
//		하나씩만 선택할 경우
		int idx = 0;
		int sum = 1;
		while (keyIter.hasNext()) {
			String key = (String) keyIter.next();
			sum *= clothes.get(key).size() + 1;
		}
		return sum - 1;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			Map<String, List<String>> map = new HashMap<>();
//			입력 받기
			for (int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				if (map.containsKey(input[1])) {
					List<String> temp = map.get(input[1]);
					temp.add(input[0]);
					map.replace(input[1], temp);
				} else {
					List<String> temp = new ArrayList<>();
					temp.add(input[0]);
					map.put(input[1], temp);
				}
			}

			System.out.println(calculate(map));
		}
	}
}