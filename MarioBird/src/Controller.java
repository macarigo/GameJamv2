import GameObjects.Character;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controller implements KeyboardHandler {

    private Character character;
    private GameEngine gameEngine;

    public void init() {
        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent pressedSpace = new KeyboardEvent();
        pressedSpace.setKey(KeyboardEvent.KEY_SPACE);
        pressedSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(pressedSpace);

        /*KeyboardEvent releaseSpace = new KeyboardEvent();
        releaseSpace.setKey(KeyboardEvent.KEY_SPACE);
        releaseSpace.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(releaseSpace);*/


        KeyboardEvent pressedR = new KeyboardEvent();
        pressedR.setKey(KeyboardEvent.KEY_R);
        pressedR.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(pressedR);

        KeyboardEvent pressedEnter = new KeyboardEvent();
        pressedEnter.setKey(KeyboardEvent.KEY_ENTER);
        pressedEnter.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(pressedEnter);


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            character.setMoving(false);
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
            gameEngine.setGameOver(true);
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER) {
            gameEngine.setGamerunning(true);
        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            character.setMoving(true);
        }
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}