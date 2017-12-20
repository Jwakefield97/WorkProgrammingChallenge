
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

    protected static Timer timer;
    private final int DELAY = 80; //time between canvas renderings. 
    private Grid grid;

    public Board(Grid g) {
        this.grid = g;
        setPreferredSize(new Dimension(Game.windowWidth, Game.windowHeight));
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Tile[][] tiles = grid.getTiles();
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                Tile t = tiles[row][col];
                if (t.getImage() != null) {
                    g2d.drawImage(t.getImage(), t.getX(), t.getY(), grid.getTileSize(), grid.getTileSize(), this);
                }
            }
        }
        if (!timer.isRunning()) {
            //ii is the game over image object 
            ImageIcon ii = new ImageIcon(Game.gameOverImage);
        }
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void resetGame(Grid grid) {
        this.grid = grid;
        timer.start();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void actionPerformed(ActionEvent e) {
        //update tiles 
        grid.updateTiles();
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (timer.isRunning()) {
                grid.keyReleased(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (timer.isRunning()) {
                grid.keyPressed(e);
            }
        }
    }
}