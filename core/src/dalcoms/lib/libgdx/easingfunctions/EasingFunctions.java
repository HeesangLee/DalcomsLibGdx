package dalcoms.lib.libgdx.easingfunctions;


/**
 * Easing functions class specify the rate of change of a parameter over time
 * https://easings.net/
 *
 * @t is the current time (or position) of the tween.
 * @b is the beginning value of the property.
 * @c is the change between the beginning and destination value of the property.
 * @d is the total time of the tween.
 */

public final class EasingFunctions {
    private EasingFunctions() {
    }

    public static float linear(float t, float b, float c, float d) {
        return c * t / d + b;
    }

    public static final class Back {

        public static float easeIn(float t, float b, float c, float d) {
            float s = 1.70158f;
            return c * (t /= d) * t * ((s + 1) * t - s) + b;
        }

        public static float easeIn(float t, float b, float c, float d, float s) {
            return c * (t /= d) * t * ((s + 1) * t - s) + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            float s = 1.70158f;
            return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        }

        public static float easeOut(float t, float b, float c, float d, float s) {
            return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            float s = 1.70158f;
            if ((t /= d / 2) < 1) return c / 2 * (t * t * (((s *= (1.525f)) + 1) * t - s)) + b;
            return c / 2 * ((t -= 2) * t * (((s *= (1.525f)) + 1) * t + s) + 2) + b;
        }

        public static float easeInOut(float t, float b, float c, float d, float s) {
            if ((t /= d / 2) < 1) return c / 2 * (t * t * (((s *= (1.525f)) + 1) * t - s)) + b;
            return c / 2 * ((t -= 2) * t * (((s *= (1.525f)) + 1) * t + s) + 2) + b;
        }
    }

    public static final class Bounce {

        public static float easeIn(float t, float b, float c, float d) {
            return c - easeOut(d - t, 0, c, d) + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            if ((t /= d) < (1 / 2.75f)) {
                return c * (7.5625f * t * t) + b;
            } else if (t < (2 / 2.75f)) {
                return c * (7.5625f * (t -= (1.5f / 2.75f)) * t + .75f) + b;
            } else if (t < (2.5 / 2.75)) {
                return c * (7.5625f * (t -= (2.25f / 2.75f)) * t + .9375f) + b;
            } else {
                return c * (7.5625f * (t -= (2.625f / 2.75f)) * t + .984375f) + b;
            }
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if (t < d / 2) return easeIn(t * 2, 0, c, d) * .5f + b;
            else return easeOut(t * 2 - d, 0, c, d) * .5f + c * .5f + b;
        }
    }

    public static final class Circ {

        public static float easeIn(float t, float b, float c, float d) {
            return -c * ((float) Math.sqrt(1 - (t /= d) * t) - 1) + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            return c * (float) Math.sqrt(1 - (t = t / d - 1) * t) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if ((t /= d / 2) < 1) return -c / 2 * ((float) Math.sqrt(1 - t * t) - 1) + b;
            return c / 2 * ((float) Math.sqrt(1 - (t -= 2) * t) + 1) + b;
        }
    }

    public static final class Cubic {

        public static float easeIn(float t, float b, float c, float d) {
            return c * (t /= d) * t * t + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            return c * ((t = t / d - 1) * t * t + 1) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t * t + b;
            return c / 2 * ((t -= 2) * t * t + 2) + b;
        }

    }

    public static final class Elastic {

        public static float easeIn(float t, float b, float c, float d) {
            if (t == 0) return b;
            if ((t /= d) == 1) return b + c;
            float p = d * .3f;
            float a = c;
            float s = p / 4;
            return -(a * (float) Math.pow(2, 10 * (t -= 1)) *
                     (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p)) + b;
        }

        public static float easeIn(float t, float b, float c, float d, float a, float p) {
            float s;
            if (t == 0) return b;
            if ((t /= d) == 1) return b + c;
            if (a < Math.abs(c)) {
                a = c;
                s = p / 4;
            } else {
                s = p / (2 * (float) Math.PI) * (float) Math.asin(c / a);
            }
            return -(a * (float) Math.pow(2, 10 * (t -= 1)) *
                     (float) Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            if (t == 0) return b;
            if ((t /= d) == 1) return b + c;
            float p = d * .3f;
            float a = c;
            float s = p / 4;
            return (a * (float) Math.pow(2, -10 * t) *
                    (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p) + c + b);
        }

        public static float easeOut(float t, float b, float c, float d, float a, float p) {
            float s;
            if (t == 0) return b;
            if ((t /= d) == 1) return b + c;
            if (a < Math.abs(c)) {
                a = c;
                s = p / 4;
            } else {
                s = p / (2 * (float) Math.PI) * (float) Math.asin(c / a);
            }
            return (a * (float) Math.pow(2, -10 * t) *
                    (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p) + c + b);
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if (t == 0) return b;
            if ((t /= d / 2) == 2) return b + c;
            float p = d * (.3f * 1.5f);
            float a = c;
            float s = p / 4;
            if (t < 1)
                return -.5f * (a * (float) Math.pow(2, 10 * (t -= 1)) *
                               (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p)) + b;
            return a * (float) Math.pow(2, -10 * (t -= 1)) *
                   (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p) * .5f + c + b;
        }

        public static float easeInOut(float t, float b, float c, float d, float a, float p) {
            float s;
            if (t == 0) return b;
            if ((t /= d / 2) == 2) return b + c;
            if (a < Math.abs(c)) {
                a = c;
                s = p / 4;
            } else {
                s = p / (2 * (float) Math.PI) * (float) Math.asin(c / a);
            }
            if (t < 1)
                return -.5f * (a * (float) Math.pow(2, 10 * (t -= 1)) *
                               (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p)) + b;
            return a * (float) Math.pow(2, -10 * (t -= 1)) *
                   (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p) * .5f + c + b;
        }

    }

    public static final class Expo {

        public static float easeIn(float t, float b, float c, float d) {
            return (t == 0) ? b : c * (float) Math.pow(2, 10 * (t / d - 1)) + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            return (t == d) ? b + c : c * (-(float) Math.pow(2, -10 * t / d) + 1) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if (t == 0) return b;
            if (t == d) return b + c;
            if ((t /= d / 2) < 1) return c / 2 * (float) Math.pow(2, 10 * (t - 1)) + b;
            return c / 2 * (-(float) Math.pow(2, -10 * --t) + 2) + b;
        }

    }

    public static final class Quad {

        public static float easeIn(float t, float b, float c, float d) {
            return c * (t /= d) * t + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            return -c * (t /= d) * (t - 2) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t + b;
            return -c / 2 * ((--t) * (t - 2) - 1) + b;
        }

    }

    public static final class Quart {

        public static float easeIn(float t, float b, float c, float d) {
            return c * (t /= d) * t * t * t + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            return -c * ((t = t / d - 1) * t * t * t - 1) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t * t * t + b;
            return -c / 2 * ((t -= 2) * t * t * t - 2) + b;
        }

    }

    public static final class Quint {

        public static float easeIn(float t, float b, float c, float d) {
            return c * (t /= d) * t * t * t * t + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t * t * t * t + b;
            return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
        }

    }

    public static final class Sine {

        public static float easeIn(float t, float b, float c, float d) {
            return -c * (float) Math.cos(t / d * (Math.PI / 2)) + c + b;
        }

        public static float easeOut(float t, float b, float c, float d) {
            return c * (float) Math.sin(t / d * (Math.PI / 2)) + b;
        }

        public static float easeInOut(float t, float b, float c, float d) {
            return -c / 2 * ((float) Math.cos(Math.PI * t / d) - 1) + b;
        }

    }

}