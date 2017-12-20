import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {
    private GameObject gameObject;
    private Direction pivotDir;
    private int x;
    private int y;

    public Tile() {

    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(GameObject gm, int x, int y) {
        this.gameObject = gm;
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        if (this.gameObject == GameObject.S_U) {
            return new ImageIcon(Game.snakeUpImage).getImage();
        } else if (this.gameObject == GameObject.S_D) {
            return new ImageIcon(Game.snakeDownImage).getImage();
        } else if (this.gameObject == GameObject.S_R) {
            return new ImageIcon(Game.snakeRightImage).getImage();
        } else if (this.gameObject == GameObject.S_L) {
            return new ImageIcon(Game.snakeLeftImage).getImage();
        } else if (this.gameObject == GameObject.B) {
            return new ImageIcon(Game.snakeBodyImage).getImage();
        } else if (this.gameObject == GameObject.F) {
            return new ImageIcon(Game.foodImage).getImage();
        } else if (this.gameObject == GameObject.G) {
            return new ImageIcon(Game.gridImage).getImage();
        } else {
            return new ImageIcon(Game.gridImage).getImage();
        }
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Direction getPivotDir() {
        return pivotDir;
    }

    public void setPivotDir(Direction pivotDir) {
        this.pivotDir = pivotDir;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}