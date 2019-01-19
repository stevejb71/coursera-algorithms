/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if(point == null) {
                throw new IllegalArgumentException();
            }
        }
        for(int i = 0; i < points.length - 1; ++i) {
            for(int j = i + 1; j < points.length; ++j) {
                if(points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public int numberOfSegments() {
        return 0;
    }

    public LineSegment[] segments() {
        return null;
    }
}
