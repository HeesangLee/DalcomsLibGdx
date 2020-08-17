package dalcoms.lib.libgdx;

import com.badlogic.gdx.utils.Array;

import dalcoms.lib.libgdx.easingfunctions.EaseLinear;
import dalcoms.lib.libgdx.easingfunctions.IEasingFunction;

/**
 * Variation per time Class
 * It can be used to calculate variation of game objects per time.
 * For variation of movements, rotation, scale, alpha changes
 */

public class VariationPerTime implements Renderable {
    private String tag;
    private Array<VarTimePair> varTimeArray;
    private float curTime = 0f;
    private State curState = State.IDLE;
    private State preState = State.IDLE;
    private IEasingFunction easingFunction;
    private EventListener eventListener;
    private boolean finishedNow = false;
    private float finishedTime = 0f;

    private enum State {
        IDLE, RUNNING, PAUSED
    }


    /**
     * Create uninitialized VariationPerTime
     */
    public VariationPerTime() {
        varTimeArray = new Array<>();
    }

    /**
     * Create VariationPerTime with Variation, Time pair array
     */
    public VariationPerTime(Array<VarTimePair> varTimeArray) {
        setVarTimeArray(varTimeArray);
    }

    @Override
    public void render(float delta) {
        if (curState.equals(State.RUNNING)) {
            if (isOnStartVarEvent()) {
                if (this.eventListener != null) {
                    this.eventListener.onStart(curTime);
                }
            }
            checkVariationPerTime(curTime);
            setCurTime(curTime + delta);
        } else {
            if (isFinishedNow()) {
                if (this.eventListener != null) {
                    this.eventListener.onFinish(getFinishedTime());
                }
                setFinishedNow(false, 0f);
            }
        }

        setCurState(curState);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * If time of 1'st pair is not zero, insert time zero pair in index of 0
     */
    public VariationPerTime setVarTimeArray(
            Array<VarTimePair> varTimeArray) {
        this.varTimeArray = varTimeArray;
//        insertTimeZeroAtStart();
        return this;
    }

    private void insertTimeZeroAtStart() {
        if (varTimeArray.get(0).getTime() != 0f) {
            this.varTimeArray
                    .insert(0, new VarTimePair(varTimeArray.get(0).getVariation(), 0f));
        }
    }

    public Array<VarTimePair> getVarTimeArray() {
        return varTimeArray;
    }

    public void addVarTime(VarTimePair vtp1) {
        this.varTimeArray.add(vtp1);
    }

    public void addVarTime(VarTimePair vtp1, VarTimePair vtp2) {
        this.varTimeArray.add(vtp1, vtp2);
    }

    public VariationPerTime start() {
        return start(EaseLinear.getInstance());
    }

    public VariationPerTime start(IEasingFunction easingFunction) {
        setCurState(State.RUNNING);
        this.easingFunction = easingFunction;
        return this;
    }

    public void pause() {
        setCurState(State.PAUSED);
    }

    public void stop() {
        setCurState(State.IDLE);
    }

    public void stop(boolean initialState) {
        if (initialState) {
            setCurState(State.IDLE, 0f);
            varTimeArray = new Array<>();
        } else {
            setCurState(State.IDLE);
        }
    }

    public void setCurTime(float curTime) {
        this.curTime = curTime;
    }

    public VariationPerTime setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
        return this;
    }

    private void setCurState(State curState) {
        this.preState = this.curState;
        this.curState = curState;
    }

    private void setCurState(State curState, float curTime) {
        setCurState(curState);
        setCurTime(curTime);
    }


    /**
     * Check variation per time in render polling method when this is running.
     *
     * @param curTime current time from start
     */
    private void checkVariationPerTime(float curTime) {
//        float beginVal = 0f, changeVal = 0f, beginTime = 0f, totalTime = 0f;
        float beginVal, changeVal, beginTime, totalTime;

        if (curTime < varTimeArray.get(0).getTime()) {
            return;
        }

        for (int index = 1; index < varTimeArray.size; index++) {
            if (curTime < varTimeArray.get(index).getTime()) {
                beginVal = varTimeArray.get(index - 1).getVariation();
                changeVal = varTimeArray.get(index).getVariation() - beginVal;
                beginTime = varTimeArray.get(index - 1).getTime();
                totalTime =
                        varTimeArray.get(index).getTime() - varTimeArray.get(index - 1).getTime();

//                Gdx.app.log("DebTestScreen",
//                            "curTime=" + String.valueOf(curTime) +
//                            ",beginVal=" + String.valueOf(beginVal) +
//                            ",changeVal=" + String.valueOf(changeVal) +
//                            ",totalTime=" + String.valueOf(totalTime) +
//                            ",easeVal=" + String.valueOf(this.easingFunction
//                                                                 .getValue(curTime - beginTime,
//                                                                           beginVal, changeVal,
//                                                                           totalTime)));
                onUpdateVariation(curTime, curTime - beginTime, beginVal, changeVal, totalTime);
                return;
            }
        }

        if (isVarTimeOut(curTime)) {
            setCurState(State.IDLE, 0f);

            beginVal = varTimeArray.get(this.varTimeArray.size - 2).getVariation();
            changeVal = varTimeArray.get(this.varTimeArray.size - 1).getVariation() - beginVal;
            totalTime = varTimeArray.get(this.varTimeArray.size - 1).getTime() -
                        varTimeArray.get(this.varTimeArray.size - 2).getTime();
            onUpdateVariation(curTime, totalTime, beginVal, changeVal, totalTime);

//            if (this.eventListener != null) {
//                this.eventListener.onFinish(curTime);
//            }
            setFinishedNow(true, curTime);
        }
    }

    private boolean isOnStartVarEvent() {
        return this.preState == State.IDLE && this.curState == State.RUNNING;

    }

    private boolean isOnFinishVarEvent(float curTime) {
        return this.preState == State.RUNNING && this.curState == State.IDLE;
    }

    private boolean isVarTimeOut(float curTime) {
        return curTime >= this.varTimeArray.get(this.varTimeArray.size - 1).getTime();
    }

    /**
     * Update variation value using easingFunction equation
     *
     * @param curTime     current time from start
     * @param easeCurTime current time for easing function equation,
     *                    easing function always time is started from 0t, so convert it for calculation
     */
    private void onUpdateVariation(float curTime, float easeCurTime, float beginVal,
            float changeVal, float totalTime) {
        if (this.eventListener != null) {
            this.eventListener.onUpdate(curTime, this.easingFunction
                    .getValue(easeCurTime, beginVal, changeVal, totalTime));
        }
    }

    public boolean isFinishedNow() {
        return finishedNow;
    }

    public void setFinishedNow(boolean finishedNow, float finishedTime) {
        this.finishedNow = finishedNow;
        this.finishedTime = finishedTime;
    }

    private float getFinishedTime() {
        return this.finishedTime;
    }

    public interface EventListener {
        void onUpdate(float curTime, float curVar);

        void onStart(float curTime);

        void onFinish(float curTime);
    }
}
