package dalcoms.lib.libgdx.easingfunctions;

public class EaseLinear implements IEasingFunction {
    private static EaseLinear instance;

    public static EaseLinear getInstance() {
        if (instance == null) {
            synchronized (EaseLinear.class) {
                if (instance == null) {
                    instance = new EaseLinear();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * t / d + b;
    }
}
