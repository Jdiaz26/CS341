package diaz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTest {
	
	@Test
    public void testInsertAndSpellCheck() {
        Dictionary dict = new Dictionary();

        dict.insertWordNode("apple");
        dict.insertWordNode("banana");
        dict.insertWordNode("grape");

        assertTrue(dict.spellCheck("apple"));
        assertTrue(dict.spellCheck("banana"));
        assertFalse(dict.spellCheck("cherry"));
    }
	
	@Test
    public void testDeleteWord() {
        Dictionary dict = new Dictionary();
        dict.insertWordNode("apple");
        dict.insertWordNode("banana");
        dict.insertWordNode("cherry");

        // delete banana
        dict.checkWord("banana");

        assertFalse(dict.spellCheck("banana"));
        assertTrue(dict.spellCheck("apple"));
        assertTrue(dict.spellCheck("cherry"));
    }
	
	@Test
    public void testInsertDuplicateWord() {
        Dictionary dict = new Dictionary();
        dict.insertWordNode("apple");
        dict.insertWordNode("apple"); // duplicate

        // Tree should still have only one "apple"
        assertTrue(dict.spellCheck("apple"));
        assertFalse(dict.spellCheck("banana"));
    }
	
	@Test
    public void testDeleteNonExistingWord() {
        Dictionary dict = new Dictionary();
        dict.insertWordNode("apple");
        dict.insertWordNode("banana");

        dict.checkWord("peach"); // deleting a word not in tree
        // Should not crash or change other words
        assertTrue(dict.spellCheck("apple"));
        assertTrue(dict.spellCheck("banana"));
    }
	
	@Test
    public void testAddAndRemoveManyWords() {
        Dictionary dict = new Dictionary();

        String[] words = {"dog", "cat", "bird", "zebra", "lion", "tiger"};
        for (String w : words) {
            dict.insertWordNode(w);
        }

        // check they exist
        for (String w : words) {
            assertTrue(dict.spellCheck(w));
        }

        // remove a few
        dict.checkWord("cat");
        dict.checkWord("tiger");

        assertFalse(dict.spellCheck("cat"));
        assertFalse(dict.spellCheck("tiger"));
        assertTrue(dict.spellCheck("lion"));
    }


}
