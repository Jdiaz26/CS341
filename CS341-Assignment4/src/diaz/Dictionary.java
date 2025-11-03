package diaz;

public class Dictionary {
    private WordNode root;

    public Dictionary() {
        root = null;
    }

    // Insert word into BST
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
        // if word already exists, do nothing
        return node;
    }

    // Spell check — return true if word exists
    public boolean spellCheck(String word) {
        return searchRecursive(root, word);
    }

    private boolean searchRecursive(WordNode node, String word) {
        if (node == null) return false;
        if (node.word.equals(word)) return true;
        if (word.compareTo(node.word) < 0)
            return searchRecursive(node.left, word);
        else
            return searchRecursive(node.right, word);
    }

    // Delete a word from the tree
    public void checkWord(String word) {
        root = deleteRecursive(root, word);
    }

    private WordNode deleteRecursive(WordNode node, String word) {
        if (node == null) return null;

        if (word.compareTo(node.word) < 0) {
            node.left = deleteRecursive(node.left, word);
        } else if (word.compareTo(node.word) > 0) {
            node.right = deleteRecursive(node.right, word);
        } else {
            // found the node
            if (node.left == null && node.right == null) {
                return null; // no children
            } else if (node.left == null) {
                return node.right; // one child
            } else if (node.right == null) {
                return node.left; // one child
            } else {
                // two children
                String smallest = findMin(node.right);
                node.word = smallest;
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

    // Optional: display the tree in order
    public void printDictionary() {
        inorderPrint(root);
    }

    private void inorderPrint(WordNode node) {
        if (node != null) {
            inorderPrint(node.left);
            System.out.print(node.word + " ");
            inorderPrint(node.right);
        }
    }
}
