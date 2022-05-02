package lisp;

import java.util.Scanner;
import java.util.Stack;

public class ParenMatcher {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter expression");
        if (balancedParentheses(scan.next())) {
            System.out.println("Parenthesis are balanced!");
        } else {
            System.out.println("Parenthesis are not balanced!");
        }
    }

    /**
     * Checks that the input string has balanced parenthesis
     *
     * @param s - input String
     * @return boolean - true if balanced
     */
     private static boolean balancedParentheses(String s) {
        Stack<Character> stack  = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }
}
