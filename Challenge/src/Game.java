import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {
    protected static int windowHeight = 920;
    protected static int windowWidth = 920;
    private Grid grid = new Grid(this);
    private Board board = new Board(grid);
    private ToolBar toolBar = new ToolBar(this, 0);
    //Load the URL's of the images 
    protected static URL gridImage = Game.class.getResource("/assets/grid.png");
    protected static URL foodImage = Game.class.getResource("/assets/snakeFood.png");
    protected static URL snakeLeftImage = Game.class.getResource("/assets/snakeLeft.png");
    protected static URL snakeRightImage = Game.class.getResource("/assets/snakeRight.png");
    protected static URL snakeUpImage = Game.class.getResource("/assets/snakeUp.png");
    protected static URL snakeDownImage = Game.class.getResource("/assets/snakeDown.png");
    protected static URL snakeBodyImage = Game.class.getResource("/assets/snakeBody.png");
    protected static URL gameOverImage = Game.class.getResource("/assets/gameOver.png");

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Game() {
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(windowWidth, windowHeight);
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(toolBar, BorderLayout.NORTH);
        add(board, BorderLayout.SOUTH);
        pack();
        board.requestFocus();
    }

    public void updateScore(int newScore) {
        toolBar.invalidate();
        toolBar.updateScore(newScore);
        revalidate();
    }

    //gets called when the restart button is clicked. 
    public void resetGame() {
        toolBar.updateScore(0);
        grid = new Grid(this);
        board.resetGame(grid);
        repaint();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }

}