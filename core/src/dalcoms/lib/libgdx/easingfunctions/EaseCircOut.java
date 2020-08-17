package dalcoms.lib.libgdx.easingfunctions;

public class EaseCircOut implements IEasingFunction {
    private static EaseCircOut instance;

    public static EaseCircOut getInstance() {
        if (instance == null) {
            synchronized (EaseCircOut.class) {
                if (instance == null) {
                    instance = new EaseCircOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * (float) Math.sqrt(1 - (t = t / d - 1) * t) + b;
    }
}