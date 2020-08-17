package dalcoms.lib.libgdx.easingfunctions;

public class EaseBounceIn implements IEasingFunction {
    private static EaseBounceIn instance;

    public static EaseBounceIn getInstance() {
        if (instance == null) {
            synchronized (EaseBounceIn.class) {
                if (instance == null) {
                    instance = new EaseBounceIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        IEasingFunction easeBounceOut = EaseBounceOut.getInstance();
        return c - easeBounceOut.getValue(d - t, 0, c, d) + b;
    }
}