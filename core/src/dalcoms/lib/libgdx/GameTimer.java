package dalcoms.lib.libgdx;

import com.badlogic.gdx.Gdx;

public class GameTimer implements Renderable {
    private String tag;
    private float curTimeSec = 0f;
    private final float TIME_250MSEC = 0.25f;
    private final float TIME_500MSEC = 0.5f;
    private final float TIME_1SEC = 1f;

    private float time250msec = 0f;
    private float time500msec = 0f;
    private float time1sec = 0f;

    private int cntTime250msec = 0;
    private int cntTime500msec = 0;
    private int cntTime1sec = 0;

    private State timerState = State.PAUSED;
    private EventListener eventListener;


    private enum State {
        RUNNING, PAUSED
    }

    public GameTimer() {

    }

    @Override
    public void render(float delta) {
        if (getTimerState() == State.RUNNING) {
            curTimeSec += delta;
            checkTimer(delta);
        }
    }

    private void checkTimer(float delta) {
        check250msecTimer(delta);
        check500msecTimer(delta);
        check1secTimer(delta);
    }

    private void check250msecTimer(float delta) {
        time250msec += delta;
        if (time250msec >= TIME_250MSEC) {
            setCntTime250msec(getCntTime250msec() + 1);
            time250msec = 0f;

            if (this.eventListener != null) {
                this.eventListener.onTimer250msec(getCurTimeSec(), getCntTime250msec());
            }
        }
    }

    private void check500msecTimer(float delta) {
        time500msec += delta;
        if (time500msec >= TIME_500MSEC) {
            setCntTime500msec(getCntTime500msec() + 1);
            time500msec = 0f;

            if (this.eventListener != null) {
                this.eventListener.onTimer500msec(getCurTimeSec(), getCntTime500msec());
            }
        }
    }

    private void check1secTimer(float delta) {
        time1sec += delta;
        Gdx.app.log("DebTestScreen", "curTime" + time1sec);
        if (time1sec >= TIME_1SEC) {
            setCntTime1sec(getCntTime1sec() + 1);
            time1sec = 0f;

            if (this.eventListener != null) {
                this.eventListener.onTimer1sec(getCurTimeSec(), getCntTime1sec());
            }
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameTimer start() {
        setTimerState(State.RUNNING);
        return this;
    }

    public GameTimer start(boolean clrCurTime) {
        if (clrCurTime) {
            curTimeSec = 0f;
        }
        return start();
    }

    public void pause() {
        setTimerState(State.PAUSED);
    }

    public void pause(boolean clrCurTime) {
        if (clrCurTime) {
            curTimeSec = 0f;
        }
        pause();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getCurTimeSec() {
        return curTimeSec;
    }

    private void setTimerState(State timerState) {
        this.timerState = timerState;
    }

    public State getTimerState() {
        return timerState;
    }

    public int getCntTime250msec() {
        return cntTime250msec;
    }

    private void setCntTime250msec(int cntTime250msec) {
        this.cntTime250msec = cntTime250msec;
    }

    public int getCntTime500msec() {
        return cntTime500msec;
    }

    private void setCntTime500msec(int cntTime500msec) {
        this.cntTime500msec = cntTime500msec;
    }

    public int getCntTime1sec() {
        return cntTime1sec;
    }

    private void setCntTime1sec(int cntTime1sec) {
        this.cntTime1sec = cntTime1sec;
    }

    public interface EventListener {
        void onTimer1sec(float curTimeSec, int timeCount);

        void onTimer500msec(float curTimeSec, int timeCount);

        void onTimer250msec(float curTimeSec, int timeCount);
    }
}
