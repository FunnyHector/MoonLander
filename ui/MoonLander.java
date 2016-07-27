package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import game.LanderCanvas;
import game.Timer;
import shapes.Lander;
import shapes.NightSky;
import shapes.Terrain;

@SuppressWarnings("serial")
public class MoonLander extends JFrame {

    public static final Dimension DIMENTION = new Dimension(600, 600);
    private LanderCanvas canvas;
    private Timer timer;
    private boolean gameRunning;

    public MoonLander() {
        super("Moon Lander");
        // initialise the timer thread
        timer = new Timer(this);
        setGameStart();
        timer.start();
        initialise();
    }

    private void initialise() {
        // create canvas
        canvas = new LanderCanvas(new NightSky(120), new Terrain(), new Lander());

        gameRunning = true;
        setGameStart();
        // ==============KeyListener===============
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (gameRunning) {
                    int code = e.getKeyCode();
                    switch (code) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_KP_RIGHT:
                    case KeyEvent.VK_D:
                        canvas.getLander().moveRight();
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_KP_LEFT:
                    case KeyEvent.VK_A:
                        canvas.getLander().moveLeft();
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_KP_UP:
                    case KeyEvent.VK_W:
                        canvas.getLander().moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_KP_DOWN:
                    case KeyEvent.VK_S:
                        canvas.getLander().moveDown();
                        break;
                    default:
                    }
                    canvas.repaint();
                    testCollision();
                }
            }
        });

        // ==============Swing stuff===============
        // layout
        setLayout(new BorderLayout());
        // add canvas
        add(canvas, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // pack components tightly together
        pack();
        // prevent us from being resizeable
        setResizable(false);
        // make sure we are visible
        setVisible(true);
    }

    public void reset() {
        setGameStart();
        canvas.resetLander();

    }

    public LanderCanvas getCanvas() {
        return canvas;
    }

    @Override
    public Dimension getPreferredSize() {
        return DIMENTION;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameStop() {
        gameRunning = false;
    }

    public void setGameStart() {
        gameRunning = true;
    }

    public void clockTick() {
        canvas.getLander().gravity();
        canvas.repaint();
        testCollision();
    }

    public void testCollision() {
        if (canvas.isCollided()) {
            
            // WIN!
            if (canvas.getLander().getX() > Terrain.SAFE_LANDING_LEFT
                    && canvas.getLander().getX() < Terrain.SAFE_LANDING_RIGHT - Lander.LANDER_WIDTH) {
                
                setGameStop();
                int r = JOptionPane.showConfirmDialog(this,
                        new JLabel("Congrat! Your ship landed safely! do you want to play again?"),
                        "WIN!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (r == JOptionPane.YES_OPTION) {
                    reset();
                    // timer.start();
                } else if (r == JOptionPane.NO_OPTION) {
                    // timer.setGameOver();
                }
 
            } else {
                // Lose!
                setGameStop();
                int r = JOptionPane.showConfirmDialog(this,
                        new JLabel("Your ship crashed, do you want to play again?"),
                        "Warning!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (r == JOptionPane.YES_OPTION) {
                    reset();
                    // timer.start();
                } else if (r == JOptionPane.NO_OPTION) {
                    // timer.setGameOver();
                }
                
            }
        }
    }

    public static void main(String[] args) {
        new MoonLander();
    }
}