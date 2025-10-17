package diaz;

public class Dictionary {
	
	private WordNode root;
	
	public Dictionary() {
		root = null;
	}
	
	//Inserts word into the BST
	public void insertWordNode(String word) {
		root = insertRecursive(root, word);
	}
	
	private WordNode insertRecursive(WordNode node, String word) {
		if (node == null) {
			return new WordNode(word);
		}
		
		if (word.compareTo(node.word) < 0) {
			node.left = insertRecursive(node.left, word);
		} else if (word.compareTo(node.word) > 0) {
			node.right = insertRecursive(node.right, word);
		}
		//If the word is already in the tree, does nothing
		return node;
	}
	
	//Spell check
	public boolean spellCheck(String word) {
		return searchRecursive(root, word);
	}
	
	private boolean searchRecursive(WordNode node, String word) {
		if (node == null) {
			return false;
		}
		
		if (word.equals(node.word)) {
			return true;
		}
		
		if (word.compareTo(node.word) < 0) {
			return searchRecursive(node.left, word);
		} else {
			return searchRecursive(node.right, word);
		}
	}
	
	//Delete a word from the BST
	public void checkWord (String word) {
		root = deleteRecursive(root, word);
	}
	
	private WordNode deleteRecursive(WordNode node, String word) {
		if (node == null) {
			return null;
		}
		
		if (word.compareTo(node.word) < 0) {
			node.left = deleteRecursive(node.left, word);
		} else if (word.compareTo(node.word) > 0) {
			node.right = deleteRecursive(node.right, word);
		} else {
			// Node with only one child or no child
			if (node.left == null && node.right == null) {
				return null;
			} else if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
			
			String smallest = findMin(node.right);
			// Node with two children: Get the smallest in the right subtree
			node.word = findMin(node.right);
			
			// Delete the inorder successor
			node.right = deleteRecursive(node.right, smallest);
			}
		}
		return node;
	}
	
	private String findMin(WordNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node.word;
	}

}
