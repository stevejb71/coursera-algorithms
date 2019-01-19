import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private final class Node {
        final Item item;
        Node prev;
        Node next;

        Node(Item item, Node prev, Node next) {
            if(item == null) {
                throw new IllegalArgumentException();
            }
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("%s<-%s->%s", shortNode(prev), item, shortNode(next));
        }

        private String shortNode(Node node) {
            return node == null ? "X" : "NODE";
        }
    }

    private Node head;
    private Node last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        final Node node = new Node(item, null, head);
        if(head != null) {
            head.prev = node;
        }
        head = node;
        if(last == null) {
            last = node;
        }
        size++;
    }

    public void addLast(Item item) {
        final Node node = new Node(item, last, null);
        if(last != null) {
            last.next = node;
        }
        last = node;
        if (head == null) {
            head = node;
        }
        size++;
    }

    public Item removeFirst() {
        if(head == null) {
            throw new NoSuchElementException();
        }
        final Item removed = head.item;
        head = head.next;
        if(head!= null) head.prev = null;
        size--;
        if(size == 0) {
            last = null;
        }
        return removed;
    }

    public Item removeLast() {
        if(last == null) {
            throw new NoSuchElementException();
        }
        final Item removed = last.item;
        last = last.prev;
        if(last != null) last.next = null;
        size--;
        if(size == 0) {
            head = null;
        }
        return removed;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                final Item next = curr.item;
                curr = curr.next;
                return next;
            }
        };
    }
}