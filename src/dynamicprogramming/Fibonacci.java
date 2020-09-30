package dynamicprogramming;

/**
 * @author Nitish
 *
 *         29-Sep-2020
 */

public class Fibonacci {

	//TC: O(2^n) SC: O(n) + Stack size
	public static int fibTD(int n, int[] memo) {
		if (n == 0 || n == 1) {
			return 1;
		}

		if (memo[n] != 0) {
			return memo[n];
		}
		
		return memo[n] = fibTD(n - 1, memo) + fibTD(n - 2, memo);
	}
	
	//TC: O(n) SC: O(n)
	public static int fibBU(int n) {
		int[] memo = new int[n + 1];
		memo[0] = memo[1] = 1;
		
		for(int i=2; i<=n; i++) {
			memo[i] = memo[i-1] + memo[i-2];
		}
		return memo[n];
	}
	
	//TC: O(n) SC: O(1)
	public static int fibBUEfficient(int n) {
		int[] memo = new int[2];
		memo[0] = memo[1] = 1;
		for (int i = 1; i < n; i++) {
			int fibNum = memo[0] + memo[1];
			memo[0] = memo[1];
			memo[1] = fibNum;
		}
		return memo[1];
	}

	public static void main(String[] args) {
		int n = 24;
		System.out.println(fibTD(n, new int[n+1]));
		System.out.println(fibBU(n));
		System.out.println(fibBUEfficient(n));
	}

}
