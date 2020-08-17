package dalcoms.lib.libgdx.easingfunctions;

public class EaseBackInOut implements IEasingFunction {
    private static EaseBackInOut instance;

    public static EaseBackInOut getInstance() {
        if (instance == null) {
            synchronized (EaseBackInOut.class) {
                if (instance == null) {
                    instance = new EaseBackInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        float s = 1.70158f;
        if ((t /= d / 2) < 1) return c / 2 * (t * t * (((s *= (1.525f)) + 1) * t - s)) + b;
        return c / 2 * ((t -= 2) * t * (((s *= (1.525f)) + 1) * t + s) + 2) + b;
    }
}