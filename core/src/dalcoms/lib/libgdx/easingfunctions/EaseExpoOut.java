package dalcoms.lib.libgdx.easingfunctions;

public class EaseExpoOut implements IEasingFunction {
    private static EaseExpoOut instance;

    public static EaseExpoOut getInstance() {
        if (instance == null) {
            synchronized (EaseExpoOut.class) {
                if (instance == null) {
                    instance = new EaseExpoOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return (t == d) ? b + c : c * (-(float) Math.pow(2, -10 * t / d) + 1) + b;
    }
}