import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class KdTreeTest {
    private final KdTree tree = new KdTree();

    @Test
    public void initially_empty() {
        assertTrue(tree.isEmpty());
    }

    @Test
    public void initially_size_0() {
        assertEquals(0, tree.size());
    }

    @Test
    public void not_empty_after_single_insertion() {
        tree.insert(p(1, 1));

        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
    }

    @Test
    public void contains_no_point_if_empty() {
        assertFalse(tree.contains(p(1, 1)));
    }

    @Test
    public void contains_single_inserted_point() {
        tree.insert(p(1, 1));

        assertFalse(tree.contains(p(2, 1)));
        assertFalse(tree.contains(p(1, 2)));
        assertTrue(tree.contains(p(1, 1)));
    }

    @Test
    public void after_2_inserted_points() {
        tree.insert(p(1, 1));
        tree.insert(p(2, 2));

        assertEquals(2, tree.size());
        assertFalse(tree.contains(p(2, 1)));
        assertFalse(tree.contains(p(1, 2)));
        assertTrue(tree.contains(p(1, 1)));
        assertTrue(tree.contains(p(2, 2)));
    }

    @Test
    public void size_unchanged_if_duplicate_inserted() {
        tree.insert(p(1, 1));
        tree.insert(p(1, 1));
        tree.insert(p(2, 2));
        tree.insert(p(2, 2));

        assertEquals(2, tree.size());
    }

    @Test
    public void range_when_some_points_in_range() {
        final Point2D p11 = new Point2D(1, 1);
        tree.insert(p11);
        final Point2D p22 = new Point2D(2, 2);
        tree.insert(p22);
        final Point2D p33 = new Point2D(3, 3);
        tree.insert(p33);

        final List<Point2D> inRange = range(new RectHV(1.8, 1.7, 3.1, 2.5));
        assertEquals(Collections.singletonList(p22), inRange);
    }

    @Test
    public void range_when_no_points_in_range() {
        final Point2D p11 = new Point2D(1, 1);
        tree.insert(p11);
        final Point2D p22 = new Point2D(2, 2);
        tree.insert(p22);
        final Point2D p33 = new Point2D(3, 3);
        tree.insert(p33);

        final List<Point2D> inRange = range(new RectHV(3.8, 1.7, 3.9, 2.5));
        assertEquals(Collections.emptyList(), inRange);
    }

    @Test
    public void range_test_example() {
        insert(0.372, 0.497);
        insert(0.564, 0.413);
        insert(0.226, 0.577);
        insert(0.144, 0.179);
        insert(0.083, 0.51);
        insert(0.32, 0.708);
        insert(0.417, 0.362);
        insert(0.862, 0.825);
        insert(0.785, 0.725);
        insert(0.499, 0.208);

        final RectHV rect = new RectHV(0.193, 0.087, 0.901, 0.219);
        final List<Point2D> expected = new ArrayList<>();
        expected.add(p(0.499, 0.208));

        assertEquals(expected, range(rect));
    }

    @Test
    public void range_test_example2() {
        insert(0.7, 0.2);
        insert(0.5, 0.4);
        insert(0.2, 0.3);
        insert(0.4, 0.7);
        insert(0.9, 0.6);

        final RectHV rect = new RectHV(0.37, 0.05, 0.49, 0.73);
        final List<Point2D> expected = new ArrayList<>();
        expected.add(p(0.4, 0.7));

        assertEquals(expected, range(rect));
    }

    @Test
    public void nearest_works_in_simple_case() {
        tree.insert(new Point2D(1, 1));
        final Point2D p22 = new Point2D(2, 2);
        tree.insert(p22);
        tree.insert(new Point2D(3, 3));

        assertSame(p22, tree.nearest(new Point2D(2.1, 2.3)));
    }

    @Test
    public void nearest_example() {
        insert(0.7, 0.2);
        insert(0.5, 0.4);
        insert(0.2, 0.3);
        insert(0.4, 0.7);
        insert(0.9, 0.6);

        assertEquals(new Point2D(0.4, 0.7), tree.nearest(new Point2D(0.535, 0.861)));
    }

    /*
      A  0.372 0.497
      B  0.564 0.413
      C  0.226 0.577
      D  0.144 0.179
      E  0.083 0.51
      F  0.32 0.708
      G  0.417 0.362
      H  0.862 0.825
      I  0.785 0.725
      J  0.499 0.208
     */
    @Test
    public void nearest_example2() {
        insert(0.372, 0.497);
        insert(0.564, 0.413);
        insert(0.226, 0.577);
        insert(0.144, 0.179);
        insert(0.083, 0.51 );
        insert(0.32,  0.708);
        insert(0.417, 0.362);
        insert(0.862, 0.825);
        insert(0.785, 0.725);
        insert(0.499, 0.208);

        assertEquals(new Point2D(0.499, 0.208), tree.nearest(new Point2D(0.47, 0.12)));
    }

    private List<Point2D> range(RectHV rect) {
        final List<Point2D> inRange = new ArrayList<>();
        tree.range(rect).forEach(inRange::add);
        return inRange;
    }

    private void insert(double x, double y) {
        tree.insert(p(x, y));
    }

    private static Point2D p(double x, double y) {
        return new Point2D(x, y);
    }
}