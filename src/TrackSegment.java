class TrackSegment {
    private RoadType roadType;
    private RoadCondition roadCondition;
    private double[] checkpoint;  // (x, y) coordinates

    public TrackSegment(RoadType roadType, RoadCondition roadCondition, double[] checkpoint) {
        this.roadType = roadType;
        this.roadCondition = roadCondition;
        this.checkpoint = checkpoint;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public RoadCondition getRoadCondition() {
        return roadCondition;
    }

    public double[] getCheckpoint() {
        return checkpoint;
    }
}
