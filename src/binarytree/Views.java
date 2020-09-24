package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * @author Nitish
 *
 * 24-Sep-2020
 */

public class Views {
	
	public static class Node{
		int data;
		Node left;
		Node right;
		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}
	
//	1, 2, 3, 4, 5, -1, 6, -1, -1, -1, -1, -1, -1
	public static Node levelOrderInput() {
		Scanner sc = new Scanner(System.in);
		int data = sc.nextInt();
		Node root = new Node(data);
		Queue<Node> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);
		
		while(!nodeQueue.isEmpty()) {
			Node currRoot = nodeQueue.poll();
			int currElement1 = sc.nextInt();
			int currElement2 = sc.nextInt();
			if(currElement1 != -1) {
				currRoot.left = new Node(currElement1);
				nodeQueue.add(currRoot.left);
			}
			if(currElement2 != -1) {
				currRoot.right = new Node(currElement2);
				nodeQueue.add(currRoot.right);
			}
		}
		sc.close();
		return root;
	}
	
	
	static int maxHeight = -1;
	
	private static void rightView(BinaryTree.Node root, int currHeight) {
		if(root == null) return;
		
		if(currHeight > maxHeight) System.out.print(root.data + " ");
		maxHeight = Math.max(currHeight, maxHeight);
		rightView(root.right, currHeight + 1);
		rightView(root.left, currHeight + 1);
	}
	
	public static void main(String[] args) {
		BinaryTree bTree = new BinaryTree();
		LinkedList<Integer> list = new LinkedList<>(
//				Arrays.asList(1, 2, 4, -1, -1, 5, -1, 9, -1, -1, 3, 6, -1, -1, 7, -1, -1));
				Arrays.asList(1, 2, 5,6,-1,-1,-1, -1, 3, 4,-1,-1, -1));
		bTree.root = bTree.createTree(list);
		
//		LinkedList<Integer> listLevelOrder = new LinkedList<>(
//				Arrays.asList(1, 2, 3, 4, 5, -1, 6, -1, -1, -1, -1, -1, -1));
		
//		Node newRoot = levelOrderInput();
		rightView(bTree.root, 0);

	}


}