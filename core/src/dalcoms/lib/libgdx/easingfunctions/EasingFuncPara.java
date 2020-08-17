package dalcoms.lib.libgdx.easingfunctions;

public class EasingFuncPara {
    /**
     * @param curTime   is the current time (or position) of the tween.
     * @param beginVal  is the beginning value of the property.
     * @param changeVal is the change between the beginning and destination value of the property.
     * @param totalTime is the total time of the tween.
     */
    float curTime, beginVal, changeVal, totalTime;

    public EasingFuncPara() {

    }

    public EasingFuncPara(float curTime, float beginVal, float changeVal, float totalTime) {
        setCurTime(curTime);
        setBeginVal(beginVal);
        setChangeVal(changeVal);
        setTotalTime(totalTime);
    }

    public float getCurTime() {
        return curTime;
    }

    public void setCurTime(float curTime) {
        this.curTime = curTime;
    }

    public float getBeginVal() {
        return beginVal;
    }

    public void setBeginVal(float beginVal) {
        this.beginVal = beginVal;
    }

    public float getChangeVal() {
        return changeVal;
    }

    public void setChangeVal(float changeVal) {
        this.changeVal = changeVal;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }
}
