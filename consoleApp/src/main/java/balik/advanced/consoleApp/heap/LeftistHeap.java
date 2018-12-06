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

    public void merge(LeftistHeap rhs) {
        if (this == rhs)
            return;
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    private Node merge(Node x, Node y) {
        if (x == null)
            return y;
        if (y == null)
            return x;
        if (x.element > y.element) {
            Node temp = x;
            x = y;
            y = temp;
        }

        x.right = merge(x.right, y);

        if (x.left == null) {
            x.left = x.right;
            x.right = null;
        } else {
            if (x.left.sValue < x.right.sValue) {
                Node temp = x.left;
                x.left = x.right;
                x.right = temp;
            }
            x.sValue = x.right.sValue + 1;
        }
        return x;
    }

    public int extractMin() {
        if (isEmpty())
            return -1;
        int minItem = root.element;
        root = merge(root.left, root.right);
        return minItem;
    }

}