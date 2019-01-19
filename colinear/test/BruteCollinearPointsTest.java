import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BruteCollinearPointsTest {
    @Test
    public void no_4_collinear_points() {
        BruteCollinearPoints brute = brute(p(0, 0), p(1, 1), p(2, 2), p(2, 3));

        assertEquals(0, brute.numberOfSegments());
        assertEquals(0, brute.segments().length);
    }

    @Test
    public void collinear_points_in_list_of_4() {
        BruteCollinearPoints brute = brute(p(0, 0), p(2, 2), p(1, 1), p(3, 3));

        assertEquals(1, brute.numberOfSegments());
        assertArrayEquals(brute.segments(), l(0, 0, 3, 3));
    }

    private static Point p(int x, int y) {
        return new Point(x, y);
    }

    private static LineSegment l(int x1, int y1, int x2, int y2) {
        return new LineSegment(p(x1, y1), p(x2, y2));
    }

    private static BruteCollinearPoints brute(Point... points) {
        return new BruteCollinearPoints(points);
    }

    private static boolean assertArrayEquals(LineSegment[] actual, LineSegment... expected) {
        return Arrays.asList(expected).equals(Arrays.asList(actual));
    }
}