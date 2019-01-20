/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

public class Board {
    private final int[] blocks;
    private final int dimension;

    public Board(int[][] blocks) {
        this(toOneDArray(blocks), blocks.length);
    }

    private Board(int[] blocks, int dimension) {
        this.blocks = blocks;
        this.dimension = dimension;
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int count = 0;
        for (int r = 0; r < dimension(); ++r) {
            for (int c = 0; c < dimension(); ++c) {
                final int block = get(r, c);
                if (block != 0 && block != goalAt(r, c)) {
                    ++count;
                }
            }
        }
        return count;
    }

    private int get(int r, int c) {
        return blocks[r * dimension + c];
    }

    public int manhattan() {
        int i = 0;
        int manhattanDistance = 0;
        for (int r = 0; r < dimension; ++r) {
            for (int c = 0; c < dimension; ++c) {
                final int block = blocks[i++] - 1;
                if (block != -1) {
                    final int blockR = block / dimension;
                    final int blockC = block % dimension;
                    manhattanDistance += Math.abs(blockR - r) + Math.abs(blockC - c);
                }
            }
        }
        return manhattanDistance;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        return null;
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != Board.class) {
            return false;
        }
        final Board otherBoard = (Board) other;
        return Arrays.equals(this.blocks, otherBoard.blocks);
    }

    public Iterable<Board> neighbors() {
        final int indexOfBlank = indexOfBlank();
        final int blankRow = indexOfBlank / dimension;
        final int blankCol = indexOfBlank % dimension;
        final List<Board> neighbours = new ArrayList<>();
        if (blankRow > 0) {
            neighbours.add(exchanged(blankRow, blankCol, blankRow - 1, blankCol));
        }
        if (blankRow < dimension) {
            neighbours.add(exchanged(blankRow, blankCol, blankRow + 1, blankCol));
        }
        if (blankCol > 0) {
            neighbours.add(exchanged(blankRow, blankCol, blankRow, blankCol - 1));
        }
        if (blankCol < dimension) {
            neighbours.add(exchanged(blankRow, blankCol, blankRow, blankCol + 1));
        }
        return neighbours;
    }

    public String toString() {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        pw.println(dimension);
        int i = 0;
        for (int r = 0; r < dimension; ++r) {
            for (int c = 0; c < dimension; ++c) {
                final String blockStr = blocks[i] == 0 ? "   " : String.format(" %2d ", blocks[i]);
                i++;
                pw.print(blockStr);
            }
            pw.println();
        }
        return sw.toString();
    }

    private int goalAt(int r, int c) {
        return (c == dimension - 1 && r == dimension - 1)
               ? 0
               : 1 + r * dimension + c;
    }

    private Board exchanged(int destRow, int destCol, int srcRow, int srcCol) {
        final int[] cloned = blocks.clone();
        final int destBlock = cloned[destRow * dimension + destCol];
        cloned[destRow * dimension + destCol] = get(srcRow, srcCol);
        cloned[srcRow * dimension + srcCol] = destBlock;
        return new Board(cloned, dimension);
    }

    private int indexOfBlank() {
        for (int i = 0; i < blocks.length; ++i) {
            if (blocks[i] == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    private static int[] toOneDArray(int[][] blocks2d) {
        final int dimension = blocks2d.length;
        final int[] blocks = new int[dimension * dimension];
        int i = 0;
        for (int[] row : blocks2d) {
            for (int block : row) {
                blocks[i++] = block;
            }
        }
        return blocks;
    }
}