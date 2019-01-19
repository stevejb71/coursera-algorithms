import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyArrayDequeTest {
    private final MyArrayDeque<Integer> d = new MyArrayDeque<>(3);

    @Test
    public void adding_1() {
        d.add(5);

        assertFalse(d.isEmpty());
        assertEquals(1, d.size());
        assertEquals((Integer)5, d.get(0));
    }

    @Test
    public void adding_several() {
        d.add(5);
        d.add(4);

        assertFalse(d.isEmpty());
        assertEquals(2, d.size());
        assertEquals((Integer)5, d.get(0));
        assertEquals((Integer)4, d.get(1));
    }

    @Test
    public void resizing_up() {
        d.add(1);
        d.add(2);
        d.add(3);
        d.add(4);

        assertEquals(4, d.size());
    }

    @Test
    public void adding_and_removing() {
        d.add(1);
        d.add(2);
        d.add(3);
        d.add(4);
        d.add(5);
        d.removeFirst();
        d.removeFirst();

        assertEquals(3, (int)d.get(0));
        assertEquals(4, (int)d.get(1));
        assertEquals(5, (int)d.get(2));
    }

    @Test
    public void adding_and_removing_many() {
        for(int i = 0; i < 1000; ++i) {
            d.add(i);
        }
        for(int i = 0; i < 999; ++i) {
            d.removeFirst();
        }
        assertEquals(1, d.size());
        assertEquals(999, (int)d.get(0));
        d.removeFirst();
        assertEquals(0, d.size());
    }

    @Test
    public void adding_and_removing_many_interleaved() {
        for(int i = 0; i < 10; ++i) {
            d.add(i);
            d.add(i);
            d.add(i);
            d.add(i);
            d.removeFirst();
            d.removeFirst();
            d.removeFirst();
            d.removeFirst();
        }
        assertEquals(0, d.size());
    }
}
