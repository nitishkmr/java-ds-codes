package general;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


/**
 * @author Nitish
 *
 * 28-Sep-2020
 */

public class KSortedArray {
	
	public static class ArrayEntry{
		int value;
		int arrayID;
		public ArrayEntry(int value, int arrayID) {
			this.value = value;
			this.arrayID = arrayID;
		}
	}
	
	public static List<Integer> mergeSortedArrays(List<List<Integer>> kSortedArrays){
		
//		Iterator<Integer>[] iters = (Iterator<Integer>[]) new Object[kSortedArrays.size()];
//		List<Iterator<Integer>> iters = new LinkedList<>(kSortedArrays.size()); // in LL we cannot define size
		List<Iterator<Integer>> iters = new ArrayList<>(kSortedArrays.size());
		List<Integer> resultArray = new ArrayList<>();
		
		for(List<Integer> array : kSortedArrays) {
			iters.add(array.iterator());
		}
		
		Queue<ArrayEntry> pq = new PriorityQueue<>(
				kSortedArrays.size(), new Comparator<ArrayEntry>() {
					@Override
					public int compare(ArrayEntry o1, ArrayEntry o2) {
						return Integer.compare(o1.value, o2.value);
					}
				});
		
		
		for(int i=0; i<iters.size(); i++) {
			if(iters.get(i).hasNext()) {
				pq.add(new ArrayEntry(iters.get(i).next(), i));
			}
		}
		
		while(!pq.isEmpty()) {
			
			ArrayEntry ejectedEntry = pq.poll();
			resultArray.add(ejectedEntry.value);
			
			if(iters.get(ejectedEntry.arrayID).hasNext()) {
				pq.add(new ArrayEntry(iters.get(ejectedEntry.arrayID).next(), ejectedEntry.arrayID));
			}
		}
		return resultArray;
	}
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<List<Integer>> kSortedArrays = new LinkedList<>();
		
		int k = sc.nextInt();
		int n = sc.nextInt();
		while(k-- > 0) {
			LinkedList<Integer> tempLinkedList = new LinkedList<>();
			for(int i=0; i<n; i++) {
				int value = sc.nextInt();
				tempLinkedList.add(value);
			}
			kSortedArrays.add(tempLinkedList);
		}
		
		List<Integer> resultList = mergeSortedArrays(kSortedArrays);
		for(int i:resultList) {
			System.out.print(i + " ");
		}
		sc.close();
	}

}
