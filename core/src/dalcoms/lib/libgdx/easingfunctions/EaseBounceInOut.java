package dalcoms.lib.libgdx.easingfunctions;

public class EaseBounceInOut implements IEasingFunction {
    private static EaseBounceInOut instance;

    public static EaseBounceInOut getInstance() {
        if (instance == null) {
            synchronized (EaseBounceInOut.class) {
                if (instance == null) {
                    instance = new EaseBounceInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        IEasingFunction easeBounceIn = EaseBounceIn.getInstance();
        IEasingFunction easeBounceOut = EaseBounceOut.getInstance();
        if (t < d / 2) {
            return easeBounceIn.getValue(t * 2, 0, c, d) * .5f + b;
        } else {
            return easeBounceOut.getValue(t * 2 - d, 0, c, d) * .5f + c * .5f + b;
        }
    }
}