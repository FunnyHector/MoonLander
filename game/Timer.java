package game;

import ui.MoonLander;

public class Timer extends Thread {

    private MoonLander game;

    public Timer(MoonLander game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (game.isGameRunning()) {
            try {
                Thread.sleep(100);
                game.clockTick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
