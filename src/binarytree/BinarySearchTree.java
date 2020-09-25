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
		if(node == null) return;
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
	
	public static Node findMinNode(Node root) {
		if(root.left == null) {
			return root;
		}
		return findMinNode(root.left);
	}
	
	public static Node deleteNode2(Node root, int key) {
		if(root == null)
		{
			return null;
		}
		
		if(key < root.data) {
			root.left = deleteNode2(root.left, key);
		}else if(key > root.data) {
			root.right = deleteNode2(root.right, key);
		}else {
			
			if(root.left == null) {
				return deleteNode2(root.right, key);
			}
			if(root.right == null) {
				return deleteNode2(root.left, key); 
			}
			
			Node temp = findMinNode(root.right);
			deleteNode2(root.right, temp.data);
			root.data = temp.data;
			}
		return root;
	}
	
	public static void deleteNode(int[] deleteArr) {
		for(int i: deleteArr) {
			deleteNode(root, i, null, false);
//			root = deleteNode2(root, i);
		}
	}
	
	private static int[] inputArr(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		return arr;
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

	public static void keysInRange(Node root, int k1, int k2) {
		if(root == null) {
			return;
		}
		
		if(root.data >= k1 && root.data <= k2) {
			System.out.print(root.data + " ");
		}
		keysInRange(root.left, k1, k2);
		keysInRange(root.right, k1, k2);
	}
	
	public static void main(String[] args) {
		//5,3,2,4,7,6,8
//		int[] treeArr = {5,3,2,4,7,6,8};
//		int[] deleteArr = {7, 7,2,8,8,8,8,8,2,3,5,6,4};
//		int tcase = sc.nextInt();
//		18
//		int[] treeArr = {172, 468, 963, 94, 951, 803, 683, 630, 198, 672, 327, 216, 451, 738, 798, 251, 558, 159}; 
//		11
//		int[] deleteArr = {683, 159, 327, 94, 451, 738, 738}; 
		
			int n = sc.nextInt();
			int[] treeArr = inputArr(n);
			int m = sc.nextInt();
			int[] deleteArr = inputArr(m);
			createBST(treeArr);
			deleteNode(deleteArr);
			print(root);
			System.out.println();
//			printChildWise(root);
			root = null;
		
	}
}
