package dalcoms.lib.libgdx.easingfunctions;

public class EaseCubicIn implements IEasingFunction {
    private static EaseCubicIn instance;

    public static EaseCubicIn getInstance() {
        if (instance == null) {
            synchronized (EaseCubicIn.class) {
                if (instance == null) {
                    instance = new EaseCubicIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * (t /= d) * t * t + b;
    }
}