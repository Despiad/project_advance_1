package balik.console.impl;

public interface VEBTree {

    /**
     * Insert x into tree
     **/
    void insert(int x);


    /**
     * Delete x into tree
     */
    void remove(int x);

    /**
     * Returns true if x is in the tree,
     * false otherwise
     **/
    boolean find(int x);

    /***
     * Returns element before x,
     * or -1 if x is the minimum
     * */
    int prev(int x);

    /***
     * Returns element after x,
     * or -1 if x is the maximum
     * */
    int next(int x);

    /**
     * Returns the minimum value in the tree
     * or -1 if the tree is empty
     **/
    int min();

    /**
     * Returns the maximum value in the tree
     * or -1 if the tree is empty
     **/
    int max();

    /**
     * Returns true if tree is empty,
     * false otherwise
     **/
    boolean empty();

}
