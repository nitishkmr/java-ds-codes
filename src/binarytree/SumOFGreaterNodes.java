package binarytree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * @author Nitish
 *
 * 30-Sep-2020
 */

public class SumOFGreaterNodes {
	
	public static Node createTree(LinkedList<Integer> list) {
		int data = list.removeFirst();
		if(data == -1) return null;
		Node newNode = new Node(data);
		if (data != -1) {
			newNode.left = createTree(list);
			newNode.right = createTree(list);
		}
		return newNode;
	}
	
	public static Node buildBalancedBST(int[] arr, int lo, int hi) {
		if(lo>hi) return null;
		int mid = lo + (hi-lo)/2;
		Node newNode = new Node(arr[mid]);
		newNode.left = buildBalancedBST(arr, lo, mid - 1);
		newNode.right = buildBalancedBST(arr, mid + 1, hi);
		return newNode;
	}
	
	
//	static HashMap<Integer, Integer> countMap = new HashMap<>();
	static int max = 0;
	static boolean notRight = true;
	static void funct(Node root){
		if(root == null) return;
		if(root.left == null && root.right == null && notRight) {
			max = root.data;
			notRight = false;
			return;
		}
		funct(root.right);
		root.data = root.data + max;
		max = root.data;
//		if(countMap.containsKey(horizontalIdx + 1)) {
//			root.data = root.data + countMap.get(horizontalIdx + 1);
//			countMap.put(horizontalIdx, root.data);
//			countMap.put(horizontalIdx + 1, root.data);
//		}else {
//			countMap.put(horizontalIdx, root.data);
//			countMap.put(horizontalIdx + 1, root.data);
//		}
		funct(root.left);
	}
	
	public static void printChildWise(Node root) {
		if(root == null) {
			return;
		}
		
		System.out.print(root.data + " -> ");
		if(root.left != null) {
			System.out.print(root.left.data + ", ");
		}else {
			System.out.print("NULL" + ", ");
		}
		if(root.right != null) {
			System.out.println(root.right.data);
		}
		else {
			System.out.println("NULL");
		}
		printChildWise(root.left);
		printChildWise(root.right);
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>(
				Arrays.asList());
		int[] arr = {20, 30, 40, 50, 60, 70, 80};
//		Node root = createTree(list);
		Node root = buildBalancedBST(arr, 0, arr.length - 1);
		funct(root);
//		for(int i:countMap.keySet()) {
//			System.out.println(i + ": " + countMap.get(i));
//		}
		printChildWise(root);
	}

}
