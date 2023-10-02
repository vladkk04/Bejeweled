package sk.tuke.gamestudio.game;

import java.util.TimerTask;

public class BewejeledTimer extends java.util.Timer
{
    private int timeRemaining;

    public BewejeledTimer(int startTime)
    {
        this.timeRemaining = startTime;
        timer();
    }

    public int getTimeRemaining()
    {
        return timeRemaining;
    }

    private void timer()
    {
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run()
            {
                if (timeRemaining > 0)
                {
                    timeRemaining--;
                }
            }
        }, 0, 1000);
    }


}
