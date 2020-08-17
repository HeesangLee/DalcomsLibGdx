package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuartInOut implements IEasingFunction {
    private static EaseQuartInOut instance;

    public static EaseQuartInOut getInstance() {
        if (instance == null) {
            synchronized (EaseQuartInOut.class) {
                if (instance == null) {
                    instance = new EaseQuartInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t * t * t + b;
        return -c / 2 * ((t -= 2) * t * t * t - 2) + b;
    }
}