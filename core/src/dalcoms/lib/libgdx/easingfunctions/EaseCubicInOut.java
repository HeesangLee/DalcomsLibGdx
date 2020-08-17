package dalcoms.lib.libgdx.easingfunctions;

public class EaseCubicInOut implements IEasingFunction {
    private static EaseCubicInOut instance;

    public static EaseCubicInOut getInstance() {
        if (instance == null) {
            synchronized (EaseCubicInOut.class) {
                if (instance == null) {
                    instance = new EaseCubicInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t * t + b;
        return c / 2 * ((t -= 2) * t * t + 2) + b;
    }
}