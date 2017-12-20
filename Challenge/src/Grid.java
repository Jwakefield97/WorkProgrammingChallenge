import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private Tile[][] tiles;
    private int tileSize = 40;
    private int currentX = (Game.windowWidth / tileSize) / 2;
    private int currentY = (Game.windowHeight / tileSize) / 2;
    private int currentDx = 0;
    private int currentDy = 0;
    private Tile snakeTile;
    private Tile foodTile;
    private ArrayList<Tile> bodyTiles;
    private int score = 0;
    private Game game;

    public Grid(Game game) {
        this.game = game;
        bodyTiles = new ArrayList<Tile>();
        tiles = new Tile[Game.windowHeight / tileSize][Game.windowWidth / tileSize];
        //creating new tiles 
        int newY = 0;
        for (int row = 0; row < tiles.length; row++) {
            int newX = 0;
            for (int col = 0; col < tiles[row].length; col++) {
                if (tiles[row][col] == null) {
                    tiles[row][col] = new Tile(GameObject.G, newX, newY);
                }

                newX += tileSize;
            }
            newY += tileSize;
        }
        foodTile = new Tile();
        generateFoodPosition();
        tiles[foodTile.getY()][foodTile.getX()].setGameObject(GameObject.F);
        tiles[currentY][currentX].setPivotDir(Direction.U);
        setSnakeTile(tiles[currentY][currentX]);
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int ts) {
        this.tileSize = ts;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {

        this.tiles = tiles;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            currentDx = -1;
            currentDy = 0;
            tiles[currentY][currentX].setGameObject(GameObject.S_L);
            tiles[currentY][currentX].setPivotDir(Direction.L);
        } else if (key == KeyEvent.VK_RIGHT) {
            currentDx = 1;
            currentDy = 0;
            tiles[currentY][currentX].setGameObject(GameObject.S_R);
            tiles[currentY][currentX].setPivotDir(Direction.R);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {

        }
        if (key == KeyEvent.VK_RIGHT) {

        }
        if (key == KeyEvent.VK_UP) {

        }
        if (key == KeyEvent.VK_DOWN) {

        }
    }

    public boolean updateTiles() {
        currentX += currentDx;
        currentY += currentDy;

        //if the snake has collided with the border 
        if (currentX == -1 || currentX == tiles[0].length || currentY == -1 || currentY == tiles.length) {
            return false;
        }
        //do all collision detection before clearing the grid. 
        if (tiles[currentY][currentX].getGameObject() == GameObject.F) {
            generateFoodPosition();
            addBody();
        }

        //if snake collides with body part
        if (tiles[currentY][currentX].getGameObject() == GameObject.B) {
            return false;
        }
        //update pivot direction
        for (Tile t : bodyTiles) {
            if (tiles[t.getY()][t.getX()].getPivotDir() == Direction.U) {
                t.setPivotDir(Direction.U);
            } else if (tiles[t.getY()][t.getX()].getPivotDir() == Direction.D) {
                t.setPivotDir(Direction.D);
            } else if (tiles[t.getY()][t.getX()].getPivotDir() == Direction.L) {
                t.setPivotDir(Direction.L);
            } else if (tiles[t.getY()][t.getX()].getPivotDir() == Direction.R) {
                t.setPivotDir(Direction.R);
            }
        }

        clearGrid();

        if (bodyTiles.size() > 0) {
            for (Tile t : bodyTiles) {
                tiles[t.getY()][t.getX()].setGameObject(GameObject.B);
            }
        }

        tiles[foodTile.getY()][foodTile.getX()].setGameObject(GameObject.F);

        //update snake head location
        if (currentDx == 1 && currentDy == 0) {
            tiles[currentY][currentX].setGameObject(GameObject.S_R);
            tiles[currentY][currentX].setPivotDir(Direction.R);
        } else if (currentDx == -1 && currentDy == 0) {
            tiles[currentY][currentX].setGameObject(GameObject.S_L);
            tiles[currentY][currentX].setPivotDir(Direction.L);
        } else if (currentDy == 1 && currentDx == 0) {
            tiles[currentY][currentX].setGameObject(GameObject.S_D);
            tiles[currentY][currentX].setPivotDir(Direction.D);
        } else if (currentDy == -1 && currentDx == 0) {
            tiles[currentY][currentX].setPivotDir(Direction.U);
            tiles[currentY][currentX].setGameObject(GameObject.S_U);
        } else {
            tiles[currentY][currentX].setPivotDir(Direction.U);
            tiles[currentY][currentX].setGameObject(GameObject.S_U);
        }
        //update each body part's location
        for (Tile t : bodyTiles) {
            if (t.getPivotDir() == Direction.U) {
                t.setX(t.getX());
                t.setY(t.getY() - 1);
            } else if (t.getPivotDir() == Direction.D) {
                t.setX(t.getX());
                t.setY(t.getY() + 1);
            } else if (t.getPivotDir() == Direction.L) {
                t.setX(t.getX() - 1);
                t.setY(t.getY());
            } else if (t.getPivotDir() == Direction.R) {
                t.setX(t.getX() + 1);
                t.setY(t.getY());
            }
        }
        return true;
    }

    public void addBody() {
        score += 1;
        game.updateScore(score);
        Tile body = new Tile();
        body.setGameObject(GameObject.B);
        if (bodyTiles.size() == 0) {
            body.setPivotDir(tiles[currentY][currentX].getPivotDir());
            body.setX(getCurrentX());
            body.setY(getCurrentY());
            bodyTiles.add(body);
        } else {
            Tile last = bodyTiles.get(bodyTiles.size() - 1);
            if (last.getPivotDir() == Direction.U) {
                body.setX(last.getX() + 1);
                body.setY(last.getY());
            } else if (last.getPivotDir() == Direction.D) {
                body.setX(last.getX() - 1);
                body.setY(last.getY());
            } else if (last.getPivotDir() == Direction.L) {
                body.setX(last.getX());
                body.setY(last.getY() + 1);
            } else if (last.getPivotDir() == Direction.R) {
                body.setX(last.getX());
                body.setY(last.getY() - 1);
            }

            body.setPivotDir(last.getPivotDir());
            bodyTiles.add(body);
        }
    }

    public void clearGrid() {
        //reset the images on the grid. 
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                tiles[row][col].setGameObject(GameObject.G);
            }
        }
    }

    private int getCurrentY() {
        return currentY;
    }

    private int getCurrentX() {
        return currentX;
    }

    public void generateFoodPosition() {
        ArrayList<Tile> foodTiles = new ArrayList<Tile>();
        Random rd = new Random();

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                //make sure the food doesn't spawn on another game object
                if (tiles[row][col].getGameObject() == GameObject.G) {
                    foodTiles.add(new Tile(GameObject.F, col, row));
                }
            }
        }
        setFoodTile(foodTiles.get(rd.nextInt(foodTiles.size())));
    }

    public ArrayList<Tile> getBodyTiles() {
        return bodyTiles;
    }

    public void setBodyTiles(ArrayList<Tile> bodyTiles) {
        this.bodyTiles = bodyTiles;
    }

    public Tile getFoodTile() {
        return foodTile;
    }

    public void setFoodTile(Tile foodTile) {
        this.foodTile = foodTile;
    }

    public Tile getSnakeTile() {
        return snakeTile;
    }

    public void setSnakeTile(Tile snakeTile) {
        this.snakeTile = snakeTile;
    }

    public int getCurrentDy() {
        return currentDy;
    }

    public void setCurrentDy(int currentDy) {
        this.currentDy = currentDy;
    }

    public int getCurrentDx() {
        return currentDx;
    }

    public void setCurrentDx(int currentDx) {
        this.currentDx = currentDx;
    }

}