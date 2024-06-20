/* a class for the timer */
public class Timer {

    long systemStartTime;
    int curTimeSeconds;
    boolean pause;

    /* creates and starts the timer */
    public Timer () {
        systemStartTime = System.currentTimeMillis();
        pause = false;
    }
    /* returns the current time of the timer in seconds */
    public int getCurTime () {
        if(pause)
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

}