package binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Nitish
 *
 * 26-Sep-2020
 * Given a Binary Tree, find the size of the largest possible BST.
 * The tree looks like
 *
 *            60
 *         /      \
 *      65           70
 *    /           
 *  50       
 * The largest BST subtree is
 *
 *      65           
 *    /           
 *  50       
 *  which has the size 2 i.e. it has 2 nodes in it.
 * 
 */

public class LargestBSTInBT {
	Node root;
	
	public class Info{
		int size;
		int min;
		int max;
		boolean isBST;
		public Info() {
			size = 0;
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			isBST = true;
		}
	}

	public Node createTree(LinkedList<Integer> list) {
		int data = list.removeFirst();
		if(data == -1) return null;
		Node newNode = new Node(data);
		if (data != -1) {
			newNode.left = createTree(list);
			newNode.right = createTree(list);
		}
		return newNode;
	}
	
	public Info findLargestBST(Node root) {
		if(root == null) {
			//null is also BST, size would be equal to 0
			return new Info();
		}
		
		//recursive PostOrder calls
		Info leftInfo = findLargestBST(root.left);
		Info rightInfo = findLargestBST(root.right);
		
		Info currInfo = new Info();
		
		//check all the condititons from the left subtree and right subtree
		if(!leftInfo.isBST || !rightInfo.isBST || leftInfo.max > root.data || rightInfo.min < root.data) {
			currInfo.size = Math.max(leftInfo.size, rightInfo.size);
			currInfo.isBST = false;
			return currInfo;
		}
		
		//here, it means that the including the current node, a BST is being formed
		currInfo.size = rightInfo.size + leftInfo.size + 1;
		currInfo.isBST = true;
		currInfo.min = root.left == null ? root.data : leftInfo.min;
		currInfo.max = root.right == null ? root.data : rightInfo.max;
		return currInfo;
	}
	

	public static void main(String[] args) {
		LargestBSTInBT bTree = new LargestBSTInBT();
		LinkedList<Integer> list = new LinkedList<>(Arrays.asList
				(50,30,5,-1,-1,25,-1,-1,60,45,-1,-1,70,65,-1,-1,80,-1,-1));
		bTree.root = bTree.createTree(list);
		Info maxBST = bTree.findLargestBST(bTree.root);
		System.out.println(maxBST.size);
	}

}
