package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NightSky {

    private List<Star> stars;
    private Random random = new Random();

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private static final int MAX_STAR_WIDTH = 8;
    private static final int MAX_STAR_HEIGHT = 8;

    public NightSky(int n) {
        stars = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            int x = random.nextInt(CANVAS_WIDTH);
            int y = random.nextInt(CANVAS_HEIGHT);
            int width = random.nextInt(MAX_STAR_WIDTH);
            int height = random.nextInt(MAX_STAR_HEIGHT);
            stars.add(new Star(x, y, width, height));
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        for (Star s : stars) {
            
            int type = random.nextInt(9);
            switch (type) {
            case 0:
                g.fillOval(s.x, s.y, s.width, s.height);
                break;
            case 1:
                g.drawOval(s.x, s.y, s.width, s.height);
                break;
            case 2:
                g.fillRect(s.x, s.y, s.width, s.height);
                break;
            case 3:
                g.drawRect(s.x, s.y, s.width, s.height);
                break;
            case 4:
                g.draw3DRect(s.x, s.y, s.width, s.height, true);
                break;
            case 5:
                g.draw3DRect(s.x, s.y, s.width, s.height, false);
                break;
            case 6:
                g.drawArc(s.x, s.y, s.width, s.height, random.nextInt(360),
                        random.nextInt(360));
                break;
            case 7:
                g.fillArc(s.x, s.y, s.width, s.height, random.nextInt(360),
                        random.nextInt(360));
                break;
            case 8:
                g.drawLine(s.x, s.y, s.x + random.nextInt(5) - random.nextInt(10),
                        s.y + random.nextInt(5) - random.nextInt(10));
                break;
            default:
            }
            s.blink();
        }
    }

    private class Star {
        int x;
        int y;
        int width;
        int height;
    
        public Star(int x, int y, int width, int height) {
            super();
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    
        public void blink() {
            this.width = random.nextInt(Math.abs(width) + 1) + 1;
            this.height = random.nextInt(Math.abs(height) + 1) + 1;
        }
    }
}
