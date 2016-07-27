package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import ui.MoonLander;

@SuppressWarnings("serial")
public class Lander extends Polygon {

    private static final int[] LANDER_SHAPEX = { 11, 13, 27, 29, 30, 26, 37, 40, 40, 30,
            30, 33, 24, 21, 24, 16, 19, 16, 7, 0, 0, 10, 10, 3, 14, 10 };
    private static final int[] LANDER_SHAPEY = { 5, 0, 0, 5, 20, 20, 35, 35, 40, 40, 35,
            35, 20, 20, 25, 25, 20, 20, 35, 35, 40, 40, 35, 35, 20, 20 };
    
    public static final int LANDER_WIDTH = 40;

    private static final int THRUST_POWER_X = 12;
    private static final int THRUST_POWER_Y = 1;
    private static final float GRAVITY = 0.2f;
    private static final int TERMINAL_VELOCITY = 10;

    private int x;
    private float descendingSpeed;

    private int fuel;
    private boolean fuelIsEmpty;

    public Lander() {
        super(LANDER_SHAPEX, LANDER_SHAPEY, LANDER_SHAPEX.length);
        descendingSpeed = 0f;
        x = 0;
        fuel = 100;
        fuelIsEmpty = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillPolygon(this);
    }

    public void moveRight() {
        if (!fuelIsEmpty) {
            useFuel();
            this.translate(THRUST_POWER_X, 0);
            x += THRUST_POWER_X;
            if (x > MoonLander.DIMENTION.getWidth()) {
                this.translate(-(int) MoonLander.DIMENTION.getWidth(), 0);
                x -= MoonLander.DIMENTION.getWidth();
            }
        }
    }

    public void moveLeft() {
        if (!fuelIsEmpty) {
            useFuel();
            this.translate(-THRUST_POWER_X, 0);
            x -= THRUST_POWER_X;
            if (x < -40) {
                this.translate((int) MoonLander.DIMENTION.getWidth(), 0);
                x += MoonLander.DIMENTION.getWidth();
            }
        }
    }

    public void moveUp() {
        if (!fuelIsEmpty) {
            useFuel();
            if (descendingSpeed > -5) {
                descendingSpeed -= THRUST_POWER_Y;
            }
            this.translate(0, (int) descendingSpeed);
        }
    }

    public void moveDown() {
        if (!fuelIsEmpty) {
            useFuel();
            if (descendingSpeed < TERMINAL_VELOCITY) {
                descendingSpeed += THRUST_POWER_Y;
            }
            this.translate(0, (int) descendingSpeed);
        }
        
    }

    public void gravity() {
        if (descendingSpeed < TERMINAL_VELOCITY) {
            descendingSpeed += GRAVITY;
        }

        this.translate(0, (int) descendingSpeed);
    }

    public int getFuel() {
        return fuel;
    }

    public void useFuel() {
        fuel--;
        if (fuel <= 0) {
            fuelIsEmpty = true;
        }
    }
    
    public int getX() {
        return x;
    }
}
