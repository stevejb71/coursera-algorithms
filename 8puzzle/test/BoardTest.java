import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    private final Board example = new Board(new int[][] {
            new int[] {0, 1, 3},
            new int[] {4, 2 ,5},
            new int[] {7, 8, 6}
    });
    private final Board websiteExample = new Board(new int[][] {
            new int[] {8, 1, 3},
            new int[] {4, 0 ,2},
            new int[] {7, 6, 5}
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
        assertEquals(4, example.hamming());
    }

    @Test
    public void hamming_on_website_example_board() {
        assertEquals(5, websiteExample.hamming());
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

    @Test
    public void neighbours() {
        final List<Board> neighbours = new ArrayList<>();
        websiteExample.neighbors().forEach(neighbours::add);
        assertEquals(4, neighbours.size());
        assertTrue(neighbours.contains(b(8, 0, 3,  4, 1, 2,  7, 6, 5)));
        assertTrue(neighbours.contains(b(8, 1, 3,  0, 4, 2,  7, 6, 5)));
        assertTrue(neighbours.contains(b(8, 1, 3,  4, 2, 0,  7, 6, 5)));
        assertTrue(neighbours.contains(b(8, 1, 3,  4, 6, 2,  7, 0, 5)));
    }

    @Test
    public void manhattan() {
        assertEquals(10, websiteExample.manhattan());
        assertEquals(0, goal.manhattan());
    }

    @Test
    public void twin_on_goal_has_manhattan_distance_of_2() {
        assertEquals(2, goal.twin().manhattan());
    }

    @Test
    public void exact_check_on_twin() {
        assertEquals(b(0, 2, 3, 4, 1 ,5, 7, 8, 6), example.twin());
    }

    private static Board b(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9) {
        return new Board(new int[][] { new int[] {p1, p2, p3}, new int[] {p4, p5, p6}, new int[] {p7, p8, p9} });
    }
}