import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class MyArrayDeque<Item> {
    private Object[] items;
    private int startPtr = 0;
    private int endPtr = 0;
    private int size = 0;

    MyArrayDeque() {
        this(16);
    }

    MyArrayDeque(int size) {
        this.items = new Object[size];
    }

    void add(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[endPtr] = item;
        endPtr++;
        if (endPtr == items.length) {
            endPtr = 0;
        }
        ++size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    Item get(int index) {
        final int trueIndex = (startPtr + index) % items.length;
        return (Item) items[trueIndex];
    }

    void set(int index, Item item) {
        final int trueIndex = (startPtr + index) % items.length;
        items[trueIndex] = item;
    }

    Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final Item removed = (Item) items[startPtr];
        items[startPtr] = null;
        startPtr++;
        --size;
        if (size * 4 <= items.length) {
            resize(size * 2);
        }
        return removed;
    }

    Item[] copy() {
        final Object[] copy = new Object[size()];
        copyInto(copy);
        return (Item[]) copy;
    }

    private void copyInto(Object[] copy) {
        for (int i = 0; i < size; ++i) {
            copy[i] = get(i);
        }
    }

    private void resize(int newSize) {
        final Object[] resized = new Object[Math.max(newSize, 4)];
        copyInto(resized);
        this.items = resized;
        this.startPtr = 0;
        this.endPtr = size;
    }
}


public class RandomizedQueue<Item> implements Iterable<Item> {
    private final MyArrayDeque<Item> items = new MyArrayDeque<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        items.add(item);
    }

    public Item dequeue() {
        return items.removeFirst();
    }

    public Item sample() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(StdRandom.uniform(size()));
    }

    public Iterator<Item> iterator() {
        final Item[] copied = shuffle(items.copy());
        return new Iterator<Item>() {
            private int ptr;

            @Override
            public boolean hasNext() {
                return ptr != copied.length;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return copied[ptr++];
            }
        };
    }

    private Item[] shuffle(Item[] values) {
        final Object[] shuffled = new Object[values.length];
        shuffled[0] = values[0];
        for(int i = 1; i < values.length; ++i) {
            final int insertPos = StdRandom.uniform(0, i + 1);
            if(insertPos == i) {
                shuffled[i] = values[i];
            } else {
                final Item toExchange = (Item)shuffled[insertPos];
                shuffled[insertPos] = values[i];
                shuffled[i] = toExchange;
            }
        }
        return (Item[])shuffled;
    }

    public static void main(String[] args) {
        final RandomizedQueue<Integer> q = new RandomizedQueue<>();
        for (int i = 0; i < 20; ++i) {
            q.enqueue(i);
        }
        for (int i = 0; i < 20; ++i) {
            StdOut.print(q.dequeue() + ", ");
        }
    }
}