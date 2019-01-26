import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SolverTest {
    @Test(timeout = 500)
    public void can_solve_already_solved_board() {
        final Board solved = b(1, 2, 3, 4, 5, 6, 7, 8, 0);

        final Solver solver = new Solver(solved);

        assertTrue(solver.isSolvable());
        assertEquals(0, solver.moves());
        assertEquals(Collections.singletonList(solved), solver.solution());
    }

    @Test(timeout = 500)
    public void cannot_solve_already_unsolveable_board() {
        final Board solved = b(1, 2, 3, 4, 5, 6, 8, 7, 0);

        final Solver solver = new Solver(solved);

        assertCannotSolve(solver);
    }

    @Test(timeout = 500)
    public void can_solve_more_difficult_puzzle() {
        final Board solved = b(0, 1, 3, 4, 2, 5, 7, 8, 6);

        final Solver solver = new Solver(solved);

        assertTrue(solver.isSolvable());
        assertEquals(4, solver.moves());
        final List<Board> expectedSolution = Arrays.asList(
                b(0, 1, 3, 4, 2, 5, 7, 8, 6),
                b(1, 0, 3, 4, 2, 5, 7, 8, 6),
                b(1, 2, 3, 4, 0, 5, 7, 8, 6),
                b(1, 2, 3, 4, 5, 0, 7, 8, 6),
                b(1, 2, 3, 4, 5, 6, 7, 8, 0)
        );
        assertEquals(expectedSolution, solver.solution());
    }

    @Test(timeout = 500)
    public void can_solve_4_puzzle() {
        final Board board = new Board(new int[][] {
                new int[] { 1, 2, 3, 4 },
                new int[] { 5, 6, 7, 0 },
                new int[] { 9, 10, 11, 8 },
                new int[] { 13, 14, 15, 12 },
                });
        final Board intermediate = new Board(new int[][] {
                new int[] { 1, 2, 3, 4 },
                new int[] { 5, 6, 7, 8 },
                new int[] { 9, 10, 11, 0 },
                new int[] { 13, 14, 15, 12 },
                });
        final Board solved = new Board(new int[][] {
                new int[] { 1, 2, 3, 4 },
                new int[] { 5, 6, 7, 8 },
                new int[] { 9, 10, 11, 12 },
                new int[] { 13, 14, 15, 0 },
                });

        final Solver solver = new Solver(board);

        assertTrue(solver.isSolvable());
        assertEquals(2, solver.moves());
        final List<Board> expectedSolution = Arrays.asList(
                board,
                intermediate,
                solved);
        assertEquals(expectedSolution, solver.solution());
    }

    private static void assertCannotSolve(Solver solver) {
        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertNull(solver.solution());
    }

    private static Board b(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9) {
        return new Board(new int[][] {
                new int[] { p1, p2, p3 }, new int[] { p4, p5, p6 }, new int[] { p7, p8, p9 }
        });
    }
}