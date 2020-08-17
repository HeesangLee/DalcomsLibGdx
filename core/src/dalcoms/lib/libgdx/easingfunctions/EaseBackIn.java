package dalcoms.lib.libgdx.easingfunctions;

public class EaseBackIn implements IEasingFunction {
    private static EaseBackIn instance;

    public static EaseBackIn getInstance() {
        if (instance == null) {
            synchronized (EaseBackIn.class) {
                if (instance == null) {
                    instance = new EaseBackIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        float s = 1.70158f;
        return c * (t /= d) * t * ((s + 1) * t - s) + b;
    }
}