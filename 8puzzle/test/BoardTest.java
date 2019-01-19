import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    private final Board example = new Board(new int[][] {
            new int[] {0, 1, 3},
            new int[] {4, 2 ,5},
            new int[] {7, 8, 6}
    });
    private final Board goal = new Board(new int[][] {
            new int[] {1, 2, 3},
            new int[] {4, 5, 6},
            new int[] {7, 8, 0}
    });

    @Test
    public void dimensions_is_correct() {
        assertEquals(3, example.dimension());
    }

    @Test
    public void hamming_is_correct() {
        assertEquals(0, goal.hamming());
        assertEquals(5, example.hamming());
    }

    @Test
    public void isGoal_on_non_goal_board() {
        assertFalse(example.isGoal());
    }

    @Test
    public void isGoal_on_goal_board() {
        assertTrue(goal.isGoal());
    }

    @Test
    public void equals_edge_cases() {
        assertNotEquals(goal, null);
        assertEquals(goal, goal);
    }

    @Test
    public void equals_on_copy() {
        final Board exampleCopy = new Board(new int[][] {
                new int[] {0, 1, 3},
                new int[] {4, 2 ,5},
                new int[] {7, 8, 6}
        });
        assertEquals(example, exampleCopy);
        assertEquals(exampleCopy, example);
    }

    @Test
    public void equals_on_distinct() {
        assertNotEquals(example, goal);
    }
}