import org.junit.Test;

public class BruteCollinearPointsConstructorTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructor_throws_if_null_arg() {
        new BruteCollinearPoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_throws_if_null_value() {
        new BruteCollinearPoints(new Point[] {new Point(3,4), null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_throws_if_duplicate() {
        new BruteCollinearPoints(new Point[] {new Point(3,4), new Point(8, 2), new Point(3, 4)});
    }
}