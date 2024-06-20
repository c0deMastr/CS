/* a class for the timer */
public class Clock {

    long systemStartTime;
    int curTimeSeconds;
    boolean started;
    boolean pause;

    /* creates and starts the timer */
    public Clock () {
        systemStartTime = System.currentTimeMillis();
        curTimeSeconds = 0;
        started = false;
        pause = false;
    }
    /* returns the current time of the timer in seconds */
    public int getCurTime () {
        if (!started || pause)
            return curTimeSeconds;

            long systemCurrentTime = System.currentTimeMillis() - systemStartTime;
            curTimeSeconds = ((int)systemCurrentTime) / 1000 ;
            return curTimeSeconds;    
    }
    /* pauses the timer */
    public void pause(){
        pause = true;
    }
     /* unpauses the timer */
    public void unpause(){
        pause = false;
    }

    public void start(){
        started = true;
    }

    public void end () {
        started = false;
    }

    public boolean running (){
        return started;
    }
}
