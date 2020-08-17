package dalcoms.lib.libgdx.easingfunctions;

public class EaseElasticIn implements IEasingFunction {
    private static EaseElasticIn instance;

    public static EaseElasticIn getInstance() {
        if (instance == null) {
            synchronized (EaseElasticIn.class) {
                if (instance == null) {
                    instance = new EaseElasticIn();
                }
            }
        }
        return instance;
    }

    @Override
    public float getValue(float t, float b, float c, float d) {
        if (t == 0) return b;
        if ((t /= d) == 1) return b + c;
        float p = d * .3f;
        float a = c;
        float s = p / 4;
        return -(a * (float) Math.pow(2, 10 * (t -= 1)) * (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p)) + b;

    }
}