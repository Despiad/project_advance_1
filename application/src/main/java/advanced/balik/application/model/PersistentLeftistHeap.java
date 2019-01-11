package advanced.balik.application.model;

public class PersistentLeftistHeap {
    private LeftHeapNode root;
    private PersistentLeftistHeap previous;

    public PersistentLeftistHeap(LeftHeapNode node, PersistentLeftistHeap prev) {
        root = node;
        previous = prev;
    }

    public PersistentLeftistHeap getPrevious() {
        return previous;
    }

    public PersistentLeftistHeap() {
        root = null;
        previous = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public PersistentLeftistHeap clear() {
        return new PersistentLeftistHeap(null, this);
    }

    public PersistentLeftistHeap insert(int x) {
        LeftHeapNode newRoot;
        if (root != null) {
            newRoot = root.copy();
        } else {
            newRoot = null;
        }
        return new PersistentLeftistHeap(merge(new LeftHeapNode(x), newRoot), this);
    }

    private LeftHeapNode merge(LeftHeapNode leftTree, LeftHeapNode rightTree) {
        if (leftTree == null)
            return rightTree;
        if (rightTree == null)
            return leftTree;
        if (leftTree.element > rightTree.element) {
            LeftHeapNode temp = leftTree;
            leftTree = rightTree;
            rightTree = temp;
        }

        leftTree.right = merge(leftTree.right, rightTree);

        if (leftTree.left == null) {
            leftTree.left = leftTree.right;
            leftTree.right = null;
        } else {
            if (leftTree.left.sValue < leftTree.right.sValue) {
                LeftHeapNode temp = leftTree.left;
                leftTree.left = leftTree.right;
                leftTree.right = temp;
            }
            leftTree.sValue = leftTree.right.sValue + 1;
        }
        return leftTree;
    }

    public int getMin() {
        if (isEmpty())
            return -1;
        return root.element;
    }

    public PersistentLeftistHeap extractMin() {
        LeftHeapNode newLeft;
        if (root.left != null) {
            newLeft = root.left.copy();
        } else {
            newLeft = null;
        }

        LeftHeapNode newRight;
        if (root.right != null) {
            newRight = root.right.copy();
        } else {
            newRight = null;
        }


        return new PersistentLeftistHeap(merge(newLeft, newRight), this);
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
        PersistentLeftistHeap left = new PersistentLeftistHeap(root.getLeft(), this);
        PersistentLeftistHeap right = new PersistentLeftistHeap(root.getRight(), this);

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
