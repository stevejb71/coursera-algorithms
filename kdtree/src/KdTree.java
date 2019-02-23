import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KdTree {
    private interface Consumer<T> {
        void invoke(T t);
    }

    private static final class Node {
        private final Point2D value;
        private final int level;
        private Node left;
        private Node right;

        public Node(Point2D value, int level) {
            this.value = value;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Node{value=" + value + '}';
        }

        // true if inserted (i.e. no duplicate already there)
        public boolean insert(Point2D p) {
            if (value.equals(p)) {
                return false;
            }
            else {
                if (level % 2 == 0) {
                    if (p.x() < value.x()) {
                        return left == null ? insertLeft(p) : left.insert(p);
                    }
                    else {
                        return right == null ? insertRight(p) : right.insert(p);
                    }
                }
                else {
                    if (p.y() < value.y()) {
                        return left == null ? insertLeft(p) : left.insert(p);
                    }
                    else {
                        return right == null ? insertRight(p) : right.insert(p);
                    }

                }

            }
        }

        public Node containingNode(Point2D p) {
            if (value.equals(p)) {
                return this;
            }
            else {
                if (level % 2 == 0) {
                    if (p.x() < value.x()) {
                        return left == null ? null : left.containingNode(p);
                    }
                    else {
                        return right == null ? null : right.containingNode(p);
                    }
                }
                else {
                    if (p.y() < value.y()) {
                        return left == null ? null : left.containingNode(p);
                    }
                    else {
                        return right == null ? null : right.containingNode(p);
                    }
                }
            }
        }

        public void iterate(Consumer<Point2D> consumer) {
            consumer.invoke(this.value);
            if (left != null) {
                left.iterate(consumer);
            }
            if (right != null) {
                right.iterate(consumer);
            }
        }

        public void range(RectHV rect, List<Point2D> collector) {
            if (rect.contains(value)) {
                collector.add(value);
            }
            if (level % 2 == 0) {
                final double x = value.x();
                if (left != null) {
                    if (rect.xmin() <= x) {
                        left.range(rect, collector);
                    }
                }
                if (right != null) {
                    if (rect.xmax() >= x) {
                        right.range(rect, collector);
                    }
                }
            }
            else {
                final double y = value.y();
                if (left != null) {
                    if (rect.ymin() <= y) {
                        left.range(rect, collector);
                    }
                }
                if (right != null) {
                    if (rect.ymax() >= y) {
                        right.range(rect, collector);
                    }
                }
            }
        }

        public Point2D nearest(Point2D p, double minSoFar, Point2D candidate) {
            final double distSq = p.distanceSquaredTo(this.value);
            if (distSq < minSoFar) {
                minSoFar = distSq;
                candidate = this.value;
            }
            if (level % 2 == 0) {
                final double x = p.x();
                final boolean pointIsOnLeft = x < value.x();
                if (pointIsOnLeft) {
                    if (left != null) {
                        final Point2D leftCandidate = left.nearest(p, minSoFar, candidate);
                        final double distToLeftCandidate = p.distanceSquaredTo(leftCandidate);
                        if (distToLeftCandidate < minSoFar) {
                            minSoFar = distToLeftCandidate;
                            candidate = leftCandidate;
                        }
                    }
                    if (right != null && diffSqr(value.x(), x) < minSoFar) {
                        final Point2D rightCandidate = right.nearest(p, minSoFar, candidate);
                        if (p.distanceSquaredTo(rightCandidate) < minSoFar) {
                            return rightCandidate;
                        }
                    }
                }
                else {
                    if (right != null) {
                        final Point2D rightCandidate = right.nearest(p, minSoFar, candidate);
                        final double distToRightCandidate = p.distanceSquaredTo(rightCandidate);
                        if (distToRightCandidate < minSoFar) {
                            minSoFar = distToRightCandidate;
                            candidate = rightCandidate;
                        }
                    }
                    if (left != null && diffSqr(value.x(), x) < minSoFar) {
                        final Point2D leftCandidate = left.nearest(p, minSoFar, candidate);
                        if (p.distanceSquaredTo(leftCandidate) < minSoFar) {
                            return leftCandidate;
                        }
                    }
                }
            }
            else {
                final double y = p.y();
                final boolean pointIsOnBottom = y < value.y();
                if (pointIsOnBottom) {
                    if (left != null) {
                        final Point2D leftCandidate = left.nearest(p, minSoFar, candidate);
                        final double distToLeftCandidate = p.distanceSquaredTo(leftCandidate);
                        if (distToLeftCandidate < minSoFar) {
                            minSoFar = distToLeftCandidate;
                            candidate = leftCandidate;
                        }
                    }
                    if (right != null && diffSqr(value.y(), y) < minSoFar) {
                        final Point2D rightCandidate = right.nearest(p, minSoFar, candidate);
                        if (p.distanceSquaredTo(rightCandidate) < minSoFar) {
                            return rightCandidate;
                        }
                    }

                }
                else {
                    if (right != null) {
                        final Point2D rightCandidate = right.nearest(p, minSoFar, candidate);
                        final double distToRightCandidate = p.distanceSquaredTo(rightCandidate);
                        if (distToRightCandidate < minSoFar) {
                            minSoFar = distToRightCandidate;
                            candidate = rightCandidate;
                        }
                    }
                    if (left != null && diffSqr(value.y(), y) < minSoFar) {
                        final Point2D leftCandidate = left.nearest(p, minSoFar, candidate);
                        if (p.distanceSquaredTo(leftCandidate) < minSoFar) {
                            return leftCandidate;
                        }
                    }

                }
            }
            return candidate;
        }

        private static double diffSqr(double a, double b) {
            return (a - b) * (a - b);
        }

        private boolean insertLeft(Point2D p) {
            left = new Node(p, level + 1);
            return true;
        }

        private boolean insertRight(Point2D p) {
            right = new Node(p, level + 1);
            return true;
        }
    }

    private Node root = null;
    private int size = 0;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        throwIfNull(p);
        if (root == null) {
            root = new Node(p, 0);
            size = 1;
        }
        else {
            if (root.insert(p)) {
                size++;
            }
        }
    }

    public boolean contains(Point2D p) {
        throwIfNull(p);
        return root != null && root.containingNode(p) != null;
    }

    public void draw() {
        if (root != null) {
            root.iterate(Point2D::draw);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        throwIfNull(rect);
        if (root == null) {
            return Collections.emptyList();
        }
        final List<Point2D> inRange = new ArrayList<>();
        root.range(rect, inRange);
        return inRange;
    }

    public Point2D nearest(Point2D query) {
        throwIfNull(query);
        if (root == null) {
            return null;
        }
        return root.nearest(query, Double.POSITIVE_INFINITY, null);
    }

    private static void throwIfNull(Object x) {
        if (x == null) throw new IllegalArgumentException();
    }
}
