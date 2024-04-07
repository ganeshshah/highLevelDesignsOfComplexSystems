package adityaverma;

import java.util.Stack;

public class ReverseStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println("Original Stack: " + stack);
        reverseStack(stack);
        System.out.println("Reversed Stack: " + stack);
    }

    public static void reverseStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            // Remove the top element from the stack
            int temp = stack.pop();
            // Reverse the remaining stack
            reverseStack(stack);
            // Insert the top element at the bottom of the stack
            insertAtBottom(stack, temp);
        }
    }

    public static void insertAtBottom(Stack<Integer> stack, int value) {
        if (stack.isEmpty()) {
            // If the stack is empty, push the value onto the stack
            stack.push(value);
        } else {
            // Remove the top element from the stack
            int temp = stack.pop();
            // Recursively insert the value at the bottom of the stack
            insertAtBottom(stack, value);
            // Push the removed element back onto the stack
            stack.push(temp);
        }
    }
}
