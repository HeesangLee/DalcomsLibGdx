package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuadInOut implements IEasingFunction {
    private static EaseQuadInOut instance;

    public static EaseQuadInOut getInstance() {
        if (instance == null) {
            synchronized (EaseQuadInOut.class) {
                if (instance == null) {
                    instance = new EaseQuadInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t + b;
        return -c / 2 * ((--t) * (t - 2) - 1) + b;
    }
}