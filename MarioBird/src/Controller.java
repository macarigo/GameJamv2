import GameObjects.Character;
import GameObjects.Menu.Background;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;
import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.KEY_ENTER;
import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType.KEY_PRESSED;
import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType.KEY_RELEASED;

public class Controller implements KeyboardHandler {

    private Character character;
    private GameEngine gameEngine;

    public Controller(GameEngine gameEngine, Character character) {
        this.gameEngine=gameEngine;
        this.character=character;

        Keyboard keyboard = new Keyboard(this);
        addKeyboardEvent(KEY_SPACE, KEY_PRESSED, keyboard);
        addKeyboardEvent(KEY_R, KEY_RELEASED, keyboard);
        addKeyboardEvent(KEY_ENTER, KEY_RELEASED, keyboard);
        addKeyboardEvent(KEY_ENTER, KEY_RELEASED, keyboard);
        addKeyboardEvent(KEY_LEFT, KEY_RELEASED, keyboard);
        addKeyboardEvent(KEY_RIGHT, KEY_RELEASED, keyboard);
    }

    private static void addKeyboardEvent(int key, KeyboardEventType EventType, Keyboard keyboard) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(key);
        event.setKeyboardEventType(EventType);
        keyboard.addEventListener(event);
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            character.jump();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
            gameEngine.restart();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER) {
            gameEngine.startRunning();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
                gameEngine.changeCharacter(1);
            }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
                gameEngine.changeCharacter(-1);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            character.jump();
        }
    }
}