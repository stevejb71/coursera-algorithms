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
}