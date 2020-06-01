package zcybook;

import java.util.Stack;

public class GetMinStack {
    private Stack<Integer> stackData = new Stack<Integer>();
    private Stack<Integer> stackMin = new Stack<Integer>();

    public void push(int newNum) {
        stackData.push(newNum);
        if (stackMin.isEmpty()) {
            stackMin.push(newNum);
        } else {
            stackMin.push(Math.min(stackMin.peek(), newNum));
        }
    }

    public int pop(){
        stackMin.pop();
        return stackData.pop();
    }

    public int peek(){
        return stackData.peek();
    }
    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("stackMin is empty!");
        } else return stackMin.peek();
    }

    public static void main(String[]args){
        GetMinStack stack = new GetMinStack();
        stack.push(4);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(5);
        assert (stack.getMin() == 1);
        assert (stack.pop() == 5);
        stack.pop();
        stack.pop();
        assert (stack.getMin() == 3);
    }
}

