/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
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
        for (int i = 0; i < points.length - 3; ++i) {
            final Point p0 = points[i];
            for (int j = i + 1; j < points.length - 2; ++j) {
                final Point p1 = points[j];
                for (int k = j + 1; k < points.length - 1; ++k) {
                    final Point p2 = points[k];
                    for (int l = k + 1; l < points.length; ++l) {
                        final Point p3 = points[l];
                        if(collinear(p0, p1, p2, p3)) {
                            lineSegments.add(new LineSegment(p0, p3));
                        }
                    }
                }

            }
        }
        this.lineSegments = lineSegments.toArray(new LineSegment[0]);
    }

    private boolean collinear(Point p0, Point p1, Point p2, Point p3) {
        final Comparator<Point> base = p0.slopeOrder();
        return base.compare(p1, p2) == 0 && base.compare(p1, p3) == 0;
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }
}
