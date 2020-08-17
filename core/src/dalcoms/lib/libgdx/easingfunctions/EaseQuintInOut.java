package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuintInOut implements IEasingFunction {
    private static EaseQuintInOut instance;

    public static EaseQuintInOut getInstance() {
        if (instance == null) {
            synchronized (EaseQuintInOut.class) {
                if (instance == null) {
                    instance = new EaseQuintInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t * t * t * t + b;
        return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
    }
}