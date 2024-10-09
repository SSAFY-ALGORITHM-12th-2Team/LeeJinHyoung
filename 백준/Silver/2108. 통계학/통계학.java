import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.awt.*;

public class Main {
	static class entry {
		int key;
		int value;

		public entry(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int avg = 0;
		int mid = 0;
		int most = 0;
		int coverage = 0;

		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

		int N = sc.nextInt();
		int[] array=new int[N];
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			int input = sc.nextInt();
			array[i]=input;
		}
		
		System.out.println(avg(array));
		System.out.println(mid(array));
		System.out.println(most(array));
		System.out.println(array[array.length-1] -array[0]);
	}

	private static int most(int[] array) {
		// TODO Auto-generated method stub
		TreeMap<Integer,Integer> map=new TreeMap<>();
		for(int i:array) {
			if(map.containsKey(i)) {
				map.replace(i, map.get(i)+1);
			} else {
				map.put(i, 1);
			}
		}
		ArrayList<Integer> list=new ArrayList<>();
		Iterator iter=map.keySet().iterator();
		
		int max=Integer.MIN_VALUE;
		while(iter.hasNext()) {
			int key=(int)iter.next();
//			System.out.println(key+" "+map.get(key));
			if(max<map.get(key)) {
				list.clear();
				list.add(key);
				max=Math.max(max, map.get(key));
			} else if(max==map.get(key)) {
				list.add(key);
			}
		}
//		System.out.println("리스트 갯수"+list.size());
//		for(int i:list) {
//			System.out.print(i+" ");
//		}
		
		if(list.size()==1) {
			return list.get(0);
		} else {
			Collections.sort(list);
			return list.get(1);
		}
	}

	private static int mid(int[] array) {
		// TODO Auto-generated method stub
		Arrays.sort(array);
		return array[array.length/2];
	}

	private static int avg(int[] array) {
		// TODO Auto-generated method stub
		double sum=0;
		for(int i:array) {
			sum+=i;
		}
		return (int) Math.round(sum/array.length);
	}
}