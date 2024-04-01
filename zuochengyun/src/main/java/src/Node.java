package src;


public class Node {
    public int date;
    public Node next;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    public Node(int data) {
        this.date = data;
    }
}
