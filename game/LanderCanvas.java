package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import shapes.Lander;
import shapes.NightSky;
import shapes.Terrain;

@SuppressWarnings("serial")
public class LanderCanvas extends JPanel {

    private NightSky nightSky;
    private Terrain terrain;
    private Lander lander;

    public LanderCanvas(NightSky nightSky, Terrain terrain, Lander lander) {
        this.nightSky = nightSky;
        this.terrain = terrain;
        this.lander = lander;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {

        // background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // night sky
        nightSky.draw(g);
        
        // the land
        terrain.draw(g);

        // the lander
        lander.draw(g);
        
        // fuel
        g.setColor(Color.lightGray);
        g.drawString("FUEL: ", 440, 22);
        if (lander.getFuel() != 0) {
            g.fillRect(480, 10, lander.getFuel(), 15);            
        } else {
            g.drawString("EMPTY!", 480, 22);
        }
        

    }

    public Lander getLander() {
        return lander;
    }

    public void resetLander() {
        lander = new Lander();
    }

    public boolean isCollided() {

        for (int i = 0; i < lander.xpoints.length; i++) {
            if (terrain.contains(lander.xpoints[i], lander.ypoints[i])) {
                return true;
            }
        }
        for (int i = 0; i < terrain.xpoints.length; i++) {
            if (lander.contains(terrain.xpoints[i], terrain.ypoints[i])) {
                return true;
            }
        }
        // need to randomly detect points inside polygon
        return false;
    }
}
