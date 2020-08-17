package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuintIn implements IEasingFunction {
    private static EaseQuintIn instance;

    public static EaseQuintIn getInstance() {
        if (instance == null) {
            synchronized (EaseQuintIn.class) {
                if (instance == null) {
                    instance = new EaseQuintIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * (t /= d) * t * t * t * t + b;
    }
}