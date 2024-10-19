public class RacingGame {
    public static void main(String[] args) {
        // Create a track with different segments
        Track track = new Track();
        track.addSegment(new TrackSegment(RoadType.STRAIGHT, RoadCondition.CLEAR, new double[]{100, 100}));
        track.addSegment(new TrackSegment(RoadType.CURVED, RoadCondition.MUDDY, new double[]{200, 300}));
        track.addSegment(new TrackSegment(RoadType.ROTATION, RoadCondition.SNOWY, new double[]{400, 400}));

        // Create bots with different difficulty levels
        Bot easyBot = BotFactory.createBot("easy", 0, 0);
        Bot mediumBot = BotFactory.createBot("medium", 0, 0);
        Bot hardBot = BotFactory.createBot("hard", 0, 0);
        Bot trainingBot = BotFactory.createBot("training", 0, 0);  // Training bot created here

        for (int step = 0; step < 10; step++) {
            easyBot.update(track, 0.1);
            mediumBot.update(track, 0.1);
            hardBot.update(track, 0.1);
            trainingBot.update(track, 0.1);

            System.out.println("Step " + step + ":");
            System.out.println("Easy Bot: " + easyBot);
            System.out.println("Medium Bot: " + mediumBot);
            System.out.println("Hard Bot: " + hardBot);
            System.out.println("Training Bot: " + trainingBot);
            System.out.println();
        }
    }
}