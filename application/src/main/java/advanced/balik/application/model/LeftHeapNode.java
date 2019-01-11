package advanced.balik.application.model;

public class LeftHeapNode {
    int element, sValue;
    LeftHeapNode left, right;

    public LeftHeapNode copy() {
        LeftHeapNode copyNode = new LeftHeapNode(this.element);
        copyNode.sValue = this.sValue;
        if (this.left != null) {
            copyNode.left = left.copy();
        }
        if (this.right != null) {
            copyNode.right = right.copy();
        }
        return copyNode;
    }

    public LeftHeapNode(int ele) {
        this(ele, null, null);
    }

    public LeftHeapNode(int ele, LeftHeapNode left, LeftHeapNode right) {
        this.element = ele;
        this.left = left;
        this.right = right;
        this.sValue = 0;
    }

    public int getElement() {
        return element;
    }

    public int getsValue() {
        return sValue;
    }

    public LeftHeapNode getLeft() {
        return left;
    }

    public LeftHeapNode getRight() {
        return right;
    }

}
