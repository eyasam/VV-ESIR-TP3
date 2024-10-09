package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
	
    // Test that an empty string is considered balanced
    @Test
    public void testEmptyString() {
        assertTrue(isBalanced(""));
    }
    
    // Test that a string with no (),[],{} is considered balanced
    @Test
	public void testNoSymbols() {
	    assertTrue(isBalanced("abc"));
	    assertTrue(isBalanced("123456"));
	}
   
    // Test that simple balanced symbols (parentheses, brackets, braces) are recognized
    @Test
    public void testBalancedSimpleSymbols() {
        assertTrue(isBalanced("()"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("{}"));
    }

    // Test that multiple nested balanced symbols are correctly recognized
    @Test
    public void testBalancedMultipleSymbols() {
        assertTrue(isBalanced("{[()]}"));
        assertTrue(isBalanced("{{[]}}"));
        assertTrue(isBalanced("{}[]()")); 
        assertTrue(isBalanced("{()}"));
    }
   
    // Test that unmatched opening symbols are considered unbalanced
    @Test
    public void testUnmatchedOpeningSymbols() {
        assertFalse(isBalanced("("));
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("["));
        assertFalse(isBalanced("(()"));
        assertFalse(isBalanced("{}{"));
        assertFalse(isBalanced("[[]"));
    }

    // Test that unmatched closing symbols are considered unbalanced
    @Test
    public void testUnmatchedClosingSymbols() {
        assertFalse(isBalanced(")"));
        assertFalse(isBalanced("}"));
        assertFalse(isBalanced("]"));
        assertFalse(isBalanced("))"));
        assertFalse(isBalanced("}{}"));
        assertFalse(isBalanced("[]]"));
    }

    // Test that mismatched symbols are considered unbalanced
    @Test
    public void testMismatchedSymbols() {
        assertFalse(isBalanced("([)]"));
        assertFalse(isBalanced("{[(])}"));
        assertFalse(isBalanced("{[}]"));
    }
    


    // Test strings with mixed content (symbols and other characters) to ensure correct balancing
    @Test
    public void testMixedContent() {
        assertTrue(isBalanced("{abc[def](ghi)\"}"));
        assertFalse(isBalanced("{abc[def}(ghi)]"));
    }

  
}
