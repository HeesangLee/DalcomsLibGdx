package dalcoms.lib.libgdx.easingfunctions;

public class EaseCircIn implements IEasingFunction {
    private static EaseCircIn instance;

    public static EaseCircIn getInstance() {
        if (instance == null) {
            synchronized (EaseCircIn.class) {
                if (instance == null) {
                    instance = new EaseCircIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return -c * ((float) Math.sqrt(1 - (t /= d) * t) - 1) + b;
    }
}