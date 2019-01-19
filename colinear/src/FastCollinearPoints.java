public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints() {
        this.lineSegments = new LineSegment[0];
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}
