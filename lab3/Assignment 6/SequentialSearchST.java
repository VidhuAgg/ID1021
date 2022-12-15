public class SequentialSearchST<Key, Value> {
    private int n; // number of key-value pairs
    private Node first; // the linked list of key-value pairs

    // a helper linked list data type
    private class Node { // implementing a node which has a key value and link to next node
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST() {
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        for (Node x = first; x != null; x = x.next) { // itterating all nodes one by one
            if (key.equals(x.key))
                return x.val; // return value if key matches
        }
        return null; // return null if no key has match
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key); // delete key if value is set to null
            return;
        }

        for (Node x = first; x != null; x = x.next) { // itterating from one node to another
            if (key.equals(x.key)) { // if key exists then update value
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first); // insert new node if key has no match
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

}
