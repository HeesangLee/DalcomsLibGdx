package dalcoms.lib.libgdx;

/**
 * (variation, time) pair class
 * Variation and time are float value. This class can be used to deal variation per time calculation.
 * Mathematical method such as linear function between to point should be included in here
 */

public class VarTimePair implements Cloneable {
    private float variation = 0, time = 0;

    public VarTimePair() {

    }

    public VarTimePair(float variation, float time) {
        this.variation = variation;
        this.time = time;
    }

    public float getVariation() {
        return variation;
    }

    public void setVariation(float variation) {
        this.variation = variation;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    /**
     * Get variation value per t using linear function between two given point
     * It also can be calculated using EaseLiner
     */
    public float getVariationBetween2Point(VarTimePair p1, VarTimePair p2, float t) {
        return Point2DFloat
                .getYBetween2Point(p1.getVariation(), p1.getTime(), p2.getVariation(), p2.getTime(),
                                   t);
    }

    public float getVariationBetween2Point(float v1, float t1, float v2, float t2, float t) {
        return Point2DFloat
                .getYBetween2Point(v1, t1, v2, t2, t);
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "(" + String.valueOf(getVariation()) + ", " + String.valueOf(getTime()) + ")";
    }

}
