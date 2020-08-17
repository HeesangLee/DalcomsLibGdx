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
            if (this.eventListener != null) {
                this.eventListener.onTimer250msec(getCurTimeSec());
            }
            time250msec = 0f;
        }
    }

    private void check500msecTimer(float delta) {
        time500msec += delta;
        if (time500msec >= TIME_500MSEC) {
            if (this.eventListener != null) {
                this.eventListener.onTimer500msec(getCurTimeSec());
            }
            time500msec = 0f;
        }
    }

    private void check1secTimer(float delta) {
        time1sec += delta;
        Gdx.app.log("DebTestScreen", "curTime" + time1sec);
        if (time1sec >= TIME_1SEC) {
            if (this.eventListener != null) {
                this.eventListener.onTimer1sec(getCurTimeSec());
            }
            time1sec = 0f;
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

    public interface EventListener {
        void onTimer1sec(float curTimeSec);

        void onTimer500msec(float curTimeSec);

        void onTimer250msec(float curTimeSec);
    }
}
