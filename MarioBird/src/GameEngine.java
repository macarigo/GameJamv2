import GameObjects.DisplayScore;
import GameObjects.Character;
import GameObjects.Grid.SimpleGxGrid;
import GameObjects.Menu.Background;
import GameObjects.Scoreline;
import GameObjects.Structures.Tubes;

public class GameEngine {

    private SimpleGxGrid grid;
    private Background background;
    private Character character;
    private Scoreline scoreline1, scoreline2, scoreline3;
    private Scoreline[] scorelineArray;
    private DisplayScore displayScore;
    private CollisionDetector collisionDetector;
    private Controller controller;
    private static final int FPS = 60;
    private static final long maxLoopTime = 1000 / FPS;
    private Tubes tubes1, tubes2, tubes3;
    private Tubes[] tubeArray;
    private boolean gameRunning;
    private int characterChoice = 0;


    public GameEngine(){
        grid = new SimpleGxGrid(73, 45);
        grid.init();
        background = new Background();
        character = new Character(10, 16, grid);
        controller = new Controller(this, character);

        initialState();
    }

    public void initialState() {
        background.renderMainMenu();
        character.backToStart();
        character.render();

        //lots of tubes
        tubes1 = new Tubes(70, 0, grid);
        tubes2 = new Tubes(48.33d, 0, grid);
        tubes3 = new Tubes(26.67d, 0, 175, grid);
        tubeArray = new Tubes[]{tubes1, tubes2, tubes3};

        displayScore = new DisplayScore(grid);
        scoreline1 = new Scoreline(grid, tubes1);
        scoreline2 = new Scoreline(grid, tubes2);
        scoreline3 = new Scoreline(grid, tubes3);
        scorelineArray = new Scoreline[]{scoreline1, scoreline2, scoreline3};

        collisionDetector = new CollisionDetector(character, tubeArray, scorelineArray, displayScore);

    }

    public void start() {
        long timestamp;
        long oldTimestamp;
        while (true) {
            oldTimestamp = System.currentTimeMillis();
            timestamp = System.currentTimeMillis();

            if(gameRunning) {
                run();
            }

            if (timestamp - oldTimestamp <= maxLoopTime) {

                try {
                    Thread.sleep(maxLoopTime - (timestamp - oldTimestamp));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startRunning(){
        if (!gameRunning) {
            gameRunning = true;
            background.hideMainMenu();
            background.renderGameRunning();
            displayScore.draw();
            character.hide();
            character.render();
        }
    }

    public void run() {
        character.hide();
        character.render();
        for (Tubes tube: tubeArray) {
            tube.show();
        }
        character.run();
        for (Tubes tube : tubeArray) {
            tube.moveLeft();
        }
        for (Scoreline line : scorelineArray) {
            line.moveLeft();
        }
        collisionDetector.incrementScore();

        if (grid.isOutOfBoundsBot(character) || grid.isOutOfBoundsTop(character) || collisionDetector.isCrashed()) {
            gameOver();
        }
    }

    public void gameOver() {
        gameRunning = false;
        background.gameOver();

        for (Tubes tube : tubeArray) {
            tube.hide();
        }

        character.hide();
        for (Scoreline line : scorelineArray) {
            line.hide();
        }
        displayScore.resetScore();
        displayScore.displayHighScore();
    }

    public void restart() {
        if (!gameRunning){
            background.hideGameOver();
            displayScore.hideHighScore();
            background.renderMainMenu();
            initialState();
        }
    }

    public void changeCharacter(int i) {
        if(!gameRunning){
            background.hideMainMenu();
            character.hide();
            characterChoice = characterChoice+i;
            character.setCharacter(characterChoice);
            background.renderMainMenu();
            character.render();

        }
    }
}