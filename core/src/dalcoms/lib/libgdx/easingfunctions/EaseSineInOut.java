package dalcoms.lib.libgdx.easingfunctions;

public class EaseSineInOut implements IEasingFunction {
    private static EaseSineInOut instance;

    public static EaseSineInOut getInstance() {
        if (instance == null) {
            synchronized (EaseSineInOut.class) {
                if (instance == null) {
                    instance = new EaseSineInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return -c / 2 * ((float) Math.cos(Math.PI * t / d) - 1) + b;
    }
}
