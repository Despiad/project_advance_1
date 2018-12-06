package balik.advanced.consoleApp.heap;

class LeftHeapNode {
    int element, sValue;
    LeftHeapNode left, right;

    LeftHeapNode(int ele) {
        this(ele, null, null);
    }

    private LeftHeapNode(int ele, LeftHeapNode left, LeftHeapNode right) {
        this.element = ele;
        this.left = left;
        this.right = right;
        this.sValue = 0;
    }
}
