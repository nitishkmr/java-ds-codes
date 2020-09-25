package binarytree;

import java.util.Scanner;



/**
 * @author Nitish
 *
 *         24-Sep-2020
 */

public class BinarySearchTree {
	static Scanner sc = new Scanner(System.in);

	public static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	static Node root;

	private static Node buildTree(Node root, Node newNode) {
		if (root == null) {
			return newNode;
		}

		if (newNode.data < root.data) {
			root.left = buildTree(root.left, newNode);
		} else {
			root.right = buildTree(root.right, newNode);
		}
		return root;
	}

	public static void createBST(int[] arr) {
		for (int i : arr) {
			Node newNode = new Node(i);
			root = buildTree(root, newNode);
		}
	}

	public static void print(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		print(root.left);
		print(root.right);
	}

	public static void deleteNode(Node node, int key, Node parent, boolean ilc) {
		if (key < node.data) {
			deleteNode(node.left, key, node, true);
		} else if (key > node.data) {
			deleteNode(node.right, key, node, false);
		} else {
			if (node.left == null && node.right == null) {
				if(parent!=null) {
					if (ilc) {
						parent.left = null;
					} else {
						parent.right = null;
					}					
				}else {
					root = null;
				}
			} else if (node.left == null && node.right != null) { // check for only left / right
				if(parent!=null) {
					if (ilc) {
						parent.left = node.right;
					} else {
						parent.right = node.right;
					}
				}else {
					root = node.right;
				}
			} else if (node.left != null && node.right == null) { // check for only left / right
				if(parent!=null) {
					if (ilc) {
						parent.left = node.left;
					} else {
						parent.right = node.left;
					}
				}else {
					root = node.left;
				}
			} else {
				// find min from the right subtree of the node
				Node temp = node.right;
				while (temp.left != null) {
					temp = temp.left;
				}
				node.data = temp.data;
				deleteNode(node.right, node.data, node, false);
			}
		}
	}
	
	private static void deleteNode(int[] deleteArr) {
		for(int i: deleteArr) {
			deleteNode(root, i, null, false);
		}
	}
	
	private static int[] inputArr(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		return arr;
	}

	public static void main(String[] args) {
		//5,3,2,4,7,6,8
		int[] arr = {5,3,2,4,7,6,8};
		int[] arr2 = {8,6};
//		int tcase = sc.nextInt();
//		while(tcase-- > 0) {			
//			int n = sc.nextInt();
//			int[] treeArr = inputArr(n);
//			int m = sc.nextInt();
//			int[] deleteArr = inputArr(m);
			createBST(arr);
			deleteNode(arr2);
			print(root);
			System.out.println();
			root = null;
//		}
		
	}


}
