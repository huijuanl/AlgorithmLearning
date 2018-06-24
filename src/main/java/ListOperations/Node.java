package ListOperations;
public class  Node { //若放在类内部，为内部类，设置成static（表示生成一个静态内部类不需要外部类成员：这是静态内部类和成员内部类的区别。）
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

}

class DoubleNode {
    public int data;
    public DoubleNode pre;
    public DoubleNode next;
    public DoubleNode(int data) {
        this.data = data;
    }

}
