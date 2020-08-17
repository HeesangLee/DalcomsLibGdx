package dalcoms.lib.libgdx;

public class Var4TimePair implements Cloneable {
    private float variation1 = 0f, variation2 = 0f, variation3 = 0f, variation4 = 0f, time = 0f;

    public Var4TimePair() {

    }

    public Var4TimePair(float var1, float var2, float var3, float var4, float time) {
        this.variation1 = var1;
        this.variation2 = var2;
        this.variation3 = var3;
        this.variation4 = var4;
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

    public float getVariation3() {
        return variation3;
    }

    public void setVariation3(float variation3) {
        this.variation3 = variation3;
    }

    public float getVariation4() {
        return variation4;
    }

    public void setVariation4(float variation4) {
        this.variation4 = variation4;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public VarTimePair getVar1TimePair() {
        return new VarTimePair(getVariation1(), getTime());
    }

    public VarTimePair getVar2TimePair() {
        return new VarTimePair(getVariation2(), getTime());
    }

    public VarTimePair getVar3TimePair() {
        return new VarTimePair(getVariation3(), getTime());
    }

    public VarTimePair getVar4TimePair() {
        return new VarTimePair(getVariation4(), getTime());
    }

    public String toString() {
        return "((" + String.valueOf(getVariation1()) + ", " + String.valueOf(getVariation2()) +
               ", " + String.valueOf(getVariation3()) + ", " + String.valueOf(getVariation4()) +
               ")," + String.valueOf(getTime()) + ")";
    }
}
