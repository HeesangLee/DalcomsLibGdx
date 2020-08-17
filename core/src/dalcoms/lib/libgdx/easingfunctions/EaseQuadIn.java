package dalcoms.lib.libgdx.easingfunctions;

public class EaseQuadIn implements IEasingFunction {
    private static EaseQuadIn instance;

    public static EaseQuadIn getInstance() {
        if (instance == null) {
            synchronized (EaseQuadIn.class) {
                if (instance == null) {
                    instance = new EaseQuadIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * (t /= d) * t + b;
    }
}