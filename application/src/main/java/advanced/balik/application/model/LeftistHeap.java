package advanced.balik.application.model;


public class LeftistHeap {
    private LeftHeapNode root;

    public LeftistHeap(LeftHeapNode node) {
        root = node;
    }

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

    public int exctractMin() {
        if (isEmpty())
            return -1;
        int minItem = root.element;
        root = merge(root.left, root.right);
        return minItem;
    }

    public LeftHeapNode getRoot() {
        return root;
    }

    public int height() {
        int level;
        if (isEmpty()) {
            level = 0;
        } else {
            level = getHeight(1, root);
        }
        return level;
    }

    /**
     * Рекурсивный метод получения высоты дерева.
     *
     * @param level текущий уровень вершины на дереве, считая от корня.
     *              Инициализируется с изначальным значением = 1.
     * @return высота дерева
     */
    private int getHeight(int level, LeftHeapNode root) {
        if (root == null) {
            return level;
        }
        LeftistHeap left = new LeftistHeap(root.getLeft());
        LeftistHeap right = new LeftistHeap(root.getRight());

        int nextLevel = level + 1;

        if (left.isEmpty() && right.isEmpty()) {
            return level;
        }

        int rightHeight = right.getHeight(nextLevel, right.getRoot());
        int leftHeight = left.getHeight(nextLevel, left.getRoot());

        if (left.isEmpty()) {
            return rightHeight;
        }

        if (right.isEmpty()) {
            return leftHeight;
        }

        if (leftHeight > rightHeight) {
            return leftHeight;
        } else {
            return rightHeight;
        }
    }
}
