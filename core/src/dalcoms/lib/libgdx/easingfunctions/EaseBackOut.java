package dalcoms.lib.libgdx.easingfunctions;

public class EaseBackOut implements IEasingFunction {
    private static EaseBackOut instance;

    public static EaseBackOut getInstance() {
        if (instance == null) {
            synchronized (EaseBackOut.class) {
                if (instance == null) {
                    instance = new EaseBackOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        float s = 1.70158f;
        return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
    }
}