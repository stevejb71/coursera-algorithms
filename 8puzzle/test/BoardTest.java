import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    private final Board example = new Board(new int[][] {
            new int[] {0, 1, 3},
            new int[] {4, 2 ,5},
            new int[] {7, 8, 6}
    });

    @Test
    public void dimensions_is_correct() {
        assertEquals(3, example.dimension());
    }

    @Test
    public void hamming_is_correct() {
        assertEquals(5, example.hamming());
    }
}