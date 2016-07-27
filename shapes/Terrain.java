package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

@SuppressWarnings("serial")
public class Terrain extends Polygon {

    private static final int[] TERRAIN_X = { 0, 30, 40, 100, 140, 160, 180, 200, 220, 230,
            300, 310, 330, 350, 360, 400, 410, 435, 460, 465, 500, 545, 560, 575, 580,
            600, 600, 0 };
    private static final int[] TERRAIN_Y = { 500, 450, 480, 510, 350, 400, 395, 482, 492,
            482, 482, 520, 515, 520, 515, 550, 400, 350, 360, 400, 410, 480, 455, 465,
            480, 500, 600, 600 };
    
    public static final int SAFE_LANDING_LEFT = 230;
    public static final int SAFE_LANDING_RIGHT = 300;

    public Terrain() {
        super(TERRAIN_X, TERRAIN_Y, TERRAIN_X.length);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillPolygon(this);
    }

}
