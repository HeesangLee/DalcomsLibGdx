package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuartOut implements IEasingFunction {
    private static EaseQuartOut instance;

    public static EaseQuartOut getInstance() {
        if (instance == null) {
            synchronized (EaseQuartOut.class) {
                if (instance == null) {
                    instance = new EaseQuartOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return -c * ((t = t / d - 1) * t * t * t - 1) + b;
    }
}