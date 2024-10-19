abstract class Bot {
    protected double x, y;
    protected double speed;
    protected double angle;
    protected double maxSpeed;
    protected double acceleration;
    protected double turnSpeed;

    public Bot(double x, double y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
        this.angle = 0;
    }

    //The racing track
    public void update(Track track, double dt) {
        TrackSegment currentSegment = track.getCurrentSegment(this.x, this.y);
        applyRoadConditions(currentSegment);

        double[] nextCheckpoint = track.getNextCheckpoint(this.x, this.y);
        steerTowards(nextCheckpoint);

        // Update speed and position
        speed = Math.min(speed + acceleration * dt, maxSpeed);
        x += Math.cos(Math.toRadians(angle)) * speed * dt;
        y += Math.sin(Math.toRadians(angle)) * speed * dt;
    }

    protected void steerTowards(double[] checkpoint) {
        double angleToCheckpoint = Math.toDegrees(Math.atan2(checkpoint[1] - y, checkpoint[0] - x));
        double angleDiff = (angleToCheckpoint - angle) % 360;
        if (angleDiff > 180) {
            angleDiff -= 360;
        }
        angle += Math.max(-turnSpeed, Math.min(turnSpeed, angleDiff));
    }

    private void applyRoadConditions(TrackSegment segment) {
        // Adjust bot behavior based on the road type and condition
        switch (segment.getRoadType()) {
            case STRAIGHT:  // Boost speed on straight roads
                acceleration = Math.min(acceleration * 1.2, maxSpeed);
                break;
            case CURVED:    // Slower turns on curved roads
                turnSpeed *= 0.9;
                break;
            case ROTATION:  // Sharper turns required for rotation segments
                turnSpeed *= 0.7;
                break;
        }

        switch (segment.getRoadCondition()) {
            case MUDDY: // Slower max speed on muddy roads
                maxSpeed *= 0.7;
                acceleration *= 0.7;
                break;
            case SNOWY: // Slightly slower on snowy roads
                maxSpeed *= 0.8;
                acceleration *= 0.8;
                break;
            case CLEAR: // Clear roads, no changes
                break;
        }
    }
    @Override
    public String toString() {
        return String.format("Position: (%.2f, %.2f), Angle: %.2f, Speed: %.2f", x, y, angle, speed);
    }
}

enum RoadType {
    STRAIGHT, CURVED, ROTATION
}

enum RoadCondition {
    MUDDY, SNOWY, CLEAR
}

class EasyBot extends Bot {
    public EasyBot(double x, double y) {
        super(x, y);
        this.maxSpeed = 100;
        this.acceleration = 5;
        this.turnSpeed = 3;
    }
}

class MediumBot extends Bot {
    public MediumBot(double x, double y) {
        super(x, y);
        this.maxSpeed = 150;
        this.acceleration = 7;
        this.turnSpeed = 4;
    }
}

class HardBot extends Bot {
    public HardBot(double x, double y) {
        super(x, y);
        this.maxSpeed = 200;
        this.acceleration = 10;
        this.turnSpeed = 5;
    }
}

class TrainingBot extends Bot {
    public TrainingBot(double x, double y) {
        super(x, y);
        this.maxSpeed = 50;
        this.acceleration = 3;
        this.turnSpeed = 2;
    }
}

class BotFactory {
    public static Bot createBot(String difficulty, double x, double y) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                return new EasyBot(x, y);
            case "medium":
                return new MediumBot(x, y);
            case "hard":
                return new HardBot(x, y);
            case "training":
                return new TrainingBot(x, y);
            default:
                throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
        }
    }
}
