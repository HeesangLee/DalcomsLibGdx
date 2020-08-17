package dalcoms.lib.libgdx.easingfunctions;

public class EaseCubicOut implements IEasingFunction {
    private static EaseCubicOut instance;

    public static EaseCubicOut getInstance() {
        if (instance == null) {
            synchronized (EaseCubicOut.class) {
                if (instance == null) {
                    instance = new EaseCubicOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * ((t = t / d - 1) * t * t + 1) + b;
    }
}