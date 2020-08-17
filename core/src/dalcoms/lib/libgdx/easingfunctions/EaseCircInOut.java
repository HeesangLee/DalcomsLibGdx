package dalcoms.lib.libgdx.easingfunctions;

public class EaseCircInOut implements IEasingFunction {
    private static EaseCircInOut instance;

    public static EaseCircInOut getInstance() {
        if (instance == null) {
            synchronized (EaseCircInOut.class) {
                if (instance == null) {
                    instance = new EaseCircInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return -c / 2 * ((float) Math.sqrt(1 - t * t) - 1) + b;
        return c / 2 * ((float) Math.sqrt(1 - (t -= 2) * t) + 1) + b;
    }
}