import GameObjects.DisplayScore;
import GameObjects.Character;
import GameObjects.Scoreline;
import GameObjects.Structures.Tubes;

public class CollisionDetector {

    private final Character character;
    private final Tubes[] tubs;
    private final Scoreline[] scoreline;
    private final DisplayScore score;


    public CollisionDetector(Character character, Tubes[] tubs, Scoreline[] scoreline, DisplayScore score) {
        this.character = character;
        this.tubs = tubs;
        this.scoreline = scoreline;
        this.score = score;


    }

    public boolean isCrashed() {
        for (Tubes tube : tubs) {

            if ((character.getX() + character.getMarioWidth() >= tube.getXBot() &&
                    character.getX() <= tube.getXBot() + tube.getWidth() &&
                    character.getY() + character.getMarioHeight() >= tube.getYBot())) {
                return true;
            } else if (character.getX() + character.getMarioWidth() >= tube.getXTop() &&
                    character.getX() <= tube.getXTop() + tube.getWidth() &&
                    character.getY() <= tube.getYTop() + tube.getHeightTop()) {
                return true;
            }
        }
        return false;
    }


    public void incrementScore() {
        for (Scoreline line : scoreline) {
            if (character.getX() >= line.getX() && line.canScore()) {
                score.setScore();
                line.setCanScore(false);
            } else if (line.isReset()) {
                line.setCanScore(true);
                line.setReset(false);
            }
        }
    }
}