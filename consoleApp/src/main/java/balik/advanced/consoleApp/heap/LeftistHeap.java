package balik.advanced.consoleApp.heap;

public class LeftistHeap {
    private LeftHeapNode root;

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
        root = merge(new LeftHeapNode(x), root);
    }

    public void merge(LeftistHeap rhs) {
        if (this == rhs)
            return;
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    private LeftHeapNode merge(LeftHeapNode x, LeftHeapNode y) {
        if (x == null)
            return y;
        if (y == null)
            return x;
        if (x.element > y.element) {
            LeftHeapNode temp = x;
            x = y;
            y = temp;
        }

        x.right = merge(x.right, y);

        if (x.left == null) {
            x.left = x.right;
            x.right = null;
        } else {
            if (x.left.sValue < x.right.sValue) {
                LeftHeapNode temp = x.left;
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