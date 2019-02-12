import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {
    private final Set<Point2D> points = new TreeSet<>();

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        points.add(p);
    }

    public boolean contains(Point2D p) {
        return points.contains(throwIfNull(p));
    }

    public void draw() {
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        throwIfNull(rect);
        return points.stream().filter(rect::contains).collect(Collectors.toList());
    }

    public Point2D nearest(Point2D query) {
        throwIfNull(query);
        Point2D nearest = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Point2D point : points) {
            final double dist = point.distanceSquaredTo(query);
            if (dist < minDist) {
                minDist = dist;
                nearest = point;
            }
        }
        return nearest;
    }


    private static <A> A throwIfNull(A x) {
        if (x == null) throw new IllegalArgumentException();
        return x;
    }
}
