import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void compareTo_on_equal() {
        assertEquals(0 ,new Point(0, 0).compareTo(new Point(0, 0)));
    }

    @Test
    public void compareTo_on_y_different() {
        assertTrue(new Point(1, 4).compareTo(new Point(0, 0)) > 0);
        assertTrue(new Point(2, -4).compareTo(new Point(0, 0)) < 0);
    }

    @Test
    public void compareTo_on_y_equal_x_different() {
        assertTrue(new Point(1, 0).compareTo(new Point(0, 0)) > 0);
        assertTrue(new Point(-2, 0).compareTo(new Point(0, 0)) < 0);
    }

    @Test
    public void slopeTo_non_degenerate() {
        final Point p0 = new Point(4, 4);
        final Point p1 = new Point(2, 5);

        assertEquals(-0.5, p0.slopeTo(p1), 0.0);
    }

    @Test
    public void slopeTo_on_horizontal_line_is_positive_zero_when_p0_is_left_of_p1() {
        final Point p0 = new Point(4, 4);
        final Point p1 = new Point(8, 4);

        double slope = p0.slopeTo(p1);

        assertEquals(0.0, slope, 0.0);
        assertEquals(Double.POSITIVE_INFINITY, 1.0 / slope, 0.0);
    }

    @Test
    public void slopeTo_on_horizontal_line_is_positive_zero_when_p1_is_left_of_p0() {
        final Point p0 = new Point(8, 4);
        final Point p1 = new Point(4, 4);

        double slope = p0.slopeTo(p1);

        assertEquals(0.0, slope, 0.0);
        assertEquals(Double.POSITIVE_INFINITY, 1.0 / slope, 0.0);
    }

    @Test
    public void slopeTo_on_vertical_line_with_p0_below_p1_is_positive_infinity() {
        final Point p0 = new Point(4, 4);
        final Point p1 = new Point(4, 5);

        assertEquals(Double.POSITIVE_INFINITY, p0.slopeTo(p1), 0.0);
    }

    @Test
    public void slopeTo_on_vertical_line_with_p1_below_p0_is_positive_infinity() {
        final Point p0 = new Point(4, 5);
        final Point p1 = new Point(4, 4);

        assertEquals(Double.POSITIVE_INFINITY, p0.slopeTo(p1), 0.0);
    }

    @Test
    public void slopeTo_on_vertical_line_of_point_with_itself_is_negative_infinity() {
        final Point p0 = new Point(4, 5);
        final Point p1 = new Point(4, 5);

        assertEquals(Double.NEGATIVE_INFINITY, p0.slopeTo(p1), 0.0);
    }
}