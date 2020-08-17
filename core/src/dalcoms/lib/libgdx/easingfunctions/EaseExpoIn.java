package dalcoms.lib.libgdx.easingfunctions;

public class EaseExpoIn implements IEasingFunction {
    private static EaseExpoIn instance;

    public static EaseExpoIn getInstance() {
        if (instance == null) {
            synchronized (EaseExpoIn.class) {
                if (instance == null) {
                    instance = new EaseExpoIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return (t == 0) ? b : c * (float) Math.pow(2, 10 * (t / d - 1)) + b;
    }
}