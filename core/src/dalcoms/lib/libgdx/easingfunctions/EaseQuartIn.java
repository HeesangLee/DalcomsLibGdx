package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuartIn implements IEasingFunction {
    private static EaseQuartIn instance;

    public static EaseQuartIn getInstance() {
        if (instance == null) {
            synchronized (EaseQuartIn.class) {
                if (instance == null) {
                    instance = new EaseQuartIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * (t /= d) * t * t * t + b;
    }
}