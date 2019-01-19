import org.junit.Test;

public class FastCollinearPointsConstructorTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructor_throws_if_null_arg() {
        new FastCollinearPoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_throws_if_null_value() {
        new FastCollinearPoints(new Point[] {new Point(3,4), null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_throws_if_duplicate() {
        new FastCollinearPoints(new Point[] {new Point(3,4), new Point(8, 2), new Point(3, 4)});
    }
}