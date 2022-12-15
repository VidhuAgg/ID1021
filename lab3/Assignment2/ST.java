import java.util.ArrayList;

public class ST {
    private Node root;

    private static class Node {
        public String key;
        private int value;
        private Node next;
    }

    public ST() {
        root = null;
    }

    public int get(String key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with null key");
        Node temp = root;
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return 0;
    }

    public void put(String key, int val) {
        if (key == null)
            throw new IllegalArgumentException("calls put() with null key");
        else if (root == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = val;
            root = newNode;
        } else {
            Node temp = root;
            while (temp != null) {
                if (temp.key.equals(key)) {
                    temp.value = val;
                    return;
                }
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = val;
            temp.next = newNode;
        }

    }

    public boolean contains(String key) {
        if (key == null)
            throw new IllegalArgumentException("calls contains() with null key");
        Node temp = root;
        while (temp != null) {
            if (temp.key.equals(key)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public Iterable<String> keys() {
        ArrayList<String> keys = new ArrayList<>();
        Node temp = root;
        while (temp != null) {
            keys.add(temp.key);
            temp = temp.next;
        }
        return keys;
    }

}
