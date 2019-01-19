import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class PointSlopeOrderTest {
    private final Comparator<Point> base = new Point(1, 1).slopeOrder();

    @Test
    public void slopeOrder_on_same_points() {
        assertEquals(0, base.compare(new Point(3, 3), new Point(3, 3)));
    }

    @Test
    public void slopeOrder_on_collinear_points() {
        assertEquals(0, base.compare(new Point(3, 3), new Point(5, 5)));
    }

    @Test
    public void slopeOrder_on_first_slope_less_than_second() {
        assertTrue(base.compare(new Point(3, 2), new Point(3, 3)) < 0);
    }

    @Test
    public void slopeOrder_on_first_slope_greater_than_second() {
        assertTrue(base.compare(new Point(3, 3), new Point(3, 2)) > 0);
    }
}
