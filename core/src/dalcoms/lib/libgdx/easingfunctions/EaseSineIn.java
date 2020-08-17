package dalcoms.lib.libgdx.easingfunctions;

public class EaseSineIn implements IEasingFunction {
    private static EaseSineIn instance;

    public static EaseSineIn getInstance() {
        if (instance == null) {
            synchronized (EaseSineIn.class) {
                if (instance == null) {
                    instance = new EaseSineIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        return -c * (float) Math.cos(t / d * (Math.PI / 2)) + c + b;
    }
}