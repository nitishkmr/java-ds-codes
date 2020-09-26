package binarytree;

/**
 * @author Nitish
 *
 *         26-Sep-2020
 */

public class CreateTreePreOrderInOrder {

	public static class CreateTree {
		private int searchInArray(int[] arr, int key, int lo, int hi) {
			for (int i = lo; i <= hi; i++) {
				if (arr[i] == key)
					return key;
			}
			return -1;
		}

		public Node createUsingPreOrderInOder(int[] inOrder, int[] preOrder, int iLo, int iHi, int preLo, int preHi) {
			if (iHi < iLo) {
				return null;
			}

			// take Nodes from the start of the PreOrder array
			int currElement = preOrder[preLo];
			Node newNode = new Node(currElement);

			
			if(iLo == iHi) return newNode;
			
			// search for the currElement's index in the InOrder array
			int currElementIdx = searchInArray(inOrder, currElement, iLo, iHi);

			// recursively build trees for the left and right side
			newNode.left = createUsingPreOrderInOder(inOrder, preOrder, iLo, currElementIdx - 1, preLo + 1, preLo + currElementIdx - iLo);
			newNode.right = createUsingPreOrderInOder(inOrder, preOrder, currElementIdx + 1, iHi,preLo + currElementIdx - iLo + 1, preHi);

			return newNode;
		}
		
		int preLo = 0;
		public Node createTree(int[] inOrder, int[] preOrder, int inLo, int inHi) {
			if(inLo > inHi) return null;
			
			int currElement = preOrder[preLo++];
			Node newNode = new Node(currElement);
			
			//if node has no children
			if(inLo == inHi) return newNode;
			
			// search for the currElement's index in the InOrder array
			int currElementIdx = searchInArray(inOrder, currElement, inLo, inHi);
			
			newNode.left = createTree(inOrder, preOrder, inLo, currElementIdx - 1);
			newNode.right = createTree(inOrder, preOrder, currElementIdx + 1, inHi);
			
			return newNode;
		}
		

		public void printChildWise(Node root) {
			if (root == null) {
				return;
			}

			System.out.print(root.data + " -> ");
			if (root.left != null) {
				System.out.print(root.left.data + ", ");
			} else {
				System.out.print("NULL" + ", ");
			}
			if (root.right != null) {
				System.out.println(root.right.data);
			} else {
				System.out.println("NULL");
			}
			printChildWise(root.left);
			printChildWise(root.right);
		}

	}

	public static void main(String[] args) {
		CreateTree bTree = new CreateTree();
//		int[] inOrder = {4,2,5,1,3,6};
//		int[] preOrder = {1,2,4,5,3,6};
		int[] inOrder = { 3, 2, 1 };
		int[] preOrder = { 1, 2, 3 };
		Node root = bTree.createUsingPreOrderInOder(inOrder, preOrder, 0, inOrder.length - 1, 0, preOrder.length - 1);
//		Node root = bTree.createTree(inOrder, preOrder, 0, inOrder.length - 1);
		bTree.printChildWise(root);
	}

}
