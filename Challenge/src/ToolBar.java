import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
    private JLabel scoreLabel;
    private JButton restartButton;

    public ToolBar(final Game game, int score) {
        //grid layout = 1 row, 3 columns
        setLayout(new BorderLayout(1, 3));

        //Add drop down menu for background color
        add(generateDropDownMenu(game), BorderLayout.EAST);

        //Score counter
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Times New Roman", 1, 25));
        add(scoreLabel, BorderLayout.CENTER);

        //reset button
        restartButton = new JButton("Restart");
        restartButton.setMnemonic(KeyEvent.VK_T);
        restartButton.setFont(new Font("Times New Roman", 1, 25));
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.resetGame();
            }
        });
        add(restartButton, BorderLayout.WEST);
    }

    public void updateScore(int score) {
        //TODO: Implement code to update score.
    }

    //Method used to generate the background color drop down menu
    public JMenuBar generateDropDownMenu(final Game game) {
        JMenuBar bgColorDropDown = new JMenuBar();
        JMenu menu = new JMenu("Background Color");
        menu.setFont(new Font("Times New Roman", 1, 25));
        menu.setMnemonic(KeyEvent.VK_A);

        //Blue button
        JMenuItem blue = new JMenuItem("Blue", KeyEvent.VK_B);
        blue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Blue");
            }
        });
        menu.add(blue);

        //Black button
        JMenuItem black = new JMenuItem("Black", KeyEvent.VK_N);
        black.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Black");
            }
        });
        menu.add(black);

        //White button
        JMenuItem white = new JMenuItem("White", KeyEvent.VK_N);
        white.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("White");
            }
        });
        menu.add(white);

        //Magenta button
        JMenuItem magenta = new JMenuItem("Magenta", KeyEvent.VK_N);
        magenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Magenta");
            }
        });
        menu.add(magenta);

        //Cyan button
        JMenuItem cyan = new JMenuItem("Cyan", KeyEvent.VK_N);
        cyan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cyan");
            }
        });
        menu.add(cyan);

        //Gray button
        JMenuItem gray = new JMenuItem("Gray", KeyEvent.VK_N);
        gray.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Gray");
            }
        });
        menu.add(gray);

        //Green button
        JMenuItem green = new JMenuItem("Green", KeyEvent.VK_N);
        green.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Green");
            }
        });
        menu.add(green);

        //Orange button
        JMenuItem orange = new JMenuItem("Orange", KeyEvent.VK_N);
        orange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Orange");
            }
        });
        menu.add(orange);

        //Yellow button
        JMenuItem yellow = new JMenuItem("Yellow", KeyEvent.VK_N);
        yellow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yellow");
            }
        });
        menu.add(yellow);

        //Red button
        JMenuItem red = new JMenuItem("Red", KeyEvent.VK_N);
        red.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Red");
            }
        });
        menu.add(red);

        bgColorDropDown.add(menu);

        return bgColorDropDown;
    }

}