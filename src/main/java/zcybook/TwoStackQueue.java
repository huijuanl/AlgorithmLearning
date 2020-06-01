package zcybook;

import java.util.Stack;

public class TwoStackQueue {
    private Stack<Integer> stackPush = new Stack<>();
    private Stack<Integer> stackPop = new Stack<>();

    public void add(int n) {
        stackPush.add(n);
    }

    public int pop() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }
    public static void main(String[]args){
        TwoStackQueue queue = new TwoStackQueue();
        queue.add(4);
        queue.add(3);
        queue.add(1);
        assert (queue.pop() == 4);
        assert (queue.pop() == 3);
        queue.add(2);
        queue.add(5);
        assert (queue.peek() == 1);
        queue.pop();
        queue.pop();
        assert (queue.pop() == 5);
    }
}
