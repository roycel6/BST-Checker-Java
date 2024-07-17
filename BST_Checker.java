package BSTChecker;


public class BSTChecker {
	
	static int lastValue = 0;		//stores last processed node
	
	public static void main(String[] args) {
	BinaryTree tree = new BinaryTree();
	boolean check = false;
	
	tree.root = new node(4);
	tree.root.left = new node(2); 
	tree.root.right = new node(6);                 
	tree.root.left.left = new node(1);
	tree.root.left.right = new node(3);
	tree.root.right.left = new node(5);
	tree.root.right.right = new node(7);
	
	delete(tree.root, 4);
	check = isBST(tree.root);
	System.out.println();
	System.out.print(check);
	
}
	
	public static node minNodeRight(node root) {	//finds the min node in right subtree
		if(root.left == null){
		return root;
		}
		
		else {
			return minNodeRight(root.left);
		}
	}
	
	public static node delete(node root, int val) {
		if(root == null) {		//base case
			return null;
		}
		
		if(root.data > val) {		//if left node is larger than current node, that node is deleted
			root.left = delete(root.left, val);
		}
		
		else if(root.data < val) {		//if right node is smaller than current, node is deleted
			root.right = delete(root.right, val);
		}
		
		else {
			if(root.left != null && root.right != null) {		//used if node being deleted has 2 children
				node temp = root;
				node minRight = minNodeRight(temp.right);	//finds min node on right
				root.data = minRight.data;		//replaces node with min
				root.right = delete(root.right, minRight.data);	//deletes min node
			}
			
			 else if (root.left != null) {	//used if 1 left child
	                root = root.left;
	            }
	           
	            else if (root.right != null) {		//used if 1 right child
	                root = root.right;
	            }
	            
	            else {		//used if no child
	                root = null;
	        }
		}
		
		return root;
	}

	public static boolean isBST(node root) {
		
		
		if(root == null) {		//base case
			return true;
		}
		if(!isBST(root.left)) {		//if left subtree is false, tree is not BST
			return false;
		}
		
		if(root.data < lastValue) {		//the current node cannot be greater than last node because it is to the right
										//if it is, the tree is not BST
			return false;
		}
		
		lastValue = root.data;
		System.out.print(root.data);
		return isBST(root.right);
		
	}

}

class node{
	int data;
	node left, right;
	
	public node(int item) {
		data = item;
		left = null;
		right = null;
	}
}

class BinaryTree{
	node root;
}

