/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Board {
    private int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = blocks;
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int count = 0;
        for (int r = 0; r < dimension(); ++r) {
            for (int c = 0; c < dimension(); ++c) {
                if (blocks[r][c] != goalAt(r, c)) {
                    ++count;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        return 0;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        return null;
    }

    public boolean equals(Object y) {
        return false;
    }

    public Iterable<Board> neighbors() {
        return null;
    }

    public String toString() {
        return "";
    }

    private int goalAt(int r, int c) {
        return (c == dimension() - 1 && r == dimension() - 1)
               ? 0
               : 1 + r * 3 + c;
    }
}