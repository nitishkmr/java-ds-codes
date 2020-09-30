package dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nitish
 *
 * 30-Sep-2020
 */

public class BoardPath {
	
	static int boardPathRec(int k, int sum) {
		if(sum == k) {
//			System.out.println(result);
			return 1;
		}
		
		if(sum > k) {
			return 0;
		}
		
		int count = 0;
		
		for(int i=1; i<=6; i++) {
			count += boardPathRec(k, sum + i);
		}
		
		return count;
	}

	static int boardPathRecTD(int k, int sum, int[] memo) {
		if(sum == k) {
			return 1;
		}
		
		if(sum > k) {
			return 0;
		}
		
		int count = 0;

		//memo means that it has the ans so return
		if(memo[sum] != 0) {
			return memo[sum];
		}
		
		for(int i=1; i<=6; i++) {
			count += boardPathRecTD(k, sum + i, memo);
		}
		
		memo[sum] = count;
		return count;
	}
	
	static int boardPathBU(int end) {
		//6 for the numbers on a dice
		int[] strg = new int[end + 6];
		
		//stores number of ways from index to k
		strg[end] = 1;
		
		for(int i = end-1; i>=0; i--) {
			strg[i] = strg[i+1] + strg[i+2] + strg[i+3] + strg[i+4] + strg[i+5] + strg[i+6];
		}
		return strg[0];
	}
	
	static int boardPathBUSpaceEfficient(int end) {
		//6 for the numbers on a dice
//		int[] strg = new int[6];
		List<Integer> strg = new ArrayList<>(6);
		
		//stores number of ways from index to k
		strg.set(0,1);
		
		for(int i = end-1; i>=0; i--) {
			int currSum = 0;
			for(int j=0; j<6; j++) {
				currSum += strg.get(j);
			}
			strg.remove(strg.size() - 1);
			strg.add(0, currSum);
		}
		return strg.get(0);
	}
	
	public static void main(String[] args) {
		int n = 40000;
//		System.out.println(boardPathRec(n, 0));
//		System.out.println(boardPathRecTD(n, 0, new int[n]));
		System.out.println(boardPathBU(n));
	}

}
