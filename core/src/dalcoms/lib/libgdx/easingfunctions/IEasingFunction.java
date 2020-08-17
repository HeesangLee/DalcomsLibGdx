package dalcoms.lib.libgdx.easingfunctions;

/**
 * Interface of Easing functions class specify the rate of change of a parameter over time
 *
 * @reference source : https://easings.net/
 */
public interface IEasingFunction {
    /**
     * @param curTime   is the current time (or position) of the tween.
     * @param beginVal  is the beginning value of the property.
     * @param changeVal is the change between the beginning and destination value of the property.
     * @param totalTime is the total time of the tween.
     */
    public float getValue(float curTime, float beginVal, float changeVal, float totalTime);

}