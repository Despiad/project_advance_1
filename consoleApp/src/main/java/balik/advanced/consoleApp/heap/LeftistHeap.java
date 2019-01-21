package balik.advanced.consoleApp.heap;

public class LeftistHeap {
    private Node root;

    public LeftistHeap() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public void insert(int x) {
        root = merge(new Node(x), root);
    }

    void merge(LeftistHeap rhs) {
        if (this == rhs) {
            return;
        }
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    private Node merge(Node leftTree, Node right) {
        if (leftTree == null)
            return right;
        if (right == null)
            return leftTree;
        if (leftTree.element > right.element) {
            Node temp = leftTree;
            leftTree = right;
            right = temp;
        }

        leftTree.right = merge(leftTree.right, right);

        if (leftTree.left == null) {
            leftTree.left = leftTree.right;
            leftTree.right = null;
        } else {
            if (leftTree.left.sValue < leftTree.right.sValue) {
                Node temp = leftTree.left;
                leftTree.left = leftTree.right;
                leftTree.right = temp;
            }
            leftTree.sValue = leftTree.right.sValue + 1;
        }
        return leftTree;
    }

    public void extractMin() {
        if (root != null) {
            root = merge(root.left, root.right);
        }
    }

    public int getMin() {
        if (isEmpty()) {
            return -1;
        }
        return root.element;
    }

}