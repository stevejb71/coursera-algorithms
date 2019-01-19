import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FastCollinearPointsTest {
    @Test
    public void no_4_collinear_points() {
        FastCollinearPoints fast = fast(p(0, 0), p(1, 1), p(2, 2), p(2, 3));

        assertEquals(0, fast.numberOfSegments());
        assertEquals(0, fast.segments().length);
    }

    @Test
    public void collinear_points_in_list_of_4() {
        FastCollinearPoints fast = fast(p(0, 0), p(2, 2), p(1, 1), p(3, 3));

        assertEquals(1, fast.numberOfSegments());
        assertArrayEquals(fast.segments(), l(0, 0, 3, 3));
    }

    private static Point p(int x, int y) {
        return new Point(x, y);
    }

    private static LineSegment l(int x1, int y1, int x2, int y2) {
        return new LineSegment(p(x1, y1), p(x2, y2));
    }

    private static FastCollinearPoints fast(Point... points) {
        return new FastCollinearPoints(points);
    }

    private static void assertArrayEquals(LineSegment[] actual, LineSegment... expected) {
        final List<String> expectedStrings = Arrays.stream(expected).map(Object::toString).collect(Collectors.toList());
        final List<String> actualStrings = Arrays.stream(actual).map(Object::toString).collect(Collectors.toList());
        assertEquals(expectedStrings, actualStrings);
    }
}