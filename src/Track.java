import java.util.ArrayList;

class Track {
    private ArrayList<TrackSegment> segments = new ArrayList<>();

    public void addSegment(TrackSegment segment) {
        segments.add(segment);
    }

    public TrackSegment getCurrentSegment(double x, double y) {
        // Simplified logic to return the current segment, you can make this more complex based on position
        return segments.get(0);  // Always return the first segment for now (for simplicity)
    }

    public double[] getNextCheckpoint(double x, double y) {
        // Simplified logic, return the checkpoint of the first segment
        return segments.get(0).getCheckpoint();
    }
}