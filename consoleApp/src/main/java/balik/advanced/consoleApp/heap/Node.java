package balik.advanced.consoleApp.heap;

class Node {
    int element, sValue;
    Node left, right;

    Node(int ele) {
        this(ele, null, null);
    }

    private Node(int ele, Node left, Node right) {
        this.element = ele;
        this.left = left;
        this.right = right;
        this.sValue = 0;
    }
}
