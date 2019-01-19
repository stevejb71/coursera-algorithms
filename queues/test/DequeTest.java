import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {
    private final Deque<Integer> d = new Deque<>();

    @Test
    public void deque_initially_empty() {
        assertTrue(d.isEmpty());
    }

    @Test
    public void not_empty_after_adding_first() {
        d.addFirst(7);

        assertFalse(d.isEmpty());
    }

    @Test
    public void not_empty_after_adding_last() {
        d.addLast(7);

        assertFalse(d.isEmpty());
    }

    @Test
    public void addFirst_incs_size() {
        assertEquals(0, d.size());
        d.addFirst(1);
        assertEquals(1, d.size());
        d.addFirst(2);
        assertEquals(2, d.size());
    }

    @Test
    public void addLast_incs_size() {
        assertEquals(0, d.size());
        d.addLast(1);
        assertEquals(1, d.size());
        d.addLast(2);
        assertEquals(2, d.size());
    }

    @Test
    public void iterator_over_adds_to_first() {
        d.addFirst(10);
        d.addFirst(4);

        final Iterator<Integer> it = d.iterator();
        assertTrue(it.hasNext());
        assertEquals((Integer)4, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer)10, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void iterator_over_adds_to_last() {
        d.addLast(4);
        d.addLast(10);

        final Iterator<Integer> it = d.iterator();
        assertTrue(it.hasNext());
        assertEquals((Integer)4, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer)10, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void iterator_over_adds_to_first_and_last() {
        d.addLast(4);
        d.addLast(10);
        d.addFirst(5);

        final Iterator<Integer> it = d.iterator();
        assertTrue(it.hasNext());
        assertEquals((Integer)5, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer)4, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer)10, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void removing_all_firsts_updates_the_last_node() {
        d.addFirst(5);
        d.addFirst(6);
        d.removeFirst();
        d.removeFirst();

        try {
            d.removeLast();
            fail();
        } catch(NoSuchElementException e) {
            // ok
        }
    }

    @Test
    public void removing_all_lasts_updates_the_head_node() {
        d.addLast(5);
        d.addLast(6);
        d.removeLast();
        d.removeLast();

        try {
            d.removeFirst();
            fail();
        } catch(NoSuchElementException e) {
            // ok
        }
    }

    @Test
    public void addFirst_then_removeFirst_returns_what_was_added() {
        d.addFirst(10);
        assertEquals((Integer)10, d.removeFirst());
    }

    @Test
    public void addFirst_then_removeLast_returns_what_was_added() {
        d.addFirst(8);
        assertEquals((Integer)8, d.removeLast());
    }

    @Test
    public void addLast_then_removeLast_returns_what_was_added() {
        d.addLast(1);
        assertEquals((Integer)1, d.removeLast());
    }

    @Test
    public void addLast_then_removeFirst_returns_what_was_added() {
        d.addLast(4);
        assertEquals((Integer)4, d.removeFirst());
    }

    @Test
    public void coursera_test() {
        d.addLast(1);
        assertEquals(1, (int)d.removeFirst());
        d.addLast(3);
        d.addLast(4);
        d.addLast(5);
        assertEquals(5, (int)d.removeLast());
        Iterator<Integer> iter = d.iterator();
        assertEquals(3, (int) iter.next());
        assertEquals(4, (int) iter.next());
        assertFalse(iter.hasNext());
    }
}