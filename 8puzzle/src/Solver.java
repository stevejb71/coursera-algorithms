/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private int moves = -1;
    private Iterable<Board> solution;

    public Solver(Board board) {
        if (board == null) throw new IllegalArgumentException();
        final SearchNode searchNode = new SearchNode(null, board, 0);
        final MinPQ<SearchNode> searchQueue = new MinPQ<>(SearchNode.manhattanComparator);
        searchQueue.insert(searchNode);
        final SearchNode twinSearchNode = new SearchNode(null, board.twin(), 0);
        final MinPQ<SearchNode> twinSearchQueue = new MinPQ<>(SearchNode.manhattanComparator);
        twinSearchQueue.insert(twinSearchNode);
        while (true) {
            if (searchQueue.isEmpty()) {
                break;
            }
            final SearchNode bestSearchNode = searchQueue.delMin();
            if (bestSearchNode == null) {
                break;
            }
            if (bestSearchNode.board.isGoal()) {
                this.moves = bestSearchNode.moves;
                this.solution = getSolution(bestSearchNode);
                break;
            }
            bestSearchNode.insertNeighbors(searchQueue);
            if (!twinSearchQueue.isEmpty()) {
                final SearchNode bestTwinSearchNode = twinSearchQueue.delMin();
                if (bestTwinSearchNode.board.isGoal()) {
                    break;
                }
                bestTwinSearchNode.insertNeighbors(twinSearchQueue);
            }
        }
    }

    public boolean isSolvable() {
        return solution != null;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private List<Board> getSolution(SearchNode node) {
        final List<Board> boards = new ArrayList<>();
        do {
            boards.add(node.board);
            node = node.predecessor;
        } while (node != null);
        Collections.reverse(boards);
        return boards;
    }


    private static final class SearchNode {
        private static Comparator<SearchNode> manhattanComparator = (n1, n2) ->
                Integer.compare(n1.manhattan, n2.manhattan);
        private final SearchNode predecessor;
        private final Board board;
        private final int moves;
        private final int manhattan;

        SearchNode(SearchNode predecessor, Board board, int moves) {
            this.predecessor = predecessor;
            this.board = board;
            this.moves = moves;
            this.manhattan = board.manhattan() + moves;
        }

        // Assumes that this has been inserted into the queue already
        void insertNeighbors(MinPQ<SearchNode> priorityQueue) {
            for (Board neighbor : board.neighbors()) {
                if (predecessor == null || !neighbor.equals(predecessor.board)) {
                    priorityQueue.insert(new SearchNode(this, neighbor, moves + 1));
                }

            }
        }
    }
}
