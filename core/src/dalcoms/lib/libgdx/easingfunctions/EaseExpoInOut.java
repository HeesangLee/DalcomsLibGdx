package dalcoms.lib.libgdx.easingfunctions;

public class EaseExpoInOut implements IEasingFunction {
    private static EaseExpoInOut instance;

    public static EaseExpoInOut getInstance() {
        if (instance == null) {
            synchronized (EaseExpoInOut.class) {
                if (instance == null) {
                    instance = new EaseExpoInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if (t == 0) return b;
        if (t == d) return b + c;
        if ((t /= d / 2) < 1) return c / 2 * (float) Math.pow(2, 10 * (t - 1)) + b;
        return c / 2 * (-(float) Math.pow(2, -10 * --t) + 2) + b;
    }
}