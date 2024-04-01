package src;

public class LeftRightNode {
    public int value;
    public LeftRightNode left;
    public LeftRightNode right;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LeftRightNode getLeft() {
        return left;
    }

    public void setLeft(LeftRightNode left) {
        this.left = left;
    }

    public LeftRightNode getRight() {
        return right;
    }

    public void setRight(LeftRightNode right) {
        this.right = right;
    }

    public LeftRightNode(int value) {
        this.value = value;
    }
}
