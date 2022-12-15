
public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // root of BST

    private class Node {
        private Key key; // sorted by key
        private Value val; // associated data
        private Node left, right; // left and right subtrees
        private int size; // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BST() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key); // call the function with root node
    }

    private Value get(Node x, Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key"); // check null equality
        if (x == null)
            return null; // check node validity
        int cmp = key.compareTo(x.key); // comparator for current node key and given key
        if (cmp < 0)
            return get(x.left, key); // recursively visit left node if current key greater than given key
        else if (cmp > 0)
            return get(x.right, key); // recursively visit right node if current key less than given key
        else
            return x.val; // return value if match found
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, val); // call function with root node
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1); // base case to insert new node upon reaching end of branch
        int cmp = key.compareTo(x.key); // comparator for currrent node and given key
        if (cmp < 0)
            x.left = put(x.left, key, val); // left recursive call similar to get
        else if (cmp > 0)
            x.right = put(x.right, key, val); // right recursive call similar to get
        else
            x.val = val; // last case if key matched then update value
        x.size = 1 + size(x.left) + size(x.right);
        return x; // join tree back up
    }

    public void printAlphabetic() {
        printAlphabetic(root); // call function with root node
    }

    private void printAlphabetic(Node current) {
        if (current == null) {
            return;// base case for recursion
        }
        printAlphabetic(current.left); // recursively call left node which is smaller than current node
        System.out.println(current.key); // print out current node
        printAlphabetic(current.right); // recursively call right node which is larger than current node
    }

    public void printReverse() {
        printReverse(root);// call function with root node
    }

    private void printReverse(Node current) {
        if (current == null) {
            return;// base case for recursion
        }
        printReverse(current.right);// recursively call right node which is larger than current node
        System.out.println(current.key);// print out current node
        printReverse(current.left);// recursively call left node which is smaller than current node
    }

    public Iterable<Key> keys() {
        if (isEmpty())
            return new Queue<Key>();
        Queue<Key> queue = new Queue<Key>();
        return keys(root, queue);
    }

    public Iterable<Key> keys(Node current, Queue<Key> queue) {
        if (current == null) {
            return null;// base case for recursion
        }
        keys(current.left, queue);
        queue.enqueue(current.key);
        keys(current.right, queue);
        return queue;
    }

}
