package dalcoms.lib.libgdx.easingfunctions;

public class EaseElasticInOut implements IEasingFunction {
    private static EaseElasticInOut instance;

    public static EaseElasticInOut getInstance() {
        if (instance == null) {
            synchronized (EaseElasticInOut.class) {
                if (instance == null) {
                    instance = new EaseElasticInOut();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if (t == 0) return b;
        if ((t /= d / 2) == 2) return b + c;
        float p = d * (.3f * 1.5f);
        float a = c;
        float s = p / 4;
        if (t < 1)
            return -.5f * (a * (float) Math.pow(2, 10 * (t -= 1)) * (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p)) + b;
        return a * (float) Math.pow(2, -10 * (t -= 1)) * (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p) * .5f + c + b;

    }
}