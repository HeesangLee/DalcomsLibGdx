package dalcoms.lib.libgdx.easingfunctions;

public class EaseSineOut implements IEasingFunction {
    private static EaseSineOut instance;

    public static EaseSineOut getInstance() {
        if (instance == null) {
            synchronized (EaseSineOut.class) {
                if (instance == null) {
                    instance = new EaseSineOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return c * (float) Math.sin(t / d * (Math.PI / 2)) + b;
    }
}
