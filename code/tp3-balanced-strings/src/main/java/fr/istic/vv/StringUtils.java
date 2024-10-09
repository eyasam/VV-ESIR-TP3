package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

	 /**
     * Checks if the brackets (),[],{} in the given string are balanced.
     * @param str the string to check for balanced brackets
     * @return true if the brackets are balanced
     */
    public static boolean isBalanced(String str) {
    	
        Stack<Character> pile = new Stack<>();
        
        for (int i =0; i<str.length(); i++) {
            char ch =str.charAt(i); 
        	if (ch == '(' || ch == '{' || ch == '[') {
                pile.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
            	if (pile.isEmpty()) {
                    return false;
                }
            	char sommet = pile.pop();
            	if ((ch == ')' && sommet != '(') || (ch == '}' && sommet != '{') || (ch == ']' && sommet != '[')) {
                    return false;
                }
            }
        }
        return pile.isEmpty();
    }
}