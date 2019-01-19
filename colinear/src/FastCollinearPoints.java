import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        final List<LineSegment> lineSegments = new ArrayList<>();
        points = points.clone();
        Arrays.sort(points);
        for(int i = 0; i < points.length - 3; ++i) {
            final LineSegment line = findCollinear(points, i);
            if(line != null) {
                lineSegments.add(line);
            }

        }
        this.lineSegments = lineSegments.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }

    private LineSegment findCollinear(Point[] points, int start) {
        final Point basePoint = points[start];
        final Comparator<Point> baseComparator = basePoint.slopeOrder();
        final Point[] others = new Point[points.length - start - 1];
        for(int i = start + 1; i < points.length; ++i) {
            others[i - start - 1] = points[i];
        }
        Arrays.sort(others, baseComparator);
        for(int i = 0; i < others.length - 2; ++i) {
            final Point p0 = others[i];
            final Point p1 = others[i + 1];
            final Point p2 = others[i + 2];
            final double baseToP0Slope = basePoint.slopeTo(p0);
            if(baseToP0Slope == basePoint.slopeTo(p1) && baseToP0Slope == basePoint.slopeTo(p2)) {
                return new LineSegment(basePoint, p2);
            }
        }
        return null;
    }

}
