package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuadOut implements IEasingFunction {
    private static EaseQuadOut instance;

    public static EaseQuadOut getInstance() {
        if (instance == null) {
            synchronized (EaseQuadOut.class) {
                if (instance == null) {
                    instance = new EaseQuadOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return -c * (t /= d) * (t - 2) + b;
    }
}