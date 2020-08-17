package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuintOut implements IEasingFunction {
    private static EaseQuintOut instance;

    public static EaseQuintOut getInstance() {
        if (instance == null) {
            synchronized (EaseQuintOut.class) {
                if (instance == null) {
                    instance = new EaseQuintOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
    }
}