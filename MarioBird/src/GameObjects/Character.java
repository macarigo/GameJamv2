package GameObjects;

import GameObjects.Grid.Position.AbstractUnitPosition;
import GameObjects.Grid.SimpleGxGrid;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Character extends AbstractUnitPosition implements Runnable {

    private double speed = 3; //Not decided yet
    private final Rectangle rectangle;
    private Picture picture;
    private final SimpleGxGrid simpleGxGrid;
    private boolean moving = false;
    private double x;
    private double y;
    private final double terminalVelocity;
    private final int characterWidth;
    private final int characterHeight;
    private String [] characters = new String[]{"MarioBird/resources/mario.png",
            "GameJam-main/resourcesAtual/wario.jpg",
            "GameJam-main/resourcesAtual/luigi.png",
            "GameJam-main/resourcesAtual/turtle.png"};

    public Character(double col, double row, SimpleGxGrid grid) {
        super(col, row, grid);
        characterWidth = 15;
        characterHeight = 30;
        simpleGxGrid = grid;
        terminalVelocity = 4; // gravidade
        double x = grid.columnToX(col);
        double y = grid.rowToY(row);
        rectangle = new Rectangle(x, y, characterWidth, characterHeight);
        picture = new Picture(x - 30, y - 15, characters[0]);
    }

    public int getCharacterWidth() {
        return characterWidth;
    }

    public int getCharacterHeight() {
        return characterHeight;
    }

    public double getColumn() {
        return getCol();
    }

    public double getRows() {
        return getRow();
    }

    @Override
    public void show() {
        rectangle.fill();
    }

    public void render() {
        if(picture!= null) picture.draw();
    }

    @Override
    public void hide() {
        rectangle.delete();
        picture.delete();
    }

    @Override
    public void moveLeft() {

    }

    public double getX() {
        return rectangle.getX();
    }

    public double getY() {
        return rectangle.getY();
    }


    public void setMoving(boolean moving) {
        this.moving = true;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public void run() {
        /*if (!simpleGxGrid.isOutOfBoundsBot(this)) {
            if (!moving) {
                if (speed == terminalVelocity) {
                    rectangle.translate(0, speed);
                    picture.translate(0, speed);
                }
                while (speed < terminalVelocity) {
                    speed++;
                    rectangle.translate(0, speed);
                    picture.translate(0, speed);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (moving) {
            speed = 3; // altura do salto
            double temp = 0;
            double dy = 5; // salto
            if (!simpleGxGrid.isOutOfBoundsTop(this)) {
                for (int i = 0; i < dy; i++) {
                    temp = i * dy;
                    rectangle.translate(0, -temp);
                    picture.translate(0, -temp);
                }
                moving = false;
            }
        }*/

    }


    public void bringToFront() {
        picture.delete();
        picture.draw();
    }

    public void setCharacter(int choice) {
        System.out.println(Math.abs(choice%characters.length));
        picture = new Picture(picture.getX(),picture.getY(),characters[Math.abs(choice%characters.length)]);

    }
}