package general;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Nitish
 *
 *         29-Sep-2020
 */

public class TopKMostFreq {

	public static class Pair implements Comparable<Pair> {
		int value1, freq;

		public Pair(int value1, int value2) {
			this.value1 = value1;
			this.freq = value2;
		}

		// IMPORTANT - ordering of both types, based on freq and if freq are same then
		// based on values, main thing is in this compareTo function
		@Override
		public int compareTo(Pair o) {
			if (o.freq == this.freq) {
				return this.value1 - o.value1;
			}
			return o.freq - this.freq;
		}
	}


	public static void funct(int[] numStream, int k) {
		TreeMap<Integer, Integer> freqMap = new TreeMap<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int i = 0; i < numStream.length; i++) {
			pq.clear();
			int currElement = numStream[i];
			freqMap.put(currElement, freqMap.get(currElement) == null ? 1 : freqMap.get(currElement) + 1);
			for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
				pq.add(new Pair(entry.getKey(), entry.getValue()));
			}

			int count = k;
			List<Pair> tempList = new ArrayList<>();

			while (!pq.isEmpty() && count != 0) {
				tempList.add(pq.poll());
				count--;
			}

			for (Pair pair : tempList) {
				System.out.print(pair.value1 + " ");
				pq.add(pair);
			}
//			System.out.println();
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

//		int n = sc.nextInt();
//		int k = sc.nextInt();
//		int[] arr = new int[n];
//		for (int i = 0; i < n; i++) {
//			arr[i] = sc.nextInt();
//		}
		int[] arr = {1,2,3,2,3,3,1,1};
		funct(arr, 1);
		System.out.println();
		sc.close();
	}

}
