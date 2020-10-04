package binarytree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Nitish
 *
 * 01-Oct-2020
 */

public class AllKNodesFromANode {
	static Node root;
	
	static int preIdx;
	private static Node buildTreePostOrderInOrder(int[] preOrder, int[] inOrder) {
		preIdx = inOrder.length - 1;
		return buildTreePostOrderInOrder(preOrder, inOrder, 0, inOrder.length - 1);
	}
	
	static Node buildTreePostOrderInOrder(int[] preOrder, int[] inOrder, int inLo, int inHi){
		if(inLo > inHi) {
			return null;
		}
		
		Node newNode = new Node(preOrder[preIdx]);
		int elementIdxIn = -1;
		for(elementIdxIn = inLo; elementIdxIn <= inHi; elementIdxIn++) {
			if(preOrder[preIdx] == inOrder[elementIdxIn]) {
				break;
			}
		}
		
		preIdx--;
		newNode.right = buildTreePostOrderInOrder(preOrder, inOrder, elementIdxIn + 1, inHi);
		newNode.left = buildTreePostOrderInOrder(preOrder, inOrder, inLo, elementIdxIn - 1);
	
		return newNode;
	}
	
	static HashMap<Node, Node> parentMap = new HashMap<>();	
	static void printNodesAtK(int k) {
		HashMap<Node, Boolean> isVisited = new HashMap<>();
		parentMap.put(root, root);
		Queue<Node> nodeQueue = new LinkedList<>();
		nodeQueue.add(reqNode);
		Node MARKER = null;
		nodeQueue.add(MARKER);
		int count = 0;
		
		while(!nodeQueue.isEmpty() && count != k) {
			//dividing like level order for BFS kind of traversal
			Node currNode = nodeQueue.poll();
			if(currNode != MARKER) {
				isVisited.put(currNode, true);					
				if(currNode.left != null && !isVisited.containsKey(currNode.left)) {
					nodeQueue.add(currNode.left);
				}
				
				if(currNode.right != null && !isVisited.containsKey(currNode.right)) {
					nodeQueue.add(currNode.right);
				}
				
				Node parentNode = parentMap.get(currNode);
				if(!isVisited.containsKey(parentNode)) {
					nodeQueue.add(parentNode);
				}
			}else {
				if(!nodeQueue.isEmpty()) {
					nodeQueue.add(MARKER);
					count++;
				}
			}
		}
		
		while(!nodeQueue.isEmpty()) {
			Node currNode = nodeQueue.poll();
			if(currNode != MARKER)
			System.out.print(currNode.data + " ");
		}
	}
	
	static Node reqNode;
	static void traverseAndMap(Node root, int k) {
		if(root == null) {
			return;
		}
		if(root.data == k) {
			reqNode = root;
		}
		
		if(root.left != null) {
			parentMap.put(root.left, root);
			traverseAndMap(root.left, k);
		}
		if(root.right != null) {
			parentMap.put(root.right, root);
			traverseAndMap(root.right, k);
		}
	}

	public static void main(String[] args) {
		
		int[] inOrder = {3,2,4,1,5,6};
		int[] preOrder = {3,4,2,6,5,1};
		
		root = buildTreePostOrderInOrder(preOrder, inOrder);
		
		traverseAndMap(root, 2);
		printNodesAtK(1);
	}

}
