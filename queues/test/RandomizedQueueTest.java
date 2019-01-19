import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;


public class RandomizedQueueTest {
    private final RandomizedQueue<Integer> q = new RandomizedQueue<>();

    @Test
    public void many_enqueue_and_dequeue() {
        for(int i = 0; i < 1000; ++i) {
            q.enqueue(i);
        }
        for(int i = 0; i < 1000; ++i) {
            q.dequeue();
        }

        assertTrue(q.isEmpty());
    }

    @Test
    public void iterators_return_the_same_values() {
        for(int i = 0; i < 1000; ++i) {
            q.enqueue(i);
        }
        final List<Integer> l1 = new ArrayList<>();
        final List<Integer> l2 = new ArrayList<>();
        final Iterator<Integer> i1 = q.iterator();
        final Iterator<Integer> i2 = q.iterator();
        for(int i = 0; i < 1000; ++i) {
            l1.add(i1.next());
            l2.add(i2.next());
        }
        assertNotEquals(l1, l2);

        Collections.sort(l1);
        Collections.sort(l2);

        assertEquals(l1, l2);
    }

    private <A> List<A> toList(Iterator<A> iter) {
        final List<A> list = new ArrayList<>();
        while(iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }
}