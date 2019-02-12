import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PointSETTest {
    @Test
    public void nearest_works() {
        final PointSET s = new PointSET();

        s.insert(new Point2D(1, 1));
        final Point2D p22 = new Point2D(2, 2);
        s.insert(p22);
        s.insert(new Point2D(3, 3));

        assertSame(p22, s.nearest(new Point2D(2.1, 2.3)));
    }

    @Test
    public void range_when_some_points_in_range() {
        final PointSET s = new PointSET();

        final Point2D p11 = new Point2D(1, 1);
        s.insert(p11);
        final Point2D p22 = new Point2D(2, 2);
        s.insert(p22);
        final Point2D p33 = new Point2D(3, 3);
        s.insert(p33);

        final List<Point2D> inRange = range(s, new RectHV(1.8, 1.7, 3.1, 2.5));
        assertEquals(Collections.singletonList(p22), inRange);
    }

    @Test
    public void range_when_no_points_in_range() {
        final PointSET s = new PointSET();

        final Point2D p11 = new Point2D(1, 1);
        s.insert(p11);
        final Point2D p22 = new Point2D(2, 2);
        s.insert(p22);
        final Point2D p33 = new Point2D(3, 3);
        s.insert(p33);

        final List<Point2D> inRange = range(s, new RectHV(3.8, 1.7, 3.9, 2.5));
        assertEquals(Collections.emptyList(), inRange);
    }

    private List<Point2D> range(PointSET s, RectHV rect) {
        final List<Point2D> inRange = new ArrayList<>();
        s.range(rect).forEach(inRange::add);
        return inRange;
    }
}