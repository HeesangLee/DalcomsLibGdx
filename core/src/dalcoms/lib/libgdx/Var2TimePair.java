package dalcoms.lib.libgdx;

public class Var2TimePair implements Cloneable {
    private float variation1 = 0f, variation2 = 0f, time = 0f;

    public Var2TimePair() {

    }

    public Var2TimePair(float var1, float var2, float time) {
        this.variation1 = var1;
        this.variation2 = var2;
        this.time = time;
    }

    public float getVariation1() {
        return variation1;
    }

    public void setVariation1(float variation1) {
        this.variation1 = variation1;
    }

    public float getVariation2() {
        return variation2;
    }

    public void setVariation2(float variation2) {
        this.variation2 = variation2;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public VarTimePair getVar1TimePair() {
        return new VarTimePair(getVariation1(), getTime());
    }

    public VarTimePair getVar2TimePair() {
        return new VarTimePair(getVariation2(), getTime());
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "((" + String.valueOf(getVariation1()) + ", " + String.valueOf(getVariation2()) +
               ")," + String.valueOf(getTime()) + ")";
    }
}
